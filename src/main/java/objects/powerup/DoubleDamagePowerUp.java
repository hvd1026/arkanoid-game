package objects.powerup;

import objects.GameObject;
import objects.movable.Ball;
import util.Constant;

import java.awt.*;

public class DoubleDamagePowerUp extends PowerUp {
    private boolean previousCanBreakStrong;

    public DoubleDamagePowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.DOUBLE_DAMAGE);
        setDuration(Constant.POWERUP_DURATION_MS_DOUBLE_DAMAGE);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }

    @Override
    public void applyEffect(GameObject o) {
        if (!(o instanceof Ball)) return;
        Ball ball = (Ball) o;
        previousCanBreakStrong = ball.isCanBreakStrongBrick();
        ball.setCanBreakStrongBrick(true);
    }

    @Override
    public void removeEffect(GameObject o) {
        if (!(o instanceof Ball)) return;
        Ball ball = (Ball) o;
        ball.setCanBreakStrongBrick(previousCanBreakStrong);
    }
}


