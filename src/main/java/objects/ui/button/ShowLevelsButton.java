package objects.ui.button;

public class ShowLevelsButton extends Button {
    public ShowLevelsButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(java.awt.Graphics2D g) {
        util.AssetManager.getInstance().draw(g, util.Constant.SHOW_LEVELS_BUTTON_IMG, x, y, width, height);
    }
}
