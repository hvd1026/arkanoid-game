package screen;

import java.awt.*;

public class LossScreen extends Screen {
    private int level;

    public LossScreen(int level) {
        this.level = level;
    }

    @Override
    public void update(double deltaTime) {
        System.out.println("Updating Game Over Screen for level " + level);
    }

    @Override
    public void render(Graphics2D g) {
        System.out.println("Rendering Game Over Screen");
    }
}
