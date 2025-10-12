package util;

public class Constant {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FPS = 60;
    public static final String TITLE = "Arkanoid Game";

    // Screen
    public static final int MENU_SCREEN = 0;
    public static final int GAME_SCREEN = 1;
    public static final int GAME_OVER_SCREEN = 2;

    // Paddle
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_Y_OFFSET = 15; // khoang cach tu bottom den paddle
    public static final float PADDLE_SPEED = 300;

    // Ball
    public static final int BALL_RADIUS = 10;
    public static final float BALL_SPEED = 300;
    public static final float BALL_MAX_ANGLE = 60f; // goc pxa lon nhat
}
