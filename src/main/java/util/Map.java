package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import objects.brick.Brick;
import objects.brick.BrickFactory;

import java.util.ArrayList;

public class Map {
    private int[][] map;
    private ArrayList<Brick> bricks;

    public int mapLevel;

    public Map(int chooseLevel) {
        map = new int[Constant.BRICK_ROWS][Constant.BRICK_COLUMNS];
        bricks = new ArrayList<>();
        mapLevel = chooseLevel;
        load();
    }

    public void load() {
        String filePath = "src/main/resources/maps/level" + mapLevel + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < Constant.BRICK_ROWS) {
                String[] values = line.split(" ");
                for (int col = 0; col < Math.min(values.length, Constant.BRICK_COLUMNS); col++) {
                    map[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Fill map with 0s in case of error
            for (int i = 0; i < Constant.BRICK_ROWS; i++)
                Arrays.fill(map[i], 0);
        }

        // create bricks from map
        bricks.clear();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0)
                    bricks.add(BrickFactory.createBrick(map[i][j],
                            j * Constant.BRICK_WIDTH,
                            Constant.GAME_Y_OFFSET + i * Constant.BRICK_HEIGHT,
                            Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT));
            }
        }
    }


    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
