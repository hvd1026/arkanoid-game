package objects.brick;

import util.AssetManager;
import util.SoundManager;

import java.awt.*;

/**
 * NormalBrick represents a standard brick in the game that can be destroyed with one hit.
 */

public class NormalBrick extends Brick {
    private final int style;
    // constructor with default style
    public NormalBrick(float x, float y, int width, int height) {
        super(x, y, width, height);
        setHitPoints(1); // max touch 1 time
        this.style = 1; // default style
    }
    // constructor with specified style
    public NormalBrick(float x, float y, int width, int height, int style) {
        super(x, y, width, height);
        setHitPoints(1); // max touch 1 time
        this.style = style;
    }

    @Override
    public void update(double deltaTime) {

    }
    // reduce hit points and play sound when hit
    @Override
    public void takeHit(int damage) {
        SoundManager.getInstance().playAudio("normal_brick");
        setHitPoints(getHitPoints() - Math.max(1, damage));
    }
    // render the brick using AssetManager
    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, style, (int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
