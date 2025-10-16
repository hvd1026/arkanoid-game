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
            case Constant.PADDLE_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 35, 120, 55, null);
                break;
            case Constant.BALL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 122, 32, 149, 57, null);
                break;
            case Constant.NORMAL_BRICK_IMG_1:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 0, 59, 30, null);
                break;
//            case Constant.NORMAL_BRICK_IMG_2:
//                g.drawImage(spriteSheet, x, y, x + width, y + height, 60, 0, 120, 30, null);
//                break;
//            case Constant.NORMAL_BRICK_IMG_3:
//                g.drawImage(spriteSheet, x, y, x + width, y + height, 123, 0, 183, 30, null);
//                break;
//            case Constant.NORMAL_BRICK_IMG_4:
//                g.drawImage(spriteSheet, x, y, x + width, y + height, 186, 0, 246, 30, null);
//                break;
//            case Constant.NORMAL_BRICK_IMG_5:
//                g.drawImage(spriteSheet, x, y, x + width, y + height, 250, 0, 310 , 30, null);
//                break;
//            case Constant.NORMAL_BRICK_IMG_6:
//                g.drawImage(spriteSheet, x, y, x + width, y + height, 313, 0, 373 , 30, null);
//                break;
            case Constant.STRONG_BRICK_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 370, 0, 430, 30, null);
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
