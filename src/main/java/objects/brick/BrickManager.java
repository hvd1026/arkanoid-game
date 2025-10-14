package objects.brick;

import util.Constant;

import java.util.ArrayList;

public class BrickManager {
    private ArrayList<Brick> bricks = new ArrayList<>();

    public BrickManager() {}

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    /** ham tao gach 1. */

    public void CreateBrick1() {
        for (int i = 0; i < Constant.SCREEN_WIDTH; i = i + Constant.BRICK_WIDTH) {
            for (int j = 0; j < Constant.BRICK_HEIGHT * 3; j = j + Constant.BRICK_HEIGHT) {
                if ((i/80 + j/20) % 2 == 0) {
                    Brick b = new NormalBrick(i, j, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
                    bricks.add(b);
                } else {
                    Brick b = new StrongBrick(i, j, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
                    bricks.add(b);
                }
            }
        }
    }


}
