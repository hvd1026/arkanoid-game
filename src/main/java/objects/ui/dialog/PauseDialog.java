package objects.ui.dialog;

import util.AssetManager;
import util.Constant;

import java.awt.*;

/**
 * PauseDialog class represents the dialog shown when the game is paused.
 */

public class PauseDialog extends Dialog {
    public PauseDialog(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, Constant.PAUSE_DIALOG_IMG, x, y, width, height);
    }
}
