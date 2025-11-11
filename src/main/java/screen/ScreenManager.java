package screen;

/**
 * ScreenManager class manages the current screen of the game using the singleton pattern.
 */

public class ScreenManager {
    private static ScreenManager instance;
    private Screen currentScreen;

    private ScreenManager() {
        currentScreen = null;
    }
    // Get the singleton instance of ScreenManager
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }
    // Switch to a new screen
    public void switchScreen(Screen newScreen) {
        currentScreen = newScreen;
    }
    // Get the current screen
    public Screen getCurrentScreen() {
        return currentScreen;
    }
}
