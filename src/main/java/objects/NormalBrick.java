package objects;

public class NormalBrick extends Brick {

    public NormalBrick(float x, float y, int width, int height, byte type, int hitPoints) {
        super(x,y,width,height);
        setType(type);
        setHitPoints(hitPoints);
    }

    @Override
    public void takeHit() {
        setHitPoints(getHitPoints() - 1);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
