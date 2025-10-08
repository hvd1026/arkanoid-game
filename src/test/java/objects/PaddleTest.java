package objects;

import objects.movable.Paddle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaddleTest {

    @Test
    @DisplayName("Test Paddle Creation")
    void testPaddleCreation() {
        Paddle paddle = new Paddle(50, 100, 200, 20);
        assertNotNull(paddle);
        assertEquals(50, paddle.getX());
        assertEquals(100, paddle.getY());
        assertEquals(200, paddle.getWidth());
        assertEquals(20, paddle.getHeight());
    }

    @Test
    @DisplayName("Test Paddle Movement 1")
    void testPaddleMovement1() {
        Paddle paddle = new Paddle(50, 100, 200, 20);
        paddle.setDx(100); // move right 100px/s
        paddle.update(1.0); // update for 1 second
        assertEquals(150, paddle.getX()); // 50 + 100*1.0 = 150
    }

    @Test
    @DisplayName("Test Paddle Movement 2")
    void testPaddleMovement2() {
        Paddle paddle = new Paddle((float) 50.5, 100, 200, 20);
        paddle.setDx(100); // move right 100px/s
        paddle.update(1 / 60.0); // update for 1 frame at 60fps
        assertEquals(52.166666667, paddle.getX(), 0.0001); // 50.5 + 100*(1/60) = 52.166666667
    }
}