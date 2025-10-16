package screen;

import objects.brick.*;
import objects.movable.Ball;
import objects.movable.Paddle;
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

    private ArrayList<Brick> bricks;

    public GameScreen() {
        paddle = new Paddle((Constant.SCREEN_WIDTH - Constant.PADDLE_WIDTH) / 2,
                Constant.SCREEN_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT,
                Constant.PADDLE_WIDTH, Constant.PADDLE_HEIGHT);
        float ballX = paddle.getX() + paddle.getWidth() / 2 - Constant.BALL_RADIUS;
        float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
        ball = new Ball(ballX, ballY, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS, 0, 0);

        Map map = new Map();
        map.load();
        bricks = map.getBricks();
    }

    @Override
    public void update(double deltaTime) {
        paddle.update(deltaTime);
        ball.update(deltaTime);
        ball.checkCollision(paddle);

        followPaddle(); // chua bat dau thi bong di theo paddle
        for (Brick b : bricks) {
            ball.checkCollision(b);
            b.update(deltaTime);
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
                ScreenManager.getInstance().switchScreen(new LossScreen());
            }
        }
    }


    @Override
    public void render(Graphics2D g) {

        paddle.render(g);
        ball.render(g);
        for (Brick b : bricks) {
            b.render(g);
        }
        g.setColor(Color.RED);
        g.drawLine(0, Constant.GAME_Y_OFFSET, Constant.SCREEN_WIDTH, Constant.GAME_Y_OFFSET);
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
