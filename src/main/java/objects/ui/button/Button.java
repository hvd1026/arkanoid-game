package objects.ui.button;

import util.MouseHandle;

import java.awt.*;

public abstract class Button extends Rectangle {
    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void update(double deltaTime);

    public abstract void render(Graphics2D g);

    public Rectangle getBounds() {
        return this;
    }
}
