package objects.movable;

import objects.brick.Brick;
import util.Constant;

import java.awt.*;

public class Ball extends MovableObject {
    public Ball(float x, float y, int weight, int height, float dx, float dy) {
        super(x, y, weight, height, dx, dy);
    }


    @Override
    public void update(double deltaTime) {
        // check wall collision
        checkWallCollision();

        // update position
        move(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        // placeholder
        g.setColor(Color.WHITE);
        g.fillOval((int) getX(), (int) getY(), getWidth(), getHeight());
    }

    public void bounceOff(Object other) {
        // toa do, tam ball
        float centerX = getX() + getWidth() / 2f;
        float centerY = getY() + getHeight() / 2f;
        float radius = getWidth() / 2f;

        if (other instanceof Paddle) {
            Paddle paddle = (Paddle) other;
            setY(paddle.getY() - radius * 2); // cho bong len tren paddle, tranh ket
            float currentSpeed = (float) Math.sqrt(getDx() * getDx() + getDy() * getDy());

            // goc phan xa
            float paddleMiddle = paddle.getX() + paddle.getWidth() / 2f;
            float deltaX = centerX - paddleMiddle;
            float ratio = deltaX / (paddle.getWidth() / 2f); // ti le khoang cach
            float bounceAngle = ratio * Constant.BALL_MAX_ANGLE; // goc phan xa ( so voi truc y)

            // cap nhat van toc
            setDx(currentSpeed * (float) Math.sin(Math.toRadians(bounceAngle)));
            setDy(-currentSpeed * (float) Math.cos(Math.toRadians(bounceAngle)));
        }

        // brick
        if (other instanceof Brick) {
            Brick brick = (Brick) other;
            // top brick
            if (centerY < brick.getY()) {
                setY(brick.getY() - radius * 2);
                setDy(-getDy());
            }
            // bottom brick
            else if (centerY > brick.getY() + brick.getHeight()) {
                setY(brick.getY() + brick.getHeight());
                setDy(-getDy());
            }
            // left brick
            else if (centerX < brick.getX()) {
                setX(brick.getX() - radius * 2);
                setDx(-getDx());
            }
            // right brick
            else if (centerX > brick.getX() + brick.getWidth()) {
                setX(brick.getX() + brick.getWidth());
                setDx(-getDx());
            }

        }
    }

    public void checkCollision(Object other) {
        // toa do, tam ball
        float centerX = getX() + getWidth() / 2f;
        float centerY = getY() + getHeight() / 2f;
        float radius = getWidth() / 2f;

        // paddle
        if (other instanceof Paddle) {
            Paddle paddle = (Paddle) other;
            // diem gan ball nhat tren paddle
            float closestX = Math.max(paddle.getX(), Math.min(centerX, paddle.getX() + paddle.getWidth()));
            float closestY = Math.max(paddle.getY(), Math.min(centerY, paddle.getY() + paddle.getHeight()));

            // kcach
            float deltaX = centerX - closestX;
            float deltaY = centerY - closestY;
            if (deltaX * deltaX + deltaY * deltaY < radius * radius) { // va cham vi khoang cach < r
                bounceOff(paddle);
            }
        }

        // brick
        if (other instanceof Brick) {
            Brick brick = (Brick) other;
            float closestX = Math.max(brick.getX(), Math.min(centerX, brick.getX() + brick.getWidth()));
            float closestY = Math.max(brick.getY(), Math.min(centerY, brick.getY() + brick.getHeight()));
            float deltaX = centerX - closestX;
            float deltaY = centerY - closestY;
            if (deltaX * deltaX + deltaY * deltaY < radius * radius) {
                bounceOff(brick);
                brick.takeHit();
            }

        }
    }

    void checkWallCollision() {
        if (getX() <= 0) { // left
            setX(0);
            setDx(-getDx());
        } else if (getX() + getWidth() >= Constant.SCREEN_WIDTH) { // right
            setX(Constant.SCREEN_WIDTH - getWidth());
            setDx(-getDx());
        }
        if (getY() <= Constant.GAME_Y_OFFSET) { // top
            setY(Constant.GAME_Y_OFFSET);
            setDy(-getDy());
        }
    }

}
