package objects.ui.button;

import util.Constant;

import java.awt.*;

public class RestartButton extends Button {
    public RestartButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        util.AssetManager.getInstance().draw(g, Constant.RESTART_BUTTON_IMG, x, y, width, height);

    }
}
