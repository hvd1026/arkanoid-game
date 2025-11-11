package objects.brick;

import util.Constant;

/**
 * BrickFactory is a factory class responsible for creating Brick objects based on the specified type.
 */

public class BrickFactory {
    public static Brick createBrick(int type, float x, float y, int width, int height) {
        if (type == Constant.STRONG_BRICK) {
            return new StrongBrick(x, y, width, height); // strong brick
        } else if (type >= 1 && type <= 6) {
            return new NormalBrick(x, y, width, height, type); // style corresponds to type
        } else {
            throw new IllegalArgumentException("Invalid brick type: " + type); // handle invalid type
        }
    }
}
