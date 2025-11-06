package objects.powerup;

import objects.GameObject;
import objects.movable.Ball;
import util.AssetManager;
import util.Constant;

import java.awt.*;

import static util.Constant.POWERUP_FAST_IMG;

public class FastBallPowerUp extends PowerUp {
    public static final float SPEED_SCALE = 1.4f;

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
        AssetManager.getInstance().draw(g, POWERUP_FAST_IMG, (int) getX(), (int) getY(), getWidth(), getHeight());
    }

    @Override
    public void applyEffect(GameObject o) {
        if (!(o instanceof Ball ball)) return;
        addAppliedTo(ball);
        ball.setDx(ball.getDx() * SPEED_SCALE);
        ball.setDy(ball.getDy() * SPEED_SCALE);
    }

    @Override
    public void removeEffect(GameObject o) {
        if (!(o instanceof Ball ball)) return;
        float inv = 1.0f / SPEED_SCALE;
        ball.setDx(ball.getDx() * inv);
        ball.setDy(ball.getDy() * inv);
    }
}
