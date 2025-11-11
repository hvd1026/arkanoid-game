package objects;

import java.awt.*;

/**
 * Abstract GameObject class represents a generic object in the game.
 * It defines the basic properties and methods that all game objects must implement.
 */

public abstract class GameObject {
    private float x, y;
    private int width, height;

    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters  and Setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // abstract method
    public abstract void update(double deltaTime);

    public abstract void render(Graphics2D g);
}
