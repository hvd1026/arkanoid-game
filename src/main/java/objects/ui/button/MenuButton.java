package objects.ui.button;

import util.AssetManager;
import util.Constant;

import java.awt.*;

public class MenuButton extends Button {
    public MenuButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, Constant.MENU_BUTTON_IMG,
                x, y, width, height);
    }
}
