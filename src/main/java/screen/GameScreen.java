package screen;

import objects.brick.*;
import objects.movable.Ball;
import objects.movable.Paddle;
import objects.GameObject;
import objects.powerup.ExpandPaddlePowerUp;
import objects.powerup.FastBallPowerUp;
import objects.powerup.DoubleDamagePowerUp;
import objects.powerup.MultiBallPowerUp;
import objects.powerup.PowerUp;
import objects.powerup.PowerUpType;
import util.AssetManager;
import util.Constant;
import util.KeyHandle;
import util.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameScreen extends Screen {
    private final Paddle paddle;
    private final ArrayList<Ball> balls = new ArrayList<>();
    private boolean ballFollowingPaddle = true;
    private int star = 3;
    private final int level;
    private final ArrayList<Brick> bricks;
    private final ArrayList<Brick> brickLine;
    private final ArrayList<PowerUp> fallingPowerUps = new ArrayList<>();
    private final ArrayList<PowerUp> activePowerUps = new ArrayList<>();
    private final Random rng = new Random();

    public GameScreen(int level) {
        paddle = new Paddle((Constant.SCREEN_WIDTH - Constant.PADDLE_WIDTH) / 2f,
                Constant.SCREEN_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT,
                Constant.PADDLE_WIDTH, Constant.PADDLE_HEIGHT);
        float ballX = paddle.getX() + paddle.getWidth() / 2f - Constant.BALL_RADIUS;
        float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
        balls.add(new Ball(ballX, ballY, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS, 0, 0));
        this.level = level;
        Map map = new Map(level);
        bricks = map.getBricks();

        // draw brick line at top
        brickLine = new ArrayList<>();
        float brickPosY = Constant.GAME_Y_OFFSET - Constant.BRICK_HEIGHT;
        for (int i = 0; i < Constant.BRICK_COLUMNS; i++) {
            brickLine.add(new StrongBrick(i * Constant.BRICK_WIDTH, brickPosY, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT));
        }
    }


    @Override
    public void update(double deltaTime) {
        paddle.update(deltaTime);
        for (Ball b : new ArrayList<>(balls)) {
            b.update(deltaTime);
            b.checkCollision(paddle);
        }

        // va chạm giữa các quả bóng
        handleBallBallCollisions();

        int count = 0;

        followPaddle(); // chua bat dau thi bong di theo paddle
        for (Brick b : bricks) {
            if (b instanceof NormalBrick) {
                count++;
            }
            for (Ball ballObj : balls) {
                ballObj.checkCollision(b);
            }
            b.update(deltaTime);
        }

        if (count == 0) {
            ScreenManager.getInstance().switchScreen(new WinScreen(level, star));
        }
        // spawn powerups và remove bricks bị phá
        Iterator<Brick> it = bricks.iterator();
        while (it.hasNext()) {
            Brick b = it.next();
            if (b.isDestroyed()) {
                maybeSpawnPowerUp(b);
                it.remove();
            }
        }

        // update powerups đang rơi
        Iterator<PowerUp> pit = fallingPowerUps.iterator();
        while (pit.hasNext()) {
            PowerUp p = pit.next();
            p.update(deltaTime);
            // chạm đáy màn hình thì biến mất
            if (p.getY() > Constant.SCREEN_HEIGHT) {
                pit.remove();
                continue;
            }
            // va chạm với paddle
            if (isIntersect(p, paddle)) {
                // nếu bóng đang bám paddle thì thu gom item nhưng KHÔNG áp dụng hiệu ứng
                if (ballFollowingPaddle) {
                    pit.remove();
                    continue;
                }
                // nếu đã có powerup cùng loại đang active, reset thời gian và bỏ item mới
                boolean refreshed = false;
                for (PowerUp ap : activePowerUps) {
                    if (ap.getType() == p.getType()) {
                        ap.setActivatedTime(System.currentTimeMillis());
                        refreshed = true;
                        break;
                    }
                }
                if (!refreshed) {
                    // kích hoạt tùy loại
                    switch (p.getType()) {
                        case EXPAND_PADDLE -> {
                            p.activate(paddle);
                            activePowerUps.add(p);
                        }
                        case FAST_BALL -> {
                            // áp dụng cho tất cả bóng hiện có
                            for (Ball ballObj : balls) {
                                FastBallPowerUp fb = new FastBallPowerUp(p.getX(), p.getY(), p.getWidth(), p.getHeight());
                                fb.activate(ballObj);
                                activePowerUps.add(fb);
                            }
                        }
                        case DOUBLE_DAMAGE -> {
                            for (Ball ballObj : balls) {
                                DoubleDamagePowerUp dd = new DoubleDamagePowerUp(p.getX(), p.getY(), p.getWidth(), p.getHeight());
                                dd.activate(ballObj);
                                activePowerUps.add(dd);
                            }
                        }
                        case MULTI_BALL -> // hiệu ứng tức thời – không add vào active list
                                handleMultiBall();
                    }
                }
                pit.remove();
            }
        }

        // quản lý hết hạn powerups đang hiệu lực
        Iterator<PowerUp> ait = activePowerUps.iterator();
        while (ait.hasNext()) {
            PowerUp p = ait.next();
            if (p.isExpired()) {
                p.removeEffect(p.getAppliedTo());
                ait.remove();
            }
        }

        // remove balls ra ngoài màn
        balls.removeIf(b -> b.getY() > Constant.SCREEN_HEIGHT);

        // nếu hết bóng -> mất 1 mạng và respawn 1 bóng bám paddle
        if (balls.isEmpty()) {
            star--;
            if (star > 0) {
                respawnSingleBallOnPaddle();
                ballFollowingPaddle = true;
            } else {
                ScreenManager.getInstance().switchScreen(new LossScreen(level));
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        paddle.render(g); // draw paddle
        for (Ball b : balls) {
            b.render(g); // draw balls
        }
        for (Brick b : bricks) { // draw bricks          
            b.render(g);
        }
        for (Brick br : brickLine) {
            br.render(g); // draw brick line at top
        }

        // draw powerups đang rơi
        for (PowerUp p : fallingPowerUps) {
            p.render(g);
        }

        // draw stars

        for (int i = 0; i < star; i++) {
            AssetManager.getInstance().draw(g, Constant.STAR_IMG,
                    700 + i * (Constant.STAR_SIZE + 5),
                    10,
                    Constant.STAR_SIZE,
                    Constant.STAR_SIZE);
        }

        g.dispose();

    }


    void followPaddle() {
        // follow
        if (ballFollowingPaddle) {
            if (!balls.isEmpty()) {
                Ball first = balls.getFirst();
                float ballX = paddle.getX() + paddle.getWidth() / 2f - Constant.BALL_RADIUS;
                float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
                first.setX(ballX);
                first.setY(ballY);
            }
        }

        // unfollow
        if (KeyHandle.getInstance().spacePressed && ballFollowingPaddle) {
            ballFollowingPaddle = false;
            // di len tren ben phai goc 60 do
            if (!balls.isEmpty()) {
                Ball first = balls.getFirst();
                first.setDx(Constant.BALL_SPEED * (float) Math.cos(Math.toRadians(Constant.BALL_START_ANGLE_DEG)));
                first.setDy(-Constant.BALL_SPEED * (float) Math.sin(Math.toRadians(Constant.BALL_START_ANGLE_DEG)));
            }
        }
    }

    private boolean isIntersect(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

    private void maybeSpawnPowerUp(Brick b) {
        if (rng.nextFloat() <= Constant.POWERUP_DROP_RATE) {
            // chọn ngẫu nhiên giữa 4 loại
            int t = rng.nextInt(4);
            PowerUp p;
            float px = b.getX() + b.getWidth() / 2f - 12;
            float py = b.getY() + b.getHeight();
            int w = Constant.POWERUP_WIDTH, h = Constant.POWERUP_HEIGHT;
            if (t == 0) p = new ExpandPaddlePowerUp(px, py, w, h);
            else if (t == 1) p = new FastBallPowerUp(px, py, w, h);
            else if (t == 2) p = new MultiBallPowerUp(px, py, w, h);
            else p = new DoubleDamagePowerUp(px, py, w, h);
            fallingPowerUps.add(p);
        }
    }

    private void handleMultiBall() {
        if (balls.isEmpty()) {
            respawnSingleBallOnPaddle();
            ballFollowingPaddle = true;
            return;
        }
        // dựa trên quả đầu tiên để nhân bản
        Ball base = balls.getFirst();
        float x = base.getX();
        float y = base.getY();
        float speed = (float) Math.sqrt(base.getDx() * base.getDx() + base.getDy() * base.getDy());
        if (speed == 0) speed = Constant.BALL_SPEED;
        // góc hiện tại của bóng gốc
        float baseAngleDeg = (float) Math.toDegrees(Math.atan2(-base.getDy(), base.getDx()));
        float spread = Constant.MULTI_BALL_SPREAD_DEG;
        float angleLeft = baseAngleDeg - spread;
        float angleRight = baseAngleDeg + spread;

        Ball b1 = new Ball(x, y, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS,
                speed * (float) Math.cos(Math.toRadians(angleLeft)), -speed * (float) Math.sin(Math.toRadians(angleLeft)));
        Ball b2 = new Ball(x, y, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS,
                speed * (float) Math.cos(Math.toRadians(angleRight)), -speed * (float) Math.sin(Math.toRadians(angleRight)));
        b1.setDamage(base.getDamage());
        b2.setDamage(base.getDamage());
        b1.setCanBreakStrongBrick(base.isCanBreakStrongBrick());
        b2.setCanBreakStrongBrick(base.isCanBreakStrongBrick());
        balls.add(b1);
        balls.add(b2);

        // đồng bộ các hiệu ứng đang tác dụng trên bóng gốc cho bóng clone
        // để thời điểm hết hạn giống nhau
        for (PowerUp ap : new ArrayList<>(activePowerUps)) {
            if (ap.getAppliedTo() == base) {
                if (ap.getType() == PowerUpType.FAST_BALL) {
                    // tạo instance cho b1, b2 với activatedTime giống ap
                    FastBallPowerUp fb1 = new FastBallPowerUp(ap.getX(), ap.getY(), ap.getWidth(), ap.getHeight());
                    FastBallPowerUp fb2 = new FastBallPowerUp(ap.getX(), ap.getY(), ap.getWidth(), ap.getHeight());
                    // tránh nhân đôi tốc độ: pre-scale ngược rồi apply
                    b1.setDx(b1.getDx() / FastBallPowerUp.SPEED_SCALE);
                    b1.setDy(b1.getDy() / FastBallPowerUp.SPEED_SCALE);
                    b2.setDx(b2.getDx() / FastBallPowerUp.SPEED_SCALE);
                    b2.setDy(b2.getDy() / FastBallPowerUp.SPEED_SCALE);
                    fb1.applyEffect(b1);
                    fb2.applyEffect(b2);
                    fb1.setActivated(true);
                    fb2.setActivated(true);
                    fb1.setActivatedTime(ap.getActivatedTime());
                    fb2.setActivatedTime(ap.getActivatedTime());
                    activePowerUps.add(fb1);
                    activePowerUps.add(fb2);
                } else if (ap.getType() == PowerUpType.DOUBLE_DAMAGE) {
                    DoubleDamagePowerUp dd1 = new DoubleDamagePowerUp(ap.getX(), ap.getY(), ap.getWidth(), ap.getHeight());
                    DoubleDamagePowerUp dd2 = new DoubleDamagePowerUp(ap.getX(), ap.getY(), ap.getWidth(), ap.getHeight());
                    // đảm bảo previous state của clone là false để remove trả về đúng
                    b1.setCanBreakStrongBrick(false);
                    b2.setCanBreakStrongBrick(false);
                    dd1.applyEffect(b1);
                    dd2.applyEffect(b2);
                    dd1.setActivated(true);
                    dd2.setActivated(true);
                    dd1.setActivatedTime(ap.getActivatedTime());
                    dd2.setActivatedTime(ap.getActivatedTime());
                    activePowerUps.add(dd1);
                    activePowerUps.add(dd2);
                }
            }
        }
    }

    private void handleBallBallCollisions() {
        int n = balls.size();
        for (int i = 0; i < n; i++) {
            Ball a = balls.get(i);
            float ax = a.getX() + a.getWidth() / 2f;
            float ay = a.getY() + a.getHeight() / 2f;
            float ar = a.getWidth() / 2f;
            for (int j = i + 1; j < n; j++) {
                Ball b = balls.get(j);
                float bx = b.getX() + b.getWidth() / 2f;
                float by = b.getY() + b.getHeight() / 2f;
                float br = b.getWidth() / 2f;
                float dx = bx - ax;
                float dy = by - ay;
                float dist2 = dx * dx + dy * dy;
                float minDist = ar + br;
                if (dist2 > 0 && dist2 < minDist * minDist) {
                    // tách hai bóng ra nhẹ để tránh dính
                    float dist = (float) Math.sqrt(dist2);
                    float nx = dx / dist;
                    float ny = dy / dist;
                    float overlap = (minDist - dist) * 0.5f;
                    a.setX(a.getX() - nx * overlap);
                    a.setY(a.getY() - ny * overlap);
                    b.setX(b.getX() + nx * overlap);
                    b.setY(b.getY() + ny * overlap);

                    // va chạm đàn hồi đơn giản: hoán đổi thành phần vận tốc theo pháp tuyến
                    float avn = a.getDx() * nx + a.getDy() * ny;
                    float bvn = b.getDx() * nx + b.getDy() * ny;
                    float avt = -a.getDx() * ny + a.getDy() * nx; // thành phần tiếp tuyến
                    float bvt = -b.getDx() * ny + b.getDy() * nx;

                    // đổi phần theo pháp tuyến (khối lượng bằng nhau)
                    float avnAfter = bvn;
                    float bvnAfter = avn;

                    // chuyển lại về (dx, dy)
                    a.setDx(avnAfter * nx - avt * ny);
                    a.setDy(avnAfter * ny + avt * nx);
                    b.setDx(bvnAfter * nx - bvt * ny);
                    b.setDy(bvnAfter * ny + bvt * nx);
                }
            }
        }
    }

    private void respawnSingleBallOnPaddle() {
        float ballX = paddle.getX() + paddle.getWidth() / 2f - Constant.BALL_RADIUS;
        float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
        balls.add(new Ball(ballX, ballY, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS, 0, 0));
    }
}
