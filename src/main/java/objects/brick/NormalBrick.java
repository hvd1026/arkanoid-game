package objects.brick;

import util.AssetManager;

import java.awt.*;

public class NormalBrick extends Brick {
    private int style;

    public NormalBrick(float x, float y, int width, int height) {
        super(x, y, width, height);
        setHitPoints(1); // toi da 1 lan cham.
        this.style = 1; // mac dinh la style 1
    }

    public NormalBrick(float x, float y, int width, int height, int style) {
        super(x, y, width, height);
        setHitPoints(1); // toi da 1 lan cham.
        this.style = style;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, style, (int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
