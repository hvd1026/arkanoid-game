package objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalBrickTest {
    NormalBrick brick;

    @Test
    @DisplayName("Test creation of NormalBrick")
    void testCreation() {
        brick = new NormalBrick(0, 0, 50, 20, (byte) 1, 2);
        assertNotNull(brick);
        assertEquals(2, brick.getHitPoints());
        assertEquals((byte) 1, brick.getType());
    }

    @Test
    @DisplayName("Test isDestroyed method")
    void testIsDestroyed() {
        brick = new NormalBrick(0, 0, 50, 20, (byte) 1, 2);
        assertFalse(brick.isDestroyed(brick.getHitPoints()));
        brick.takeHit();
        assertFalse(brick.isDestroyed(brick.getHitPoints()));
        brick.takeHit();
        assertTrue(brick.isDestroyed(brick.getHitPoints()));
    }

    @Test
    @DisplayName("Test takeHit method")
    void testTakeHit() {
        brick = new NormalBrick(0, 0, 50, 20, (byte) 1, 2);
        assertEquals(2, brick.getHitPoints());
        brick.takeHit();
        assertEquals(1, brick.getHitPoints());
        brick.takeHit();
        assertEquals(0, brick.getHitPoints());
    }
}