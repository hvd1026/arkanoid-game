package util;

import java.io.Serializable;

import static util.Constant.TOTAL_LEVELS;

public class LevelData implements Serializable {
    private int[] levels = new int[TOTAL_LEVELS + 1];

    public LevelData() {
        for (int i = 0; i <= TOTAL_LEVELS; i++) {
            levels[i] = 0;
        }
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevelStar(int level, int star) {
        levels[level] = star;
    }
}
