package screen;

import objects.ui.button.MenuButton;
import objects.ui.button.NextLevelButton;
import objects.ui.button.ShowLevelsButton;
import objects.ui.dialog.WinDialog;
import util.AssetManager;
import util.Constant;
import util.LevelData;
import util.MouseHandle;

import java.awt.*;
import java.io.*;

public class WinScreen extends Screen {
    private final int level;
    private final NextLevelButton nextLevelButton;
    private final ShowLevelsButton showLevelsButton;
    private final MenuButton menuButton;
    private final WinDialog winDialog;
    private LevelData levelData;

    public WinScreen(int level, int star) {
        this.level = level;
        loadLevelData();
        if (levelData.getLevels()[level] < star) {
            levelData.getLevels()[level] = star;
            saveLevelData();
        }

        // Dialog
        int dialogX = (Constant.SCREEN_WIDTH - Constant.DIALOG_WIDTH) / 2;
        int dialogY = Constant.SCREEN_HEIGHT - Constant.DIALOG_HEIGHT - Constant.PADDING_DIALOG_BOTTOM;
        winDialog = new WinDialog(dialogX, dialogY, Constant.DIALOG_WIDTH, Constant.DIALOG_HEIGHT);

        // Next level Button
        nextLevelButton = new NextLevelButton(
                (Constant.SCREEN_WIDTH - Constant.NEXT_LEVEL_BUTTON_WIDTH) / 2,
                Constant.SCREEN_HEIGHT - Constant.NEXT_LEVEL_BUTTON_HEIGHT - Constant.PADDING_DIALOG_BOTTOM - 25,
                Constant.NEXT_LEVEL_BUTTON_WIDTH, Constant.NEXT_LEVEL_BUTTON_HEIGHT);
        int nextLevelButtonPaddingX = (Constant.DIALOG_WIDTH - Constant.NEXT_LEVEL_BUTTON_WIDTH) / 2;
        // Show Levels Button
        showLevelsButton = new ShowLevelsButton(
                dialogX + Constant.DIALOG_WIDTH - nextLevelButtonPaddingX - Constant.SHOW_LEVELS_BUTTON_WIDTH,
                dialogY + 50,
                Constant.SHOW_LEVELS_BUTTON_WIDTH, Constant.SHOW_LEVELS_BUTTON_HEIGHT);
        // Menu Button
        menuButton = new MenuButton(
                dialogX + nextLevelButtonPaddingX,
                dialogY + 50,
                Constant.MENU_BUTTON_WIDTH, Constant.MENU_BUTTON_HEIGHT);
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
        // check hover on buttons
        boolean isHoverOnSomeButton = false;
        if (MouseHandle.getInstance().isHoverOn(menuButton)
                || MouseHandle.getInstance().isHoverOn(showLevelsButton)
                || MouseHandle.getInstance().isHoverOn(nextLevelButton)) {
            isHoverOnSomeButton = true;
        }

        // change cursor type
        if (isHoverOnSomeButton) {
            MouseHandle.getInstance().changeToHandCursor();
        } else {
            MouseHandle.getInstance().changeToDefaultCursor();
        }

        // check click on buttons
        if (MouseHandle.getInstance().isClickOn(menuButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new MenuScreen());
        } else if (MouseHandle.getInstance().isClickOn(showLevelsButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            ScreenManager.getInstance().switchScreen(new LevelScreen());
        } else if (MouseHandle.getInstance().isClickOn(nextLevelButton)) {
            MouseHandle.getInstance().changeToDefaultCursor();
            if (level < Constant.TOTAL_LEVELS) {
                ScreenManager.getInstance().switchScreen(new GameScreen(level + 1));
            } else {
                // If at the last level, go to level selection screen
                ScreenManager.getInstance().switchScreen(new LevelScreen());
            }
        }

    }

    @Override
    public void render(java.awt.Graphics2D g) {
        AssetManager.getInstance().drawBackground(g);
        winDialog.render(g);
        nextLevelButton.render(g);
        showLevelsButton.render(g);
        menuButton.render(g);
    }
}
