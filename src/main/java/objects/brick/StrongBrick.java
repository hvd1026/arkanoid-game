package objects.brick;

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
        g.setColor(Color.blue);
        g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }
}
