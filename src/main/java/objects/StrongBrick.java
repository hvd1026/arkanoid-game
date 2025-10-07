package objects;

public class StrongBrick extends Brick{

    public StrongBrick(float x, float y, int width, int height, byte type, int hitPoints) {
        super(x, y, width, height);
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
