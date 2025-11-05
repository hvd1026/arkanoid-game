package objects.ui.button;

public class TryAgainButton extends Button {
    public TryAgainButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(java.awt.Graphics2D g) {
        util.AssetManager.getInstance().draw(g, util.Constant.TRY_AGAIN_BUTTON_IMG, x, y, width, height);
    }
}
