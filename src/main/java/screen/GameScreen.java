package screen;

import objects.brick.*;
import objects.movable.Ball;
import objects.movable.Paddle;
import objects.GameObject;
import objects.powerup.ExpandPaddlePowerUp;
import objects.powerup.FastBallPowerUp;
import objects.powerup.PowerUp;
import util.AssetManager;
import util.Constant;
import util.KeyHandle;
import util.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameScreen extends Screen {
    private Paddle paddle;
    private Ball ball;
    private boolean ballFollowingPaddle = true;
    private int star = 3;
    private int level;
    private ArrayList<Brick> bricks;
    private ArrayList<Brick> brickLine;
    private ArrayList<PowerUp> fallingPowerUps = new ArrayList<>();
    private ArrayList<PowerUp> activePowerUps = new ArrayList<>();
    private final Random rng = new Random();

    public GameScreen(int level) {
        paddle = new Paddle((Constant.SCREEN_WIDTH - Constant.PADDLE_WIDTH) / 2,
                Constant.SCREEN_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT,
                Constant.PADDLE_WIDTH, Constant.PADDLE_HEIGHT);
        float ballX = paddle.getX() + paddle.getWidth() / 2 - Constant.BALL_RADIUS;
        float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
        ball = new Ball(ballX, ballY, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS, 0, 0);
        this.level = level;
        Map map = new Map(level);
        bricks = map.getBricks();

        // draw brick line at top
        brickLine = new ArrayList<Brick>();
        float brickPosY = Constant.GAME_Y_OFFSET - Constant.BRICK_HEIGHT;
        for (int i = 0; i < Constant.BRICK_COLUMNS; i++) {
            brickLine.add(new StrongBrick(i * Constant.BRICK_WIDTH, brickPosY, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT));
        }
    }


    @Override
    public void update(double deltaTime) {
        paddle.update(deltaTime);
        ball.update(deltaTime);
        ball.checkCollision(paddle);

        int count = 0;

        followPaddle(); // chua bat dau thi bong di theo paddle
        for (Brick b : bricks) {
            if (b instanceof NormalBrick) {
                count++;
            }
            ball.checkCollision(b);
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
                        case EXPAND_PADDLE -> p.activate(paddle);
                        case FAST_BALL -> p.activate(ball);
                    }
                    activePowerUps.add(p);
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

        // check star.
        if (ball.getY() > Constant.SCREEN_HEIGHT) {
            star--;
            if (star > 0) {
                // reset
                ballFollowingPaddle = true;
            } else {
                // game over
                ScreenManager.getInstance().switchScreen(new LossScreen(level));
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        paddle.render(g); // draw paddle
        ball.render(g); // draw ball
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
            float ballX = paddle.getX() + paddle.getWidth() / 2 - Constant.BALL_RADIUS;
            float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
            ball.setX(ballX);
            ball.setY(ballY);
        }

        // unfollow
        if (KeyHandle.getInstance().spacePressed && ballFollowingPaddle) {
            ballFollowingPaddle = false;
            // di len tren ben phai goc 60 do
            ball.setDx(Constant.BALL_SPEED * (float) Math.cos(Math.toRadians(60)));
            ball.setDy(-Constant.BALL_SPEED * (float) Math.sin(Math.toRadians(60)));
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
            PowerUp p;
            // chọn loại đơn giản 50/50
            if (rng.nextBoolean()) {
                p = new ExpandPaddlePowerUp(b.getX() + b.getWidth() / 2f - 12, b.getY() + b.getHeight(), 24, 24);
            } else {
                p = new FastBallPowerUp(b.getX() + b.getWidth() / 2f - 12, b.getY() + b.getHeight(), 24, 24);
            }
            fallingPowerUps.add(p);
        }
    }
}
