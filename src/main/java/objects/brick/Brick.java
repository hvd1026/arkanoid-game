package objects.brick;

import objects.GameObject;

public abstract class Brick extends GameObject {
    private int hitPoints;// so lan cham de gach bi pha huy.

    public Brick(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    // do ben cua gach sau khi cham bong.
    public void takeHit(int damage) {
        setHitPoints(getHitPoints() - Math.max(1, damage));
    }

    // ham kiem tra gach bi pha.
    public boolean isDestroyed() {
        if (hitPoints <= 0) {
            return true;
        }
        return false;
    }


}
