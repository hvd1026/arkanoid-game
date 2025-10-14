package objects;

import objects.brick.NormalBrick;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalBrickTest {
    NormalBrick brick;

    @Test
    @DisplayName("Test creation of NormalBrick")
    void testCreation() {
        brick = new NormalBrick(0, 0, 50, 20);
        assertNotNull(brick);
        assertEquals(2, brick.getHitPoints());
    }

    @Test
    @DisplayName("Test isDestroyed method")
    void testIsDestroyed() {
        brick = new NormalBrick(0, 0, 50, 20);
        assertFalse(brick.isDestroyed());
        brick.takeHit();
        assertFalse(brick.isDestroyed());
        brick.takeHit();
        assertTrue(brick.isDestroyed());
    }

    @Test
    @DisplayName("Test takeHit method")
    void testTakeHit() {
        brick = new NormalBrick(0, 0, 50, 20);
        assertEquals(2, brick.getHitPoints());
        brick.takeHit();
        assertEquals(1, brick.getHitPoints());
        brick.takeHit();
        assertEquals(0, brick.getHitPoints());
    }
}