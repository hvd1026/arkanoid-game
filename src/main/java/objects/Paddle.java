package objects;

public class Paddle extends MovableObject {
    public Paddle(float x, float y, int width, int height) {
        super(x, y, width, height, 0, 0);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {

    }

    public void moveLeft() {
//        setDx(-speed);
    }

    public void moveRight() {
//        setDx(speed);
    }

    //Kiem tra va cham
    public boolean checkCollision(GameObject o) {
        return getX() < o.getX() + o.getWidth() &&
                getX() + getWidth() > o.getX() &&
                getY() < o.getY() + o.getHeight() &&
                getY() + getHeight() > o.getY();
    }
}
