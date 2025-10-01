package objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    @Test
    @DisplayName("Test Ball Creation")
    void testCreation() {
        Ball ball = new Ball(10, 20, 15, 15, 5, -3);
        assertEquals(10, ball.getX());
        assertEquals(20, ball.getY());
        assertEquals(15, ball.getWidth());
        assertEquals(15, ball.getHeight());
        assertEquals(5, ball.getDx());
        assertEquals(-3, ball.getDy());
    }

    @Test
    @DisplayName("Test Ball Movement 1")
    void testMovement1() {
        // test with delta time = 1/60s
        Ball ball = new Ball(0, 0, 10, 10, 60, 120); // 60 px/s right, 120 px/s down
        ball.update();
        int expectedX = 1; // 0 + 60 * (1/60) = 1
        int expectedY = 2; // 0 + 120 * (1/60) = 2
        assertEquals(1, ball.getX(), 0.0000001);
        assertEquals(2, ball.getY(), 0.0000001);
    }

    @Test
    @DisplayName("Test Ball Movement 2")
    void testMovement2() {
        // test with delta time = 1/60s
        Ball ball = new Ball(100, 50, 20, 20, -30, 90); // -30 px/s left, 90 px/s down
        ball.update(); // Simulate one update (1/60th of a second)
        int expectedX = 99; // 100 + (-30) * (1/60) = 99.5
        int expectedY = 51; // 50 + 90 * (1/60) = 51.5
        assertEquals(99.5, ball.getX(), 0.0000001);
        assertEquals(51.5, ball.getY(), 0.0000001);
    }
}