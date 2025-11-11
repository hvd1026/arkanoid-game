package util;

public interface Constant {
    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 600;
    int FPS = 60;
    String TITLE = "Arkanoid Game";
    int GAME_Y_OFFSET = 60; // padding top
    double BALL_START_ANGLE_DEG = 60;
    long CLICK_DELAY_MS = 300; // delay between clicks

    // Paddle
    int PADDLE_WIDTH = 80;
    int PADDLE_HEIGHT = 15;
    int PADDLE_Y_OFFSET = 15; // distance from bottom
    float PADDLE_SPEED = 300;

    // Ball
    int BALL_RADIUS = 10;
    float BALL_SPEED = 300;
    float BALL_MAX_ANGLE = 60f; // max angle when ball bounces off paddle
    float MULTI_BALL_SPREAD_DEG = 12f; // angle between balls in multi-ball powerup

    // Brick.
    int STRONG_BRICK = -1;
    int BRICK_WIDTH = 50;
    int BRICK_HEIGHT = 25;
    int BRICK_ROWS = 16;
    int BRICK_COLUMNS = 16;

    // Assets
    int PADDLE_IMG = 0;
    int STRONG_BRICK_IMG = -1;
    int NORMAL_BRICK_IMG_1 = 1;
    int NORMAL_BRICK_IMG_2 = 2;
    int NORMAL_BRICK_IMG_3 = 3;
    int NORMAL_BRICK_IMG_4 = 4;
    int NORMAL_BRICK_IMG_5 = 5;
    int NORMAL_BRICK_IMG_6 = 6;
    int BALL_IMG = 7;
    int ZERO_STAR_LEVEL_IMG = 8;
    int ONE_STAR_LEVEL_IMG = 9;
    int TWO_STARS_LEVEL_IMG = 10;
    int THREE_STARS_LEVEL_IMG = 11;
    int STAR_IMG = 12;
    int LOSS_DIALOG_IMG = 13;
    int TRY_AGAIN_BUTTON_IMG = 14;
    int SHOW_LEVELS_BUTTON_IMG = 15;
    int MENU_BUTTON_IMG = 16;
    int WIN_DIALOG_IMG = 17;
    int NEXT_LEVEL_BUTTON_IMG = 18;
    int LOGO_IMG = 19;
    int PAUSE_DIALOG_IMG = 20;
    int RESUME_BUTTON_IMG = 21;
    int RESTART_BUTTON_IMG = 22;
    int AUDIO_IMG = 23;
    int MUSIC_IMG = 24;
    int AUDIO_MUTED_IMG = 25;
    int MUSIC_MUTED_IMG = 26;
    // stars
    int STAR_SIZE = 20;

    // Level screen
    int TOTAL_LEVELS = 12;
    int LEVEL_COLS = 4;
    int LEVEL_ROWS = 3;
    int LEVEL_BUTTON_WIDTH = 100;
    int LEVEL_BUTTON_HEIGHT = 85;
    int PADDING_X_LEFT = 110;
    int PADDING_Y_TOP = 180;
    int PADDING_X_BETWEEN = 60;
    int PADDING_Y_BETWEEN = 40;
    int LEVEL_TITLE_Y_OFFSET = 100;

    // PowerUp config
    float POWERUP_DROP_RATE = 0.3f;
    float POWERUP_FALL_SPEED = 120f;
    int POWERUP_DURATION_MS_EXPAND = 8000;
    int POWERUP_DURATION_MS_FASTBALL = 6000;
    int POWERUP_DURATION_MS_DOUBLE_DAMAGE = 8000;
    int POWERUP_WIDTH = 51;
    int POWERUP_HEIGHT = 20;
    float SPEED_SCALE = 1.4f;

    // PowerUp asset ids
    int POWERUP_EXPAND_IMG = 1001;
    int POWERUP_FAST_IMG = 1002;
    int POWERUP_MULTIBALL_IMG = 1003;
    int POWERUP_DOUBLE_DAMAGE_IMG = 1004;

    // Dialog
    int PADDING_DIALOG_BOTTOM = 100;
    int DIALOG_WIDTH = 290;
    int DIALOG_HEIGHT = 190;

    // Buttons
    int TRY_AGAIN_BUTTON_WIDTH = 255;
    int TRY_AGAIN_BUTTON_HEIGHT = 50;
    int NEXT_LEVEL_BUTTON_WIDTH = 255;
    int NEXT_LEVEL_BUTTON_HEIGHT = 50;
    int RESUME_BUTTON_WIDTH = 255;
    int RESUME_BUTTON_HEIGHT = 50;
    int SHOW_LEVELS_BUTTON_WIDTH = 123;
    int SHOW_LEVELS_BUTTON_HEIGHT = 47;
    int MENU_BUTTON_WIDTH = 123;
    int MENU_BUTTON_HEIGHT = 47;
    int RESTART_BUTTON_WIDTH = 123;
    int RESTART_BUTTON_HEIGHT = 47;
}
