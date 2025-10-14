package objects.brick;

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
        g.setColor(Color.green);
        g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
