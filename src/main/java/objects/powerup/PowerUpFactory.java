package objects.powerup;

import util.Constant;

public class PowerUpFactory {
    public static PowerUp createPowerUp(PowerUpType type, float x, float y) {
        return switch (type) {
            case FAST_BALL -> new FastBallPowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
            case MULTI_BALL -> new MultiBallPowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
            case EXPAND_PADDLE -> new ExpandPaddlePowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
            case DOUBLE_DAMAGE -> new DoubleDamagePowerUp(x, y, Constant.POWERUP_WIDTH, Constant.POWERUP_HEIGHT);
        };
    }

    public static PowerUp createRandomPowerUp(float x, float y) {
        PowerUpType randomType = getRandomPowerUpType();
        return createPowerUp(randomType, x, y);
    }

    public static PowerUpType getRandomPowerUpType() {
        PowerUpType[] types = PowerUpType.values();
        int randomIndex = (int) (Math.random() * types.length);
        return types[randomIndex];
    }

}
