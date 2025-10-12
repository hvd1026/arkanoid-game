package screen;

public class ScreenManager {
    private static ScreenManager instance;
    private Screen currentScreen;

    private ScreenManager() {
        currentScreen = null;
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void switchScreen(Screen newScreen) {
        currentScreen = newScreen;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }
}
