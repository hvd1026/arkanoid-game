package objects.movable;

import objects.GameObject;

public abstract class MovableObject extends GameObject {
    float dx, dy; // velocity in x and y directions

    public MovableObject(float x, float y, int width, int height, float dx, float dy) {
        super(x, y, width, height);
        this.dx = dx;
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void move(double deltaTime) {
        setX(getX() + dx * (float) deltaTime);
        setY(getY() + dy * (float) deltaTime);
    }
}
