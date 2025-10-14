package objects.brick;

import util.Constant;

public class BrickFactory {
    public static Brick createBrick(int type, float x, float y, int width, int height) {
        switch (type) {
            case Constant.NORMAL_BRICK:
                return new NormalBrick(x, y, width, height);
            case Constant.STRONG_BRICK:
                return new StrongBrick(x, y, width, height);
            default:
                throw new IllegalArgumentException("Invalid brick type: " + type);
        }
    }
}
