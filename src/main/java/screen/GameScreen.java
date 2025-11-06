package screen;

import objects.brick.*;
import objects.movable.Ball;
import objects.movable.Paddle;
import objects.powerup.*;
import util.AssetManager;
import util.Constant;
import util.KeyHandle;
import util.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen extends Screen {
    private final Paddle paddle;
    private final ArrayList<Ball> balls = new ArrayList<>();
    private boolean ballFollowingPaddle = true;
    private int star = 3;
    private final int level;
    private final ArrayList<Brick> bricks;
    private final ArrayList<Brick> brickLine;
    private final PowerUpManager powerUpManager;

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

        // power up manager
        powerUpManager = new PowerUpManager(paddle, balls);
    }


    @Override
    public void update(double deltaTime) {
        followPaddle(); // ball will follow paddle before game starts
        paddle.update(deltaTime);
        for (Ball b : balls) {
            b.update(deltaTime);
            b.checkCollision(paddle);
        }

        // win condition: all normal bricks destroyed
        int normalBrickCount = 0;
        for (Brick b : bricks) {
            if (b instanceof NormalBrick) {
                normalBrickCount++;
            }
            for (Ball ballObj : balls) {
                ballObj.checkCollision(b);
            }
            b.update(deltaTime);
        }
        if (normalBrickCount == 0) {
            ScreenManager.getInstance().switchScreen(new WinScreen(level, star));
        }

        // spawn power-up when brick destroyed
        Iterator<Brick> it = bricks.iterator();
        while (it.hasNext()) {
            Brick b = it.next();
            if (b.isDestroyed()) {
                powerUpManager.maybeSpawnPowerUp(b);
                it.remove();
            }
        }

        // update falling power-ups
        powerUpManager.updateFallingPowerUps(deltaTime, ballFollowingPaddle);

        // update active power-ups
        powerUpManager.updateActivePowerUps(deltaTime);

        // remove balls out of screen
        balls.removeIf(b -> b.getY() > Constant.SCREEN_HEIGHT);

        // if no balls left
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
        paddle.render(g);
        for (Ball b : balls) {
            b.render(g);
        }
        for (Brick b : bricks) { // draw bricks          
            b.render(g);
        }
        for (Brick br : brickLine) {
            br.render(g); // draw brick line at top
        }

        // draw power-ups
        powerUpManager.render(g);

        // draw stars remaining
        for (int i = 0; i < star; i++) {
            AssetManager.getInstance().draw(g, Constant.STAR_IMG,
                    700 + i * (Constant.STAR_SIZE + 5),
                    10,
                    Constant.STAR_SIZE,
                    Constant.STAR_SIZE);
        }

        // draw level text
        g.setFont(AssetManager.getInstance().getDefaultFont());
        g.setColor(Color.WHITE);
        g.drawString(String.format("LEVEL %d", level), 350, 30);

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

        // unfollow when space pressed
        if (KeyHandle.getInstance().spacePressed && ballFollowingPaddle) {
            ballFollowingPaddle = false;
            if (!balls.isEmpty()) {
                Ball first = balls.getFirst();
                first.setDx(Constant.BALL_SPEED * (float) Math.cos(Math.toRadians(Constant.BALL_START_ANGLE_DEG)));
                first.setDy(-Constant.BALL_SPEED * (float) Math.sin(Math.toRadians(Constant.BALL_START_ANGLE_DEG)));
            }
        }
    }

    private void respawnSingleBallOnPaddle() {
        float ballX = paddle.getX() + paddle.getWidth() / 2f - Constant.BALL_RADIUS;
        float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
        balls.add(new Ball(ballX, ballY, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS, 0, 0));
    }
}
