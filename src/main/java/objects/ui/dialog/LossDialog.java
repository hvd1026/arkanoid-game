package objects.ui.dialog;

import util.AssetManager;
import util.Constant;

import java.awt.*;

public class LossDialog extends Dialog {
    public LossDialog(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, Constant.LOSS_DIALOG_IMG, x, y, width, height);
    }
}
