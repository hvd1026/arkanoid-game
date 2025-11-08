package objects.brick;

import util.AssetManager;
import util.Constant;
import util.SoundManager;

import java.awt.*;

public class StrongBrick extends Brick {

    public StrongBrick(float x, float y, int width, int height) {
        super(x, y, width, height);
        setHitPoints(2); // toi da 2 lan cham.
    }


    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, Constant.STRONG_BRICK_IMG, (int) getX(), (int) getY(), getWidth(), getHeight());
    }

    @Override
    public void takeHit(int damage) {
        SoundManager.getInstance().playAudio("strong_brick");
        return;
    }
}
