package screen;

import objects.ui.button.Button;
import objects.ui.button.StartButton;
import util.AssetManager;
import util.MouseHandle;

import java.awt.*;

import static util.Constant.SCREEN_HEIGHT;
import static util.Constant.SCREEN_WIDTH;


public class MenuScreen extends Screen {
    private Button startButton;

    public MenuScreen() {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(AssetManager.getInstance().getDefaultFont());
        int startButtonWidth = fm.stringWidth("START");
        int startButtonHeight = fm.getHeight();
        int startButtonX = (SCREEN_WIDTH - startButtonWidth) / 2; // Centered horizontally
        int startButtonY = (SCREEN_HEIGHT - startButtonHeight) * 2 / 3; // start at 2/3 of the screen height
        startButton = new StartButton(startButtonX, startButtonY, startButtonWidth, startButtonHeight);
    }

    @Override
    public void update(double deltaTime) {

        if (MouseHandle.getInstance().isClickOn(startButton)) {
            System.out.println("Start Button Clicked!");
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
            return;
        }

        if (MouseHandle.getInstance().isHoverOn(startButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        startButton.render(g);
    }
}
