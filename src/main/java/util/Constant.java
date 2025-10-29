package util;

public class Constant {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FPS = 60;
    public static final String TITLE = "Arkanoid Game";
    public static final int GAME_Y_OFFSET = 60; // padding top
    public static final double BALL_START_ANGLE_DEG = 60;

    // Paddle
    public static final int PADDLE_WIDTH = 80;
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_Y_OFFSET = 15; // khoang cach tu bottom den paddle
    public static final float PADDLE_SPEED = 300;

    // Ball
    public static final int BALL_RADIUS = 10;
    public static final float BALL_SPEED = 300;
    public static final float BALL_MAX_ANGLE = 60f; // goc pxa lon nhat
    public static final float MULTI_BALL_SPREAD_DEG = 12f; // goc lech nho cho MultiBall

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
    public static final int ZERO_STAR_LEVEL_IMG = 8;
    public static final int ONE_STAR_LEVEL_IMG = 9;
    public static final int TWO_STARS_LEVEL_IMG = 10;
    public static final int THREE_STARS_LEVEL_IMG = 11;
    public static final int STAR_IMG = 12;
    public static final int LOSS_DIALOG_IMG = 13;
    public static final int TRY_AGAIN_BUTTON_IMG = 14;
    public static final int SHOW_LEVELS_BUTTON_IMG = 15;
    public static final int MENU_BUTTON_IMG = 16;
    public static final int WIN_DIALOG_IMG = 17;
    public static final int NEXT_LEVEL_BUTTON_IMG = 18;
    // stars
    public static final int STAR_SIZE = 20;

    // Level screen
    public static final int TOTAL_LEVELS = 12;
    public static final int LEVEL_COLS = 4;
    public static final int LEVEL_ROWS = 3;
    public static final int LEVEL_BUTTON_WIDTH = 100;
    public static final int LEVEL_BUTTON_HEIGHT = 85;
    public static final int PADDING_X_LEFT = 110;
    public static final int PADDING_Y_TOP = 180;
    public static final int PADDING_X_BETWEEN = 60;
    public static final int PADDING_Y_BETWEEN = 40;
    public static final int LEVEL_TITLE_Y_OFFSET = 100;

    // PowerUp config
    public static final float POWERUP_DROP_RATE = 0.5f; // 50% xác suất rơi
    public static final float POWERUP_FALL_SPEED = 120f; // px/s
    public static final int POWERUP_DURATION_MS_EXPAND = 8000;
    public static final int POWERUP_DURATION_MS_FASTBALL = 6000;
    public static final int POWERUP_DURATION_MS_DOUBLE_DAMAGE = 8000;

    // PowerUp asset ids (placeholder)
    public static final int POWERUP_EXPAND_IMG = 1001;
    public static final int POWERUP_FAST_IMG = 1002;
    public static final int POWERUP_MULTIBALL_IMG = 1003;
    public static final int POWERUP_DOUBLE_DAMAGE_IMG = 1004;

    // Dialog
    public static final int PADDING_DIALOG_BOTTOM = 100;
    public static final int DIALOG_WIDTH = 290;
    public static final int DIALOG_HEIGHT = 190;

    // Buttons
    public static final int TRY_AGAIN_BUTTON_WIDTH = 255;
    public static final int TRY_AGAIN_BUTTON_HEIGHT = 50;
    public static final int NEXT_LEVEL_BUTTON_WIDTH = 255;
    public static final int NEXT_LEVEL_BUTTON_HEIGHT = 50;
    public static final int SHOW_LEVELS_BUTTON_WIDTH = 123;
    public static final int SHOW_LEVELS_BUTTON_HEIGHT = 47;
    public static final int MENU_BUTTON_WIDTH = 123;
    public static final int MENU_BUTTON_HEIGHT = 47;

}
