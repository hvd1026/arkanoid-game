package objects.powerup;

import objects.GameObject;
import objects.movable.Ball;
import util.Constant;

import java.awt.*;

public class FastBallPowerUp extends PowerUp {
    public FastBallPowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.FAST_BALL);
        setDuration(Constant.POWERUP_DURATION_MS_FASTBALL);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }

    @Override
    public void applyEffect(GameObject o) {
        if (!(o instanceof Ball)) return;
        Ball ball = (Ball) o;
        float speedScale = 1.4f;
        ball.setDx(ball.getDx() * speedScale);
        ball.setDy(ball.getDy() * speedScale);
    }

    @Override
    public void removeEffect(GameObject o) {
        if (!(o instanceof Ball)) return;
        Ball ball = (Ball) o;
        float speedScale = 1.0f / 1.4f;
        ball.setDx(ball.getDx() * speedScale);
        ball.setDy(ball.getDy() * speedScale);
    }
}
