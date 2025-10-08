package screen;

public abstract class Screen {
    public boolean isActive = true;

    public abstract void update(double deltaTime);

    public abstract void render();
}
