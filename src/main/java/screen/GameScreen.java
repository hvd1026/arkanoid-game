package screen;

import java.awt.*;

public class GameScreen extends Screen {
    @Override
    public void update(double deltaTime) {
        System.out.println("GameScreen Update");
    }

    @Override
    public void render(Graphics2D g) {
        System.out.println("GameScreen Render");
    }
}
