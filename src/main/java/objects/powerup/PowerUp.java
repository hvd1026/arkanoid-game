package objects.powerup;

import objects.GameObject;

public abstract class PowerUp extends GameObject {
    private byte type;
    private int duration;
    private long activatedTime;
    private boolean isActivated;
    private GameObject appliedTo;


    public PowerUp(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    // Getters  and Setters

    public byte getType() {
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

    public void setType(byte type) {
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
}
