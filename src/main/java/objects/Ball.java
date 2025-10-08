package objects;

import java.awt.*;

public class Ball extends MovableObject {
    public Ball(float x, float y, int weight, int height, float dx, float dy) {
        super(x, y, weight, height, dx, dy);
    }


    @Override
    public void update(double deltaTime) {
        move(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        // placeholder
    }

    public void bounceOff(Object other) {

    }

    public void checkCollision(Object other) {

    }
}
