package screen;

public class GameScreen extends Screen {
    @Override
    public void update(double deltaTime) {
        System.out.println("GameScreen Update");
    }

    @Override
    public void render() {
        System.out.println("GameScreen Render");
    }
}
