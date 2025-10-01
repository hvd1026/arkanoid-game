package objects;

public abstract class Brick extends GameObject{
    private byte type;
    private int hitPoints;

    public Brick(byte type, int hitPoints, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.type = type;
        this.hitPoints = hitPoints;
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


}
