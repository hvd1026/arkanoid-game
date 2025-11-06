package screen;

import objects.ui.button.MenuButton;
import objects.ui.button.ShowLevelsButton;
import objects.ui.button.TryAgainButton;
import objects.ui.dialog.LossDialog;
import util.AssetManager;
import util.Constant;
import util.MouseHandle;

import java.awt.*;

import static util.Constant.SCREEN_WIDTH;

public class LossScreen extends Screen {
    private final int level;
    private final TryAgainButton tryAgainButton;
    private final ShowLevelsButton showLevelsButton;
    private final MenuButton menuButton;
    private final LossDialog lossDialog;

    public LossScreen(int level) {
        this.level = level;
        // Dialog
        int dialogX = (Constant.SCREEN_WIDTH - Constant.DIALOG_WIDTH) / 2;
        int dialogY = Constant.SCREEN_HEIGHT - Constant.DIALOG_HEIGHT - Constant.PADDING_DIALOG_BOTTOM;
        lossDialog = new LossDialog(dialogX, dialogY, Constant.DIALOG_WIDTH, Constant.DIALOG_HEIGHT);

        // Try Again Button
        tryAgainButton = new TryAgainButton(
                (Constant.SCREEN_WIDTH - Constant.TRY_AGAIN_BUTTON_WIDTH) / 2,
                Constant.SCREEN_HEIGHT - Constant.TRY_AGAIN_BUTTON_HEIGHT - Constant.PADDING_DIALOG_BOTTOM - 25,
                Constant.TRY_AGAIN_BUTTON_WIDTH, Constant.TRY_AGAIN_BUTTON_HEIGHT);
        int tryAgainButtonPaddingX = (Constant.DIALOG_WIDTH - Constant.TRY_AGAIN_BUTTON_WIDTH) / 2;
        // Show Levels Button
        showLevelsButton = new ShowLevelsButton(
                dialogX + Constant.DIALOG_WIDTH - tryAgainButtonPaddingX - Constant.SHOW_LEVELS_BUTTON_WIDTH,
                dialogY + 50,
                Constant.SHOW_LEVELS_BUTTON_WIDTH, Constant.SHOW_LEVELS_BUTTON_HEIGHT);
        // Menu Button
        menuButton = new MenuButton(
                dialogX + tryAgainButtonPaddingX,
                dialogY + 50,
                Constant.MENU_BUTTON_WIDTH, Constant.MENU_BUTTON_HEIGHT);

    }

    @Override
    public void update(double deltaTime) {
        // check hover on buttons
        boolean isHoverOnSomeButton = false;
        if (MouseHandle.getInstance().isHoverOn(menuButton)
                || MouseHandle.getInstance().isHoverOn(showLevelsButton)
                || MouseHandle.getInstance().isHoverOn(tryAgainButton)) {
            isHoverOnSomeButton = true;
        }

        // change cursor type
        if (isHoverOnSomeButton) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }
        
        // check click on buttons
        if (MouseHandle.getInstance().isClickOn(menuButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new MenuScreen());
        } else if (MouseHandle.getInstance().isClickOn(showLevelsButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
        } else if (MouseHandle.getInstance().isClickOn(tryAgainButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new GameScreen(level));
        }


    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        AssetManager.getInstance().draw(g,
                Constant.LOGO_IMG,
                (SCREEN_WIDTH - 670) / 2,
                60,
                500,
                156);

        lossDialog.render(g);
        tryAgainButton.render(g);
        showLevelsButton.render(g);
        menuButton.render(g);
    }
}
