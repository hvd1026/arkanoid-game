package objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpandPaddlePowerUpTest {
    @Test
    @DisplayName("Test create ExpandPaddlePowerUp")
    void testCreateExpandPaddlePowerUp() {
        ExpandPaddlePowerUp expandPaddlePowerUp = new ExpandPaddlePowerUp(150, 250, 30, 30);
        assertNotNull(expandPaddlePowerUp);
        assertEquals(150, expandPaddlePowerUp.getX());
        assertEquals(250, expandPaddlePowerUp.getY());
        assertEquals(30, expandPaddlePowerUp.getWidth());
        assertEquals(30, expandPaddlePowerUp.getHeight());
    }
}