package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * AssetManager is a singleton class responsible for loading and rendering game assets.
 * - Load sprite sheets images and provides methods to draw them on the screen.
 * - Load background image and provides method to draw it on the screen.
 * - Defines a default font for the game.
 */
public class AssetManager {
    private BufferedImage spriteSheet;
    private BufferedImage backgroundImage;
    private BufferedImage logo;
    private BufferedImage gameGuideImage;
    private static AssetManager instance = null;
    public Font deffaultFont;

    private AssetManager() {
        deffaultFont = new Font("Arial", Font.BOLD, 25);
        loadSpriteSheet();
        loadBackgroundImage();
        loadLogo();
        loadGameGuideImage();
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public Font getDefaultFont() {
        return deffaultFont;
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background.png")));
        } catch (IOException e) {
            System.err.println("Can't load background image");
            e.printStackTrace();
        }
    }

    public void drawBackground(Graphics2D g) {
        // draw background at (0,0) with size of screen, crop image to fit screen size
        g.drawImage(backgroundImage, 0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, 0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, null);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites.png")));
        } catch (IOException e) {
            System.err.println("Can't load sprite sheet");
            e.printStackTrace();
        }
    }

    private void loadLogo() {
        try {
            logo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/logo.png")));
        } catch (IOException e) {
            System.err.println("Can't logo image");
            e.printStackTrace();
        }
    }

    private void loadGameGuideImage() {
        try {
            gameGuideImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/guide.png")));
        } catch (IOException e) {
            System.err.println("Can't load game guide image");
            e.printStackTrace();
        }
    }

    public void drawGameGuide(Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(gameGuideImage, x, y, x + width, y + height, 0, 0, gameGuideImage.getWidth(), gameGuideImage.getHeight(), null);
    }

    /**
     * Draws image from sprite sheet to screen based on id.
     *
     * @param g      Graphics2D object
     * @param id     id of sprite to draw
     * @param x      position x on screen
     * @param y      position y on screen
     * @param width  width on screen
     * @param height height on screen
     */
    public void draw(Graphics2D g, int id, int x, int y, int width, int height) {
        /* Structure for adding new assets:
         * case ID:
         *    g.drawImage(spriteSheet, x, y, x + width, y + height, srcX1, srcY1, srcX2, srcY2, null);
         *    break;
         * Where:
         *  ID: a constant defined in util.Constant.java
         *  srcX1, srcY1: top-left corner of the asset in the sprite sheet
         *  srcX2, srcY2: bottom-right corner of the asset in the
         */
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
                g.drawImage(spriteSheet, x, y, x + width, y + height, 316, 32, 367, 52, null);
                break;
            case Constant.POWERUP_FAST_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 369, 32, 420, 52, null);
                break;
            case Constant.POWERUP_DOUBLE_DAMAGE_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 423, 32, 474, 52, null);
                break;
            case Constant.POWERUP_MULTIBALL_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 477, 32, 528, 52, null);
                break;
            case Constant.LOSS_DIALOG_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 120, 290, 310, null);
                break;
            case Constant.TRY_AGAIN_BUTTON_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 512, 255, 560, null);
                break;
            case Constant.SHOW_LEVELS_BUTTON_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 1, 69, 124, 116, null);
                break;
            case Constant.MENU_BUTTON_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 129, 69, 252, 116, null);
                break;
            case Constant.WIN_DIALOG_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 0, 314, 296, 505, null);
                break;
            case Constant.NEXT_LEVEL_BUTTON_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 262, 512, 517, 560, null);
                break;
            case Constant.LOGO_IMG:
                g.drawImage(logo, x, y, x + width + 200, y + height, 0, 0, 1200, 375, null);
                break;
            case Constant.PAUSE_DIALOG_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 403, 312, 697, 503, null);
                break;
            case Constant.RESUME_BUTTON_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 409, 257, 664, 305, null);
                break;
            case Constant.RESTART_BUTTON_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 255, 69, 378, 116, null);
                break;
            case Constant.AUDIO_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 215, 35, 247, 61, null);
                break;
            case Constant.MUSIC_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 184, 34, 211, 62, null);
                break;
            case Constant.AUDIO_MUTED_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 249, 35, 281, 61, null);
                break;
            case Constant.MUSIC_MUTED_IMG:
                g.drawImage(spriteSheet, x, y, x + width, y + height, 284, 33, 314, 62, null);
                break;
            default:
                System.err.println("Asset not found: " + id);
                break;
        }
    }
}
