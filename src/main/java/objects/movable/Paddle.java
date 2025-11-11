package objects.movable;

import util.AssetManager;
import util.Constant;
import util.KeyHandle;

import java.awt.*;

/**
 * Paddle class represents the player's paddle in the game.
 * It can move left and right based on user input.
 */

public class Paddle extends MovableObject {
    public Paddle(float x, float y, int width, int height) {
        super(x, y, width, height, 0, 0);
    }

    @Override
    public void update(double deltaTime) {
        if (KeyHandle.getInstance().leftPressed) {
            moveLeft();
        } else if (KeyHandle.getInstance().rightPressed) {
            moveRight();
        } else {
            setDx(0);
        }

        move(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, Constant.PADDLE_IMG,
                (int) getX(),
                (int) getY(),
                getWidth(),
                getHeight());
    }

    public void moveLeft() {
        if (getX() <= 0) { // reach left edge
            setDx(0);
            return;
        }
        setDx(-Constant.PADDLE_SPEED);
    }

    public void moveRight() {
        if (getX() + getWidth() >= Constant.SCREEN_WIDTH) { // reach right edge
            setDx(0);
            return;
        }

        setDx(Constant.PADDLE_SPEED);
    }
}
