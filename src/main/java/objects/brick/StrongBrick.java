package objects.brick;

import util.AssetManager;
import util.Constant;
import util.SoundManager;

import java.awt.*;

/**
 * StrongBrick represents a durable brick in the game that cannot be destroyed by hits.
 */

public class StrongBrick extends Brick {

    public StrongBrick(float x, float y, int width, int height) {
        super(x, y, width, height);
        setHitPoints(1); // although it has hit points, it cannot be destroyed
    }


    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, Constant.STRONG_BRICK_IMG,
                (int) getX(),
                (int) getY(),
                getWidth(),
                getHeight());
    }
    // play sound when hit, but do not reduce hit points
    @Override
    public void takeHit(int damage) {
        SoundManager.getInstance().playAudio("strong_brick");
    }
}
