package objects.powerup;

import objects.GameObject;
import objects.brick.Brick;
import objects.movable.Ball;
import objects.movable.Paddle;
import util.Constant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PowerUpManager {
    private final Paddle paddle;
    private final ArrayList<Ball> balls;
    private final ArrayList<PowerUp> fallingPowerUps = new ArrayList<>();
    private final ArrayList<PowerUp> activePowerUps = new ArrayList<>();
    private final Random rng = new Random();

    public PowerUpManager(Paddle paddle, ArrayList<Ball> balls) {
        this.paddle = paddle;
        this.balls = balls;
    }

    public void updateFallingPowerUps(double deltaTime, boolean ballFollowingPaddle) {
        Iterator<PowerUp> pit = fallingPowerUps.iterator();
        while (pit.hasNext()) {
            PowerUp p = pit.next();
            p.update(deltaTime);
            // powerup out of screen
            if (p.getY() > Constant.SCREEN_HEIGHT) {
                pit.remove();
                continue;
            }
            // if powerup collides with paddle
            if (isIntersect(p, paddle)) {
                // if ball is following paddle, ignore powerup
                if (ballFollowingPaddle) {
                    pit.remove();
                    continue;
                }
                // if powerup is activated, refresh time
                boolean refreshed = false;
                for (PowerUp ap : activePowerUps) {
                    if (ap.getType() == p.getType()) {
                        System.out.println("Power-up refreshed: " + p.getType());
                        ap.setActivatedTime(System.currentTimeMillis());
                        refreshed = true;
                        break;
                    }
                }
                // if not refreshed, apply new powerup
                if (!refreshed) {
                    p.applyEffect(paddle);
                    p.bulkApplyEffect(balls);
                    p.setActivated(true);
                    p.setActivatedTime(System.currentTimeMillis());
                    activePowerUps.add(p);
                    System.out.println("Power-up activated: " + p.getType());
                }
                pit.remove();
            }
        }
    }

    public void updateActivePowerUps(double deltaTime) {
        // sync power up effects with current balls
        for (PowerUp p : activePowerUps) {
            p.syncEffect(balls);
        }

        // Remove expired power-ups
        Iterator<PowerUp> ait = activePowerUps.iterator();
        while (ait.hasNext()) {
            PowerUp p = ait.next();
            if (p.isExpired()) {
                p.bulkRemoveEffect();
                System.out.println("Power-up expired: " + p.getType());
                ait.remove();
            }
        }
    }

    public void render(java.awt.Graphics2D g) {
        for (PowerUp p : fallingPowerUps) {
            p.render(g);
        }
    }

    // Call when a brick is destroyed
    public void maybeSpawnPowerUp(Brick b) {
        if (rng.nextFloat() <= Constant.POWERUP_DROP_RATE) {
            float px = b.getX() + b.getWidth() / 2f - 12;
            float py = b.getY() + b.getHeight();
            PowerUp p = PowerUpFactory.createRandomPowerUp(px, py);
            fallingPowerUps.add(p);
        }
    }

    // AABB collision detection
    private boolean isIntersect(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }
}
