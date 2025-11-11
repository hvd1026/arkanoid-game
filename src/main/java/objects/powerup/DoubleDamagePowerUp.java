package objects.powerup;

import objects.GameObject;
import objects.movable.Ball;
import util.AssetManager;
import util.Constant;

import java.awt.*;

import static util.Constant.POWERUP_DOUBLE_DAMAGE_IMG;

/**
 * DoubleDamagePowerUp represents a power-up that allows balls to break strong bricks.
 */

public class DoubleDamagePowerUp extends PowerUp {

    public DoubleDamagePowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.DOUBLE_DAMAGE);
        setDuration(Constant.POWERUP_DURATION_MS_DOUBLE_DAMAGE);
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, POWERUP_DOUBLE_DAMAGE_IMG, (int) getX(), (int) getY(), getWidth(), getHeight());
    }
    // When applied, set the ball's ability to break strong bricks to true
    @Override
    public void applyEffect(GameObject o) {
        if (!(o instanceof Ball ball)) {
            return;
        }
        addAppliedTo(ball);
        ball.setCanBreakStrongBrick(true);
    }

    @Override
    public void removeEffect(GameObject o) {
        if (!(o instanceof Ball ball)) {
            return;
        }
        ball.setCanBreakStrongBrick(false);
    }
}


