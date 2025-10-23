package screen;

import objects.ui.button.LevelButton;
import util.AssetManager;
import util.LevelData;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import static util.Constant.*;

public class LevelScreen extends Screen {
    private LevelData levelData;
    private int[] levels;
    private LevelButton[] levelButtons;
    private Font font = new Font("Arial", Font.BOLD, 30);

    public LevelScreen() {
        levelData = new LevelData(); // Initialize with default or empty LevelData
        loadLevelData();
    }

    private void loadLevelData() {
        String filePath = "src/main/resources/maps/level.data"; // Relative path from project root
        File levelFile = new File(filePath);


        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(levelFile));
            levelData = (LevelData) ois.readObject();
            System.out.println("Level data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading level data: " + e.getMessage());
            levelData = new LevelData(); // Re-initialize if loading failed
            saveLevelData();
        }
        levels = levelData.getLevels();
        levelButtons = new LevelButton[TOTAL_LEVELS + 1];
        for (int i = 1; i <= LEVEL_ROWS; i++) {
            for (int j = 1; j <= LEVEL_COLS; j++) {
                int levelIndex = (i - 1) * LEVEL_COLS + j;
                if (levelIndex > TOTAL_LEVELS) {
                    break;
                }
                int x = PADDING_X_LEFT + (j - 1) * (LEVEL_BUTTON_WIDTH + PADDING_X_BETWEEN);
                int y = PADDING_Y_TOP + (i - 1) * (LEVEL_BUTTON_HEIGHT + PADDING_Y_BETWEEN);
                levelButtons[levelIndex] = new LevelButton(x, y, LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT, levelIndex, levels[levelIndex]);
            }
        }
    }

    private void saveLevelData() {
        String filePath = "src/main/resources/maps/level.data";
        File levelFile = new File(filePath);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(levelFile));
            oos.writeObject(levelData);
        } catch (IOException e) {
            System.err.println("Error saving level data: " + e.getMessage());
        }
    }

    @Override
    public void update(double deltaTime) {
        for (int i = 1; i <= TOTAL_LEVELS; i++) {
            levelButtons[i].update(deltaTime);
        }
    }

    @Override
    public void render(java.awt.Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        g.setColor(Color.WHITE);
        g.setFont(font);
        FontMetrics fmt = g.getFontMetrics(font);
        int titleWidth = fmt.stringWidth("Select Level");

        g.drawString("Select Level", (SCREEN_WIDTH - titleWidth) / 2, LEVEL_TITLE_Y_OFFSET);

        for (int i = 1; i <= TOTAL_LEVELS; i++) {
            levelButtons[i].render(g);
        }
    }

}
