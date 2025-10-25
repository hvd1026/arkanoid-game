package screen;

import util.LevelData;

import java.io.*;

public class WinScreen extends Screen {
    private int level;
    private int star;
    private LevelData levelData;

    public WinScreen(int level, int star) {
        this.level = level;
        this.star = star;
        loadLevelData();
        if (levelData.getLevels()[level] < star) {
            levelData.getLevels()[level] = star;
            saveLevelData();
        }
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
        System.out.printf("Update win screen: level %d, star %d\n", level, star);
    }

    @Override
    public void render(java.awt.Graphics2D g) {
        System.out.println("Render win screen");
    }
}
