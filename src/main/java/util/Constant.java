package util;

public class Constant {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FPS = 60;
    public static final String TITLE = "Arkanoid Game";
    public static final int GAME_Y_OFFSET = 50; // padding top

    // Paddle
    public static final int PADDLE_WIDTH = 80;
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_Y_OFFSET = 15; // khoang cach tu bottom den paddle
    public static final float PADDLE_SPEED = 300;

    // Ball
    public static final int BALL_RADIUS = 10;
    public static final float BALL_SPEED = 300;
    public static final float BALL_MAX_ANGLE = 60f; // goc pxa lon nhat

    // Brick.
    public static final int NORMAL_BRICK = 1;
    public static final int STRONG_BRICK = -1;
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 25;
    public static final int BRICK_ROWS = 16;
    public static final int BRICK_COLUMNS = 16;

    // Assets
    public static final int PADDLE_IMG = 0;
    public static final int STRONG_BRICK_IMG = -1;
    public static final int NORMAL_BRICK_IMG_1 = 1;
    public static final int NORMAL_BRICK_IMG_2 = 2;
    public static final int NORMAL_BRICK_IMG_3 = 3;
    public static final int NORMAL_BRICK_IMG_4 = 4;
    public static final int NORMAL_BRICK_IMG_5 = 5;
    public static final int NORMAL_BRICK_IMG_6 = 6;
    public static final int BALL_IMG = 7;
}
