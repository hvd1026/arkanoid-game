package objects.powerup;

import objects.GameObject;
import objects.movable.Ball;
import util.Constant;

import java.awt.*;
import java.util.ArrayList;

public abstract class PowerUp extends GameObject {
    private PowerUpType type;
    private int duration;
    private long activatedTime;
    private boolean isActivated;
    private final ArrayList<GameObject> appliedTo;

    public PowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
        appliedTo = new ArrayList<>();
    }

    // Getters  and Setters

    public PowerUpType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public long getActivatedTime() {
        return activatedTime;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public ArrayList<GameObject> getAppliedTo() {
        return appliedTo;
    }

    public void setType(PowerUpType type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setActivatedTime(long activatedTime) {
        this.activatedTime = activatedTime;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void addAppliedTo(GameObject o) {
        appliedTo.add(o);
    }

    public void bulkApplyEffect(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            applyEffect(ball);
        }
    }

    public void bulkRemoveEffect() {
        for (GameObject o : appliedTo) {
            removeEffect(o);
        }
    }

    public void syncEffect(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            if (!appliedTo.contains(ball)) {
                applyEffect(ball);
            }
        }
    }

    public abstract void applyEffect(GameObject o);

    public abstract void removeEffect(GameObject o);

    @Override
    public void update(double deltaTime) {
        if (!isActivated) {
            setY(getY() + Constant.POWERUP_FALL_SPEED * (float) deltaTime);
        }
    }

    public boolean isExpired() {
        if (!isActivated) return false;
        if (duration <= 0) return true; // instant powerup
        return System.currentTimeMillis() - activatedTime >= duration;
    }
    
}
