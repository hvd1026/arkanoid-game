package objects.powerup;

import objects.GameObject;
import objects.movable.Ball;
import util.AssetManager;
import util.Constant;

import java.awt.*;
import java.util.ArrayList;

import static util.Constant.POWERUP_MULTIBALL_IMG;

/**
 * MultiBallPowerUp represents a power-up that, when activated,
 * duplicates all existing balls in play, creating additional balls.
 */

public class MultiBallPowerUp extends PowerUp {

    public MultiBallPowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.MULTI_BALL);
        setDuration(0); // Instant effect power-up
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g,
                POWERUP_MULTIBALL_IMG,
                (int) getX(),
                (int) getY(),
                getWidth(),
                getHeight());
    }

    @Override
    public void bulkApplyEffect(ArrayList<Ball> balls) {
        // Clone the current balls and add new balls with slight angle variations
        ArrayList<Ball> snapshot = new ArrayList<>(balls);
        float spread = Constant.MULTI_BALL_SPREAD_DEG;
        for (Ball base : snapshot) {
            float x = base.getX();
            float y = base.getY();
            float dx = base.getDx();
            float dy = base.getDy();
            float speed = (float) Math.sqrt(dx * dx + dy * dy);
            if (speed == 0) speed = Constant.BALL_SPEED;
            float baseAngleDeg = (float) Math.toDegrees(Math.atan2(-dy, dx));

            float angleLeft = baseAngleDeg - spread;
            float angleRight = baseAngleDeg + spread;

            Ball b1 = new Ball(x, y, 2 * Constant.BALL_RADIUS,
                    2 * Constant.BALL_RADIUS,
                    speed * (float) Math.cos(Math.toRadians(angleLeft)),
                    -speed * (float) Math.sin(Math.toRadians(angleLeft)));
            Ball b2 = new Ball(x, y, 2 * Constant.BALL_RADIUS,
                    2 * Constant.BALL_RADIUS,
                    speed * (float) Math.cos(Math.toRadians(angleRight)),
                    -speed * (float) Math.sin(Math.toRadians(angleRight)));
            balls.add(b1);
            balls.add(b2);
        }
    }

    @Override
    public void applyEffect(GameObject o) {

    }

    @Override
    public void removeEffect(GameObject o) {
        // No removal effect needed for MultiBallPowerUp
    }
}


