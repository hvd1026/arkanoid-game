package screen;

import util.AssetManager;
import util.LevelData;
import objects.ui.button.Button;
import objects.ui.button.StartButton;
import util.AssetManager;
import util.MouseHandle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static util.Constant.SCREEN_HEIGHT;
import static util.Constant.SCREEN_WIDTH;

public class WinScreen extends Screen {
    private int level;
    private int star;
    private LevelData levelData;
    private final Button NextLevelButton;
    private final Button ReStartButton;
    private final Button MenuButton;

    public WinScreen(int level, int star) {
        this.level = level;
        this.star = star;
        loadLevelData();
        if (levelData.getLevels()[level] < star) {
            levelData.getLevels()[level] = star;
            saveLevelData();
        }

        //Click Buttons
        // Create a temporary image to get FontMetrics
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(AssetManager.getInstance().getDefaultFont());
        FontMetrics fm = g2d.getFontMetrics();
        // Calculate button size and position of the menu & try_again button
        int MenuButtonWidth = fm.stringWidth("                 ");
        int MenuButtonHeight = fm.getHeight() + 30;
        int MenuButtonX = (SCREEN_WIDTH - MenuButtonWidth) / 2; // Centered horizontally
        int MenuButtonY = (SCREEN_HEIGHT - MenuButtonHeight) / 2 ; // Centered vertically
        MenuButton = new StartButton(MenuButtonX, MenuButtonY, MenuButtonWidth, MenuButtonHeight);

//      //NEXT LEVEL
        int NextLevelButtonWidth = fm.stringWidth("               ");
        int NextLevelButtonHeight = fm.getHeight() + 30;
        int NextLevelButtonX = (SCREEN_WIDTH - NextLevelButtonWidth) / 2; // Centered horizontally
        int NextLevelButtonY = (SCREEN_HEIGHT - NextLevelButtonHeight) / 2 + 70; // Centered vertically
        NextLevelButton = new StartButton(NextLevelButtonX, NextLevelButtonY, NextLevelButtonWidth, NextLevelButtonHeight);

        //RESTART
        int RestartButtonWidth = fm.stringWidth("               ");
        int RestartButtonHeight = fm.getHeight() + 30;
        int RestartButtonX = (SCREEN_WIDTH - RestartButtonWidth) / 2; // Centered horizontally
        int RestartButtonY = (SCREEN_HEIGHT - RestartButtonHeight) / 2 + 150; // Centered vertically
        ReStartButton = new StartButton(RestartButtonX, RestartButtonY, RestartButtonWidth, RestartButtonHeight);
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
        if (MouseHandle.getInstance().isClickOn(MenuButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
            return;
        }

        if (MouseHandle.getInstance().isClickOn(NextLevelButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen( new GameScreen(level + 1));
            return;
        }

        if (MouseHandle.getInstance().isClickOn(ReStartButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen( new GameScreen(level));
            return;
        }

        // Change cursor if hovering over button
        if (MouseHandle.getInstance().isHoverOn(MenuButton)
                || MouseHandle.getInstance().isHoverOn(ReStartButton)
                ||  MouseHandle.getInstance().isHoverOn(NextLevelButton)) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }

    }

    @Override
    public void render(java.awt.Graphics2D g) {
        AssetManager.getInstance().drawWinScreen(g);
        System.out.println("Render win screen");
    }
}
