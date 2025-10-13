package screen;

import objects.movable.Ball;
import objects.movable.Paddle;
import util.Constant;
import util.KeyHandle;

import java.awt.*;

public class GameScreen extends Screen {
    private Paddle paddle;
    private Ball ball;
    private boolean ballFollowingPaddle = true;

    public GameScreen() {
        paddle = new Paddle((Constant.SCREEN_WIDTH - Constant.PADDLE_WIDTH) / 2,
                Constant.SCREEN_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT,
                Constant.PADDLE_WIDTH, Constant.PADDLE_HEIGHT);
        float ballX = paddle.getX() + paddle.getWidth() / 2 - Constant.BALL_RADIUS;
        float ballY = paddle.getY() - Constant.BALL_RADIUS * 2;
        ball = new Ball(ballX, ballY, 2 * Constant.BALL_RADIUS, 2 * Constant.BALL_RADIUS, 0, 0);
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

    @Override
    public void update(double deltaTime) {
        paddle.update(deltaTime);
        ball.update(deltaTime);
        ball.checkCollision(paddle);

        followPaddle(); // chua bat dau thi bong di theo paddle
    }

    @Override
    public void render(Graphics2D g) {
        paddle.render(g);
        ball.render(g);
    }
}
