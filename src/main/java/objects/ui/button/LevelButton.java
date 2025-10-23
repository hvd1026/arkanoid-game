package objects.ui.button;

import screen.GameScreen;
import screen.ScreenManager;
import util.AssetManager;
import util.MouseHandle;

import java.awt.*;

public class LevelButton extends Button {
    private int levelNumber;
    private int star;

    public LevelButton(int x, int y, int width, int height, int levelNumber, int star) {
        super(x, y, width, height);
        this.levelNumber = levelNumber;
        this.star = star;
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isClickOn(this)) {
            System.out.println("Selected level " + levelNumber);
            ScreenManager.getInstance().switchScreen(new GameScreen(levelNumber));
        }
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, star + 8, x, y, width, height); // 0 star id = 8, 1 star id = 9, 2 stars id = 10, 3 stars id = 11
        g.setColor(Color.WHITE);
        g.setFont(AssetManager.getInstance().getDefaultFont());
        String levelText = (levelNumber < 10) ? "0" + levelNumber : Integer.toString(levelNumber);
        FontMetrics fm = g.getFontMetrics(AssetManager.getInstance().getDefaultFont());
        int textWidth = fm.stringWidth(levelText);
        int textHeight = fm.getAscent();
        g.drawString(levelText, x + (width - textWidth) / 2, y + (height + textHeight) / 2 - 5);
    }
}
