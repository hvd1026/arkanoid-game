package objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastBallPowerUpTest {
    @Test
    @DisplayName("Test create FastBallPowerUp")
    void testCreateFastBallPowerUp() {
        FastBallPowerUp fastBallPowerUp = new FastBallPowerUp(100, 200, 20, 20);
        assertNotNull(fastBallPowerUp);
        assertEquals(100, fastBallPowerUp.getX());
        assertEquals(200, fastBallPowerUp.getY());
        assertEquals(20, fastBallPowerUp.getWidth());
        assertEquals(20, fastBallPowerUp.getHeight());
    }


}