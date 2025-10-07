package objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {


    private PowerUp createPowerUp() {
        // Sử dụng constructor của PowerUp: public PowerUp(float x, float y, int width, int height)
        return new PowerUp(0, 0, 10, 10) {
            @Override
            public void update() {
            }
            @Override
            public void render() {
            }
            // Bắt buộc phải triển khai các phương thức trừu tượng
            @Override
            public void applyEffect(GameObject o) {}
            @Override
            public void removeEffect(GameObject o) {}
        };
    }

    PowerUp powerUp = createPowerUp();

    @Test
    @DisplayName("Test getter and setter for type")
    void testGetTypeAndSetType() {
        byte expectedType = 5;
        powerUp.setType(expectedType);
        assertEquals(expectedType, powerUp.getType());
    }

    @Test
    @DisplayName("Test getter and setter for duration")
    void testGetDurationAndSetDuration() {
        int expectedDuration = 10000; // 10 secs
        powerUp.setDuration(expectedDuration);
        assertEquals(expectedDuration, powerUp.getDuration());
    }

    @Test
    @DisplayName("Test getter and setter for activatedTime")
    void testGetActivatedTimeAndSetActivatedTime() {
        long expectedTime = System.currentTimeMillis();
        powerUp.setActivatedTime(expectedTime);
        assertEquals(expectedTime, powerUp.getActivatedTime());
    }

    @Test
    @DisplayName("Test getter and setter for isActivated status")
    void testIsActivatedAndSetActivated() {
        // Mặc định là false (nếu không được khởi tạo trong constructor)
        assertFalse(powerUp.isActivated());

        powerUp.setActivated(true);
        assertTrue(powerUp.isActivated());
    }

    @Test
    @DisplayName("Test getter and setter for appliedTo object")
    void testGetAppliedToAndSetAppliedTo() {
        // Giả sử có một đối tượng GameObject khác để gán
        GameObject dummyObject = createPowerUp(); // Dùng PowerUp stub như một GameObject bất kỳ

        powerUp.setAppliedTo(dummyObject);
        assertEquals(dummyObject, powerUp.getAppliedTo());
        assertNotNull(powerUp.getAppliedTo());
    }
}