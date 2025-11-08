package objects.ui.button;

import util.AssetManager;
import util.Constant;

import java.awt.*;

public class ResumeButton extends Button {

    public ResumeButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        util.AssetManager.getInstance().draw(g, Constant.RESUME_BUTTON_IMG, x, y, width, height);
    }
}
