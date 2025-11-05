package objects.ui.button;

import util.Constant;

public class NextLevelButton extends Button {
    public NextLevelButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(java.awt.Graphics2D g) {
        util.AssetManager.getInstance().draw(g, Constant.NEXT_LEVEL_BUTTON_IMG, x, y, width, height);
    }
}
