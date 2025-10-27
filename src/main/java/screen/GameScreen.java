package screen;

import objects.brick.*;
import objects.movable.Ball;
import objects.movable.Paddle;
import util.AssetManager;
import util.Constant;
import util.KeyHandle;
import util.Map;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends Screen {
    private Paddle paddle;
    private Ball ball;
    private boolean ballFollowingPaddle = true;
    private int star = 3;
    private int level;
    private ArrayList<Brick> bricks;
    private ArrayList<Brick> brickLine;

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
        // remove brick
        bricks.removeIf(b -> b.isDestroyed());

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

        paddle.render(g); // draw paddle
        ball.render(g); // draw ball
        for (Brick b : bricks) { // draw bricks
            b.render(g);
        }
        for (Brick br : brickLine) {
            br.render(g); // draw brick line at top
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
}
