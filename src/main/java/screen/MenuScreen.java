package screen;

import java.awt.*;

public class MenuScreen extends Screen {

    @Override
    public void update(double deltaTime) {
        System.out.println("MenuScreen Update");
    }

    @Override
    public void render(Graphics2D g) {
        System.out.println("MenuScreen Render");
    }
}
