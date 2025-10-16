package screen;

public class WinScreen extends Screen {
    private int level;
    private int star;

    public WinScreen(int level, int star) {
        this.level = level;
        this.star = star;
    }


    @Override
    public void update(double deltaTime) {
        System.out.printf("Update win screen: level %d, star %d\n", level, star);
    }

    @Override
    public void render(java.awt.Graphics2D g) {
        System.out.println("Render win screen");
    }
}
