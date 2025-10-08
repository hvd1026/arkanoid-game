package objects;

import java.awt.*;

public class Paddle extends MovableObject {
    public Paddle(float x, float y, int width, int height) {
        super(x, y, width, height, 0, 0);
    }

    @Override
    public void update(double deltaTime) {
        move(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {

    }

    public void moveLeft() {
//        setDx(-speed);
    }

    public void moveRight() {
//        setDx(speed);
    }

    //Kiem tra va cham
//    public boolean checkCollision(GameObject o) {
//
//    }
}
