package screen;

import objects.ui.button.*;
import objects.ui.dialog.PauseDialog;
import util.Constant;
import util.MouseHandle;

import java.awt.*;

public class PauseScreen extends Screen {
    Screen previousScreen;
    private final PauseDialog pauseDialog;
    private final ResumeButton resumeButton;
    private final MenuButton menuButton;
    private final RestartButton restartButton;


    public PauseScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
        // Dialog
        int dialogX = (Constant.SCREEN_WIDTH - Constant.DIALOG_WIDTH) / 2;
        int dialogY = Constant.SCREEN_HEIGHT - Constant.DIALOG_HEIGHT - Constant.PADDING_DIALOG_BOTTOM;
        pauseDialog = new PauseDialog(dialogX, dialogY, Constant.DIALOG_WIDTH, Constant.DIALOG_HEIGHT);

        // Resume Button
        resumeButton = new ResumeButton(
                (Constant.SCREEN_WIDTH - Constant.RESUME_BUTTON_WIDTH) / 2,
                Constant.SCREEN_HEIGHT - Constant.RESUME_BUTTON_HEIGHT - Constant.PADDING_DIALOG_BOTTOM - 25,
                Constant.RESUME_BUTTON_WIDTH, Constant.RESUME_BUTTON_HEIGHT);
        int resumeButtonPaddingX = (Constant.DIALOG_WIDTH - Constant.RESUME_BUTTON_WIDTH) / 2;

        // Menu Button
        menuButton = new MenuButton(
                dialogX + resumeButtonPaddingX,
                dialogY + 50,
                Constant.MENU_BUTTON_WIDTH, Constant.MENU_BUTTON_HEIGHT);

        // restart button
        restartButton = new RestartButton(
                dialogX + Constant.DIALOG_WIDTH - resumeButtonPaddingX - Constant.SHOW_LEVELS_BUTTON_WIDTH,
                dialogY + 50,
                Constant.RESTART_BUTTON_WIDTH, Constant.RESTART_BUTTON_HEIGHT);
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isHoverOn(menuButton)
                || MouseHandle.getInstance().isHoverOn(restartButton)
                || MouseHandle.getInstance().isHoverOn(resumeButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }

        if (MouseHandle.getInstance().isClickOn(resumeButton)) {
            ScreenManager.getInstance().switchScreen(previousScreen);
        } else if (MouseHandle.getInstance().isClickOn(menuButton)) {
            ScreenManager.getInstance().switchScreen(new MenuScreen());
        } else if (MouseHandle.getInstance().isClickOn(restartButton)) {
            if (previousScreen instanceof GameScreen gameScreen) {
                int level = gameScreen.getLevel();
                ScreenManager.getInstance().switchScreen(new GameScreen(level));
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        previousScreen.render(g);
        // Overlay
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        // component
        pauseDialog.render(g);
        resumeButton.render(g);
        menuButton.render(g);
        restartButton.render(g);
    }
}
