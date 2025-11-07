package screen;

import objects.ui.button.Button;
import objects.ui.button.ExitButton;
import objects.ui.button.StartButton;
import util.AssetManager;
import util.Constant;
import util.MouseHandle;
import util.SoundManager;

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
    private final Button exitButton;
    private boolean soundLoaded = false;

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

        // Calculate button size and position of the exit button

        int exitButtonWidth = fm.stringWidth("EXIT");
        int exitButtonHeight = fm.getHeight();
        int exitButtonX = (SCREEN_WIDTH - exitButtonWidth) / 2; // Centered horizontally
        int exitButtonY = ((SCREEN_HEIGHT - exitButtonHeight) / 2) + 50; // Centered vertically
        exitButton = new ExitButton(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);

        //Background Sound
        new Thread(() -> {
            SoundManager sound = SoundManager.getInstance();
            sound.loadAllSound();
            try {
                Thread.sleep(500); // Đợi sound load xong
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            soundLoaded = true;
            sound.playBackgroundMusic(Constant.BACKGROUND_SOUND, true);
        }).start();
    }

    @Override
    public void update(double deltaTime) {
        // Check mouse click on start button
        if (MouseHandle.getInstance().isClickOn(startButton)) {
            SoundManager.getInstance().stopBackgroundMusic();
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
            return;
        }

        // Check mouse click on exit button
        if (MouseHandle.getInstance().isClickOn(exitButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            System.exit(0); // Thoát chương trình
            return;
        }

        // Change cursor type based on hover state
        if (MouseHandle.getInstance().isHoverOn(exitButton) || MouseHandle.getInstance().isHoverOn(startButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        AssetManager.getInstance().draw(g, Constant.LOGO_IMG, (SCREEN_WIDTH - 670) / 2, 60, 500, 156);
        startButton.render(g);
        exitButton.render(g);
    }
}
