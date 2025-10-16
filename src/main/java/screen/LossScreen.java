package screen;

import java.awt.*;

public class LossScreen extends Screen {


    @Override
    public void update(double deltaTime) {
        System.out.println("Update Game Over Screen");
    }

    @Override
    public void render(Graphics2D g) {
        System.out.println("Rendering Game Over Screen");
    }
}
