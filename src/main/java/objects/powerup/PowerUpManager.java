package objects.powerup;

import objects.GameObject;
import objects.brick.Brick;
import objects.movable.Ball;
import objects.movable.Paddle;
import util.AssetManager;
import util.Constant;
import util.SoundManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * PowerUpManager class handles the spawning, updating, rendering,
 * and expiration of power-ups in the game.
 */

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
                SoundManager.getInstance().playAudio("powerup");
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

    public void updateActivePowerUps() {
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

    public void clearAllActivePowerUps() {
        for (PowerUp p : activePowerUps) {
            p.bulkRemoveEffect();
        }
        activePowerUps.clear();
    }

    public void renderActiveHUD(Graphics2D g) {
        if (activePowerUps.isEmpty()) return;

        // Top-left corner
        final int paddingLeft = 10;
        final int paddingTop = 10;
        final int iconW = Constant.POWERUP_WIDTH;
        final int iconH = Constant.POWERUP_HEIGHT;
        final int gap = 6; // Distance between icons

        int n = activePowerUps.size();

        long now = System.currentTimeMillis();
        // Arrange icons from right to left
        for (int i = 0; i < n; i++) {
            PowerUp p = activePowerUps.get(i);
            int x = paddingLeft + (n - 1 - i) * (iconW + gap);

            int duration = p.getDuration();
            long elapsed = now - p.getActivatedTime();

            boolean shouldBlink = duration > 0 && elapsed >= (long) (duration * 0.7f);
            boolean visibleThisFrame = true;
            if (shouldBlink) {
                // Blinking effect: toggle visibility every 200 ms
                visibleThisFrame = ((now / 200) % 2) == 1;
            }

            if (visibleThisFrame) {
                int iconId = getIconIdForType(p.getType());
                AssetManager.getInstance().draw(g, iconId, x, paddingTop, iconW, iconH);
            }
        }
    }

    private int getIconIdForType(PowerUpType type) {
        return switch (type) {
            case EXPAND_PADDLE -> Constant.POWERUP_EXPAND_IMG;
            case FAST_BALL -> Constant.POWERUP_FAST_IMG;
            case MULTI_BALL -> Constant.POWERUP_MULTIBALL_IMG;
            case DOUBLE_DAMAGE -> Constant.POWERUP_DOUBLE_DAMAGE_IMG;
        };
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

