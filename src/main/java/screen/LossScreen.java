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
    private final Button TryAgainButton;

    public LossScreen(int level) {
        this.level = level;
        // Create a temporary image to get FontMetrics
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(AssetManager.getInstance().getDefaultFont());
        FontMetrics fm = g2d.getFontMetrics();
        // Calculate button size and position of the menu & try_again button
        int MenuButtonWidth = fm.stringWidth("                 ");
        int MenuButtonHeight = fm.getHeight() + 30;
        int MenuButtonX = (SCREEN_WIDTH - MenuButtonWidth) / 2; // Centered horizontally
        int MenuButtonY = (SCREEN_HEIGHT - MenuButtonHeight) / 2 ; // Centered vertically
        MenuButton = new StartButton(MenuButtonX, MenuButtonY, MenuButtonWidth, MenuButtonHeight);

        //TRY AGAIN
        int TryAgainButtonWidth = fm.stringWidth("               ");
        int TryAgainButtonHeight = fm.getHeight() + 30;
        int TryAgainButtonX = (SCREEN_WIDTH - TryAgainButtonWidth) / 2; // Centered horizontally
        int TryAgainButtonY = (SCREEN_HEIGHT - TryAgainButtonHeight) / 2 + 70; // Centered vertically
        TryAgainButton = new StartButton(TryAgainButtonX, TryAgainButtonY, TryAgainButtonWidth, TryAgainButtonHeight);
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isClickOn(MenuButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
            return;
        }
        // Change cursor if hovering over Menu button
        if (MouseHandle.getInstance().isHoverOn(MenuButton) || MouseHandle.getInstance().isHoverOn(TryAgainButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }
        System.out.println("Updating Game Over Screen for level " + level);

        //TRY AGAIN
        if (MouseHandle.getInstance().isClickOn(TryAgainButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen( new GameScreen(level));
            return;
        }
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawLossScreen(g);

        System.out.println("Rendering Game Over Screen");
    }
}
