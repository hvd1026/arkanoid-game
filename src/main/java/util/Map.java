package util;

import objects.brick.Brick;
import objects.brick.BrickFactory;

import java.util.ArrayList;

public class Map {
    private int[][] map;
    private ArrayList<Brick> bricks;

    public Map() {
        map = new int[Constant.BRICK_ROWS][Constant.BRICK_COLUMNS];
        bricks = new ArrayList<>();
    }

    public void load() {
        // sample map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 0;
            }
        }
        for (int j = 0; j < map[0].length; j++) {
            if (j % 2 == 0) {
                map[0][j] = Constant.NORMAL_BRICK;
            } else {
                map[0][j] = Constant.STRONG_BRICK;
            }
        }
        for (int j = 0; j < map[0].length; j++) {
            if (j % 2 == 0) {
                map[1][j] = Constant.STRONG_BRICK;
            } else {
                map[2][j] = Constant.NORMAL_BRICK;
            }
        }

        // create bricks from map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0)
                    bricks.add(BrickFactory.createBrick(map[i][j], j * Constant.BRICK_WIDTH, Constant.GAME_Y_OFFSET + i * Constant.BRICK_HEIGHT, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT));
            }
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
