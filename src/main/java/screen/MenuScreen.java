package screen;

public class MenuScreen extends Screen {

    @Override
    public void update(double deltaTime) {
        System.out.println("MenuScreen Update");
    }

    @Override
    public void render() {
        System.out.println("MenuScreen Render");
    }
}
