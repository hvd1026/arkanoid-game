package objects;

public abstract class Brick extends GameObject{
    private byte type;
    private int hitPoints;

    public Brick(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public byte getType() {
        return type;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public abstract void takeHit();

    public boolean isDestroyed(int hitPoints) {
        if (hitPoints == 0) {
            return true;
        }
        return false;
    }
}
