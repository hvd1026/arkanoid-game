package util;

import java.io.Serializable;

import static util.Constant.TOTAL_LEVELS;

/**
 * LevelData class represents the data structure to store the star ratings for each level.
 * Implements Serializable for easy saving and loading of level data.
 */

public class LevelData implements Serializable {
    private final int[] levels = new int[TOTAL_LEVELS + 1];

    public LevelData() {
        for (int i = 0; i <= TOTAL_LEVELS; i++) {
            levels[i] = 0;
        }
    }

    public int[] getLevels() {
        return levels;
    }

}
