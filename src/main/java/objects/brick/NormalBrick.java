package objects.brick;

import util.AssetManager;

import java.awt.*;

public class NormalBrick extends Brick {

    public NormalBrick(float x, float y, int width, int height) {
        super(x, y, width, height);
        setHitPoints(1); // toi da 1 lan cham.
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, util.Constant.NORMAL_BRICK_IMG_1, (int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
