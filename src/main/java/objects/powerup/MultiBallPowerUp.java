package objects.powerup;

import objects.GameObject;

import java.awt.*;

public class MultiBallPowerUp extends PowerUp {
    private int ballsToSpawn = 2; // số bóng sẽ spawn thêm

    public MultiBallPowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        setType(PowerUpType.MULTI_BALL);
        setDuration(0); // hiệu ứng tức thời
    }

    public int getBallsToSpawn() {
        return ballsToSpawn;
    }

    public void setBallsToSpawn(int ballsToSpawn) {
        this.ballsToSpawn = Math.max(1, ballsToSpawn);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.PINK);
        g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
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


