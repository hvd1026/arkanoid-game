package objects.ui.dialog;

/**
 * Abstract Dialog class represents a UI dialog component.
 * It defines the basic properties and methods that all dialog types must implement.
 */

public abstract class Dialog {
    protected int x, y, width, height;

    public Dialog(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update(double deltaTime);

    public abstract void render(java.awt.Graphics2D g);
}
