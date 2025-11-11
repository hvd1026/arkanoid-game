package objects.powerup;

import util.Constant;

/**
 * PowerUpFactory is a factory class responsible for creating PowerUp objects based on the specified type.
 */

public class PowerUpFactory {
    public static PowerUp createPowerUp(PowerUpType type, float x, float y) {
        return switch (type) {
            case FAST_BALL -> new FastBallPowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
            case MULTI_BALL -> new MultiBallPowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
            case EXPAND_PADDLE -> new ExpandPaddlePowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
            case DOUBLE_DAMAGE -> new DoubleDamagePowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
        };
    }
    // Create a random power-up at the specified position
    public static PowerUp createRandomPowerUp(float x, float y) {
        PowerUpType randomType = getRandomPowerUpType();
        return createPowerUp(randomType, x, y);
    }
    // Get a random PowerUpType
    public static PowerUpType getRandomPowerUpType() {
        PowerUpType[] types = PowerUpType.values();
        int randomIndex = (int) (Math.random() * types.length);
        return types[randomIndex];
    }

}
