package objects.movable;

import objects.brick.Brick;
import objects.brick.StrongBrick;
import util.AssetManager;
import util.Constant;
import util.SoundManager;

import java.awt.*;

/**
 * Ball represents the ball object in the game that moves and interacts with paddles and bricks.
 */

public class Ball extends MovableObject {
    private boolean canBreakStrongBrick = false;

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
        AssetManager.getInstance().draw(g, Constant.BALL_IMG, (int) getX(), (int) getY(), getWidth(), getHeight());
    }

    public void bounceOff(Object other) {
        // coordinates and radius of ball
        float centerX = getX() + getWidth() / 2f;
        float centerY = getY() + getHeight() / 2f;
        float radius = getWidth() / 2f;

        if (other instanceof Paddle paddle) {
            if (centerY < paddle.getY()) { // top paddle
                float currentSpeed = (float) Math.sqrt(getDx() * getDx() + getDy() * getDy());
                // reflect ball based on where it hits the paddle
                float paddleMiddle = paddle.getX() + paddle.getWidth() / 2f;
                float deltaX = centerX - paddleMiddle;
                float ratio = deltaX / (paddle.getWidth() / 2f); // rate between -1 and 1
                float bounceAngle = ratio * Constant.BALL_MAX_ANGLE; // reflect angle

                // set new velocity
                setDx(currentSpeed * (float) Math.sin(Math.toRadians(bounceAngle)));
                setDy(-currentSpeed * (float) Math.cos(Math.toRadians(bounceAngle)));
            }
            // bottom paddle
            else if (centerY > paddle.getY() + paddle.getHeight()) {
                setY(paddle.getY() + paddle.getHeight());
                setDy(-getDy());
            }
            // left paddle
            else if (centerX < paddle.getX()) {
                setX(paddle.getX() - radius * 2);
                setDx(-getDx());
            }
            // right paddle
            else if (centerX > paddle.getX() + paddle.getWidth()) {
                setX(paddle.getX() + paddle.getWidth());
                setDx(-getDx());
            }
        }

        // brick bounce
        if (other instanceof Brick brick) {
            // top brick
            if (centerY < brick.getY()) {
                setY(brick.getY() - radius * 2);
                if (getDy() > 0)
                    setDy(-getDy());
            }
            // bottom brick
            else if (centerY > brick.getY() + brick.getHeight()) {
                setY(brick.getY() + brick.getHeight());
                if (getDy() < 0)
                    setDy(-getDy());
            }
            // left brick
            else if (centerX < brick.getX()) {
                setX(brick.getX() - radius * 2);
                if (getDx() > 0)
                    setDx(-getDx());
            }
            // right brick
            else if (centerX > brick.getX() + brick.getWidth()) {
                setX(brick.getX() + brick.getWidth());
                if (getDx() < 0)
                    setDx(-getDx());
            }
        }
    }

    public void checkCollision(Object other) {
        // coordinates and radius of ball
        float centerX = getX() + getWidth() / 2f;
        float centerY = getY() + getHeight() / 2f;
        float radius = getWidth() / 2f;

        // check paddle collision
        if (other instanceof Paddle paddle) {
            // closest point on paddle to ball center
            float closestX = Math.max(paddle.getX(), Math.min(centerX, paddle.getX() + paddle.getWidth()));
            float closestY = Math.max(paddle.getY(), Math.min(centerY, paddle.getY() + paddle.getHeight()));

            // distance from ball center to closest point
            float deltaX = centerX - closestX;
            float deltaY = centerY - closestY;
            if (deltaX * deltaX + deltaY * deltaY < radius * radius) {
                // collision detected
                SoundManager.getInstance().playAudio("paddle");
                bounceOff(paddle);
            }
        }

        // check brick collision
        if (other instanceof Brick brick) {
            float closestX = Math.max(brick.getX(), Math.min(centerX, brick.getX() + brick.getWidth()));
            float closestY = Math.max(brick.getY(), Math.min(centerY, brick.getY() + brick.getHeight()));
            float deltaX = centerX - closestX;
            float deltaY = centerY - closestY;
            if (deltaX * deltaX + deltaY * deltaY < radius * radius) {
                bounceOff(brick);
                brick.takeHit(getDamage());
                if (brick instanceof StrongBrick && isCanBreakStrongBrick()) {
                    brick.setHitPoints(0);
                }
            }

        }
    }

    void checkWallCollision() {
        if (getX() <= 0) {
            // left wall
            setX(0);
            if (getDx() < 0) {
                setDx(-getDx());
                SoundManager.getInstance().playAudio("strong_brick");
            }
        } else if (getX() + getWidth() >= Constant.SCREEN_WIDTH) {
            // right wall
            setX(Constant.SCREEN_WIDTH - getWidth());
            if (getDx() > 0) {
                setDx(-getDx());
                SoundManager.getInstance().playAudio("strong_brick");
            }
        }
        if (getY() <= Constant.GAME_Y_OFFSET) {
            // top wall
            setY(Constant.GAME_Y_OFFSET);
            if (getDy() < 0) {
                setDy(-getDy());
                SoundManager.getInstance().playAudio("strong_brick");
            }
        }
    }

    public int getDamage() {
        return 1;
    }

    public boolean isCanBreakStrongBrick() {
        return canBreakStrongBrick;
    }

    public void setCanBreakStrongBrick(boolean canBreakStrongBrick) {
        this.canBreakStrongBrick = canBreakStrongBrick;
    }

}
