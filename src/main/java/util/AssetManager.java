package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetManager {
    private BufferedImage spriteSheet;
    private BufferedImage backgroundImage;
    private static AssetManager instance = null;
    public Font deffaultFont = new Font("Arial", Font.BOLD, 25);

    private AssetManager() {
        loadSpriteSheet();
        loadBackgroundImage();
    }

    public Font getDefaultFont() {
        return deffaultFont;
    }


    public void draw(Graphics2D g, int id, int x, int y, int width, int height) {
        switch (id) {
            case Constant.PADDLE_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 35, 120, 55, null);
                break;
            case Constant.BALL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 122, 32, 149, 57, null);
                break;
            case Constant.NORMAL_BRICK_IMG_1:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 0, 58, 30, null);
                break;
            case Constant.NORMAL_BRICK_IMG_2:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 61, 0, 120, 30, null);
                break;
            case Constant.NORMAL_BRICK_IMG_3:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 123, 0, 182, 30, null);
                break;
            case Constant.NORMAL_BRICK_IMG_4:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 185, 0, 244, 30, null);
                break;
            case Constant.NORMAL_BRICK_IMG_5:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 247, 0, 306, 30, null);
                break;
            case Constant.NORMAL_BRICK_IMG_6:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 309, 0, 368, 30, null);
                break;
            case Constant.STRONG_BRICK_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 371, 0, 430, 30, null);
                break;
            case Constant.ZERO_STAR_LEVEL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 296, 120, 395, 203, null);
                break;
            case Constant.ONE_STAR_LEVEL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 297, 206, 396, 290, null);
                break;
            case Constant.TWO_STARS_LEVEL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 299, 292, 398, 375, null);
                break;
            case Constant.THREE_STARS_LEVEL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 300, 379, 399, 462, null);
                break;
            case Constant.STAR_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 150, 32, 179, 59, null);
                break;
            case Constant.POWERUP_EXPAND_IMG:
                g.setColor(Color.YELLOW);
                g.fillOval(x, y, width, height);
                break;
            case Constant.POWERUP_FAST_IMG:
                g.setColor(Color.CYAN);
                g.fillRect(x, y, width, height);
                break;
            default:
                System.out.println("Asset not found: " + id);
                break;
        }
    }

    public void drawBackground(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, 0, 0, 800, 600, null);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/sprites.png"));
        } catch (IOException e) {
            System.out.println("Can't load img");
            e.printStackTrace();
        }
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background.png"));
        } catch (IOException e) {
            System.out.println("Can't load background img");
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
