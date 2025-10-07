package objects;

public class Paddle extends MovableObject {
    private final float speed; //Toc do di chuyen cua Paddle

    public Paddle(float x, float y, int width, int height) {
        super(x, y, width, height, 0, 0);
    }

    public void moveLeft() {
        setDx(-speed);
    }

    public void moveRight() {
        setDx(speed);
    }

    //Kiem tra va cham
    public boolean checkCollision(GameObject o) {
        return getX() < o.getX() + o.getWidth() &&
                getX() + getWidth() > o.getX() &&
                getY() < o.getY() + o.getHeight() &&
                getY() + getHeight() > o.getY();
    }
}
