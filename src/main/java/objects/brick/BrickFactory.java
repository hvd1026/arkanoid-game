package objects.brick;

import util.Constant;

public class BrickFactory {
    public static Brick createBrick(int type, float x, float y, int width, int height) {
        if (type == Constant.STRONG_BRICK) {
            return new StrongBrick(x, y, width, height);
        } else if (type >= 1 && type <= 6) {
            return new NormalBrick(x, y, width, height); // chua add style vao
        } else {
            throw new IllegalArgumentException("Invalid brick type: " + type);
        }
    }
}
