package objects.powerup;

import objects.GameObject;
import objects.movable.Paddle;
import util.Constant;

import java.awt.*;

public class ExpandPaddlePowerUp extends PowerUp {
    public ExpandPaddlePowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.EXPAND_PADDLE);
        setDuration(Constant.POWERUP_DURATION_MS_EXPAND);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
    }

    @Override
    public void applyEffect(GameObject o) {
        if (!(o instanceof Paddle)) return;
        Paddle paddle = (Paddle) o;
        int newWidth = (int) (paddle.getWidth() * 1.5f);
        // clamp trong màn hình
        newWidth = Math.min(newWidth, Constant.SCREEN_WIDTH);
        int delta = newWidth - paddle.getWidth();
        paddle.setWidth(newWidth);
        // cố gắng giữ tâm gần như cũ
        paddle.setX(Math.max(0, Math.min(paddle.getX() - delta / 2f, Constant.SCREEN_WIDTH - paddle.getWidth())));
    }

    @Override
    public void removeEffect(GameObject o) {
        if (!(o instanceof Paddle)) return;
        Paddle paddle = (Paddle) o;
        int original = Constant.PADDLE_WIDTH;
        int delta = paddle.getWidth() - original;
        paddle.setWidth(original);
        paddle.setX(Math.max(0, Math.min(paddle.getX() + delta / 2f, Constant.SCREEN_WIDTH - paddle.getWidth())));
    }
}
