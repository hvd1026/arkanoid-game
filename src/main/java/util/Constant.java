package util;

public class Constant {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FPS = 60;
    public static final String TITLE = "Arkanoid Game";
    public static final int GAME_Y_OFFSET = 60; // padding top

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
    public static final int ZERO_STAR_LEVEL_IMG = 8;
    public static final int ONE_STAR_LEVEL_IMG = 9;
    public static final int TWO_STARS_LEVEL_IMG = 10;
    public static final int THREE_STARS_LEVEL_IMG = 11;
    public static final int STAR_IMG = 12;
    public static final int STAR_SIZE = 20;
    public static final int LOSS_IMG = 13;
    public static final int MENU_END_GAME_IMG = 14;
    public static final int LEVELS_IMG = 15;
    public static final int TRY_AGAIN_IMG = 16;

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

    // PowerUp asset ids (placeholder)
    public static final int POWERUP_EXPAND_IMG = 1001;
    public static final int POWERUP_FAST_IMG = 1002;

    //SOUND
    public static final int BACKGROUND_SOUND = -100;
    public static final int LOST_SOUND = -200;
    public static final int NORMAL_BRICK_SOUND = -300;
    public static final int STRONG_BRICK_SOUND = -400;
    public static final int PADDLE_SOUND = -500;
    public static final int POWERUP_SOUND = -600;
    public static final int WIN_SOUND = -700;






}
