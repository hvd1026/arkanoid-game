package objects.powerup;

import objects.GameObject;

import java.awt.*;

public class ExpandPaddlePowerUp extends PowerUp {
    public ExpandPaddlePowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void applyEffect(GameObject o) {

    }

    @Override
    public void removeEffect(GameObject o) {

    }
}
