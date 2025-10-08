package objects;

import objects.brick.StrongBrick;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrongBrickTest {

    StrongBrick brick;

    @Test
    @DisplayName("Test creation of StrongBrick")
    void testCreation() {
        brick = new StrongBrick(0, 0, 50, 20, (byte) 2, 10);
        assertNotNull(brick);
        assertEquals(10, brick.getHitPoints());
        assertEquals((byte) 2, brick.getType());
    }

    @Test
    @DisplayName("Test isDestroyed method")
    void testIsDestroyed() {
        brick = new StrongBrick(0, 0, 50, 20, (byte) 2, 2);
        assertFalse(brick.isDestroyed(brick.getHitPoints()));
        brick.takeHit();
        assertFalse(brick.isDestroyed(brick.getHitPoints()));
        brick.takeHit();
        assertTrue(brick.isDestroyed(brick.getHitPoints()));
    }

    @Test
    @DisplayName("Test takeHit method")
    void testTakeHit() {
        brick = new StrongBrick(0, 0, 50, 20, (byte) 2, 2);
        assertEquals(2, brick.getHitPoints());
        brick.takeHit();
        assertEquals(1, brick.getHitPoints());
        brick.takeHit();
        assertEquals(0, brick.getHitPoints());
    }
}