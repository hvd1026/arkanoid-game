package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetManager {
    private BufferedImage spriteSheet;
    private static AssetManager instance = null;

    private AssetManager() {
        loadSpriteSheet();
    }

    public void draw(Graphics2D g, int id, int x, int y, int width, int height) {
        switch (id) {
            case Constant.NORMAL_BRICK_IMG_1:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 0, 59, 30, null);
                break;
            default:
                System.out.println("Asset not found: " + id);
                break;
        }

    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/sprites.png"));
        } catch (IOException e) {
            System.out.println("Can't load img");
            e.printStackTrace();
        }
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }
}
