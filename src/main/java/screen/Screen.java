package screen;

import java.awt.*;

/**
 * Abstract class representing a screen in the game.
 * Each screen must implement update and render methods.
 */

public abstract class Screen {

    public abstract void update(double deltaTime);

    public abstract void render(Graphics2D g);
}
