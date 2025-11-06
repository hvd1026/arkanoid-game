package objects.powerup;

import objects.GameObject;
import util.AssetManager;

import java.awt.*;

import static util.Constant.POWERUP_MULTIBALL_IMG;

public class MultiBallPowerUp extends PowerUp {

    public MultiBallPowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.MULTI_BALL);
        setDuration(0); // hiệu ứng tức thời
    }

    @Override
    public void render(Graphics2D g) {
        AssetManager.getInstance().draw(g, POWERUP_MULTIBALL_IMG, (int) getX(), (int) getY(), getWidth(), getHeight());
    }

    @Override
    public void applyEffect(GameObject o) {
        // Được xử lý tại GameScreen (tạo thêm bóng) – không làm gì tại đây
    }

    @Override
    public void removeEffect(GameObject o) {
        // Tức thời – không cần remove
    }
}


