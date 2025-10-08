package screen;

import util.Constant;

public class ScreenFactory {
    public static Screen createScreen(int screenType) {
        switch (screenType) {
            case Constant.MENU_SCREEN:
                return new MenuScreen();
            case Constant.GAME_SCREEN:
                return new GameScreen();
            case Constant.GAME_OVER_SCREEN:
                return new GameOverScreen();
            default:
                throw new IllegalArgumentException("Invalid screen type: " + screenType);
        }
    }
}
