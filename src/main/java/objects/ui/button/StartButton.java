package objects.ui.button;

import screen.LevelScreen;
import screen.ScreenManager;
import util.AssetManager;
import util.MouseHandle;

import java.awt.*;

public class StartButton extends Button {
    public StartButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isClickOn(this)) {
            System.out.println("Start Button Clicked!");
            ScreenManager.getInstance().switchScreen(new LevelScreen());
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(AssetManager.getInstance().getDefaultFont());
        g.setColor(Color.WHITE);
        g.drawString("START", x, y + height);
    }
}
