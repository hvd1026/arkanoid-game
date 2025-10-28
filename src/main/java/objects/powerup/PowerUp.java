package objects.powerup;

import objects.GameObject;
import util.Constant;

import java.awt.*;

public abstract class PowerUp extends GameObject {
    private PowerUpType type;
    private int duration;
    private long activatedTime;
    private boolean isActivated;
    private GameObject appliedTo;
    private float dy = Constant.POWERUP_FALL_SPEED;


    public PowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
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

    public GameObject getAppliedTo() {
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

    public void setAppliedTo(GameObject appliedTo) {
        this.appliedTo = appliedTo;
    }

    public abstract void applyEffect(GameObject o);

    public abstract void removeEffect(GameObject o);

    @Override
    public void update(double deltaTime) {
        if (!isActivated) {
            setY(getY() + dy * (float) deltaTime);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval((int) getX(), (int) getY(), getWidth(), getHeight());
    }

    public boolean isExpired() {
        if (!isActivated) return false;
        if (duration <= 0) return true; // tức thời
        return System.currentTimeMillis() - activatedTime >= duration;
    }

    public void activate(GameObject target) {
        if (isActivated) return;
        setAppliedTo(target);
        applyEffect(target);
        setActivated(true);
        setActivatedTime(System.currentTimeMillis());
    }
}
