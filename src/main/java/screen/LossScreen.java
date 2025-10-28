package screen;

import objects.ui.button.Button;
import objects.ui.button.StartButton;
import util.AssetManager;
import util.MouseHandle;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Constant.SCREEN_HEIGHT;
import static util.Constant.SCREEN_WIDTH;

public class LossScreen extends Screen {
    private int level;
    private final Button MenuButton;

    public LossScreen(int level) {
        this.level = level;
        // Create a temporary image to get FontMetrics
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(AssetManager.getInstance().getDefaultFont());
        FontMetrics fm = g2d.getFontMetrics();
        // Calculate button size and position of the start button
        int MenuButtonWidth = fm.stringWidth("               ");
        int MenuButtonHeight = fm.getHeight() + 30;
        int startButtonX = (SCREEN_WIDTH - MenuButtonWidth) / 2; // Centered horizontally
        int startButtonY = (SCREEN_HEIGHT - MenuButtonHeight) / 2; // Centered vertically
        MenuButton = new StartButton(startButtonX, startButtonY, MenuButtonWidth, MenuButtonHeight);
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isClickOn(MenuButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
            return;
        }
        // Change cursor if hovering over start button
        if (MouseHandle.getInstance().isHoverOn(MenuButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }
        System.out.println("Updating Game Over Screen for level " + level);
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawLossScreen(g);

        System.out.println("Rendering Game Over Screen");
    }
}
