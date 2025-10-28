package screen;

import objects.ui.button.Button;
import objects.ui.button.StartButton;
import util.AssetManager;
import util.MouseHandle;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Constant.SCREEN_HEIGHT;
import static util.Constant.SCREEN_WIDTH;

/**
 * MenuScreen class is the main menu screen of the application.
 * Contains a start button that allows users to begin the game.
 */
public class MenuScreen extends Screen {
    private final Button startButton;

    public MenuScreen() {
        // Create a temporary image to get FontMetrics
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(AssetManager.getInstance().getDefaultFont());
        FontMetrics fm = g2d.getFontMetrics();
        // Calculate button size and position of the start button
        int startButtonWidth = fm.stringWidth("START");
        int startButtonHeight = fm.getHeight();
        int startButtonX = (SCREEN_WIDTH - startButtonWidth) / 2; // Centered horizontally
        int startButtonY = (SCREEN_HEIGHT - startButtonHeight) / 2; // Centered vertically
        startButton = new StartButton(startButtonX, startButtonY, startButtonWidth, startButtonHeight);
    }

    @Override
    public void update(double deltaTime) {
        // Check mouse click on start button
        if (MouseHandle.getInstance().isClickOn(startButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
            return;
        }
        // Change cursor if hovering over start button
        if (MouseHandle.getInstance().isHoverOn(startButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        startButton.render(g);
    }
}
