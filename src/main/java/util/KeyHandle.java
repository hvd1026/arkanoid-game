package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandle implements KeyListener {
    private static KeyHandle instance;
    public boolean spacePressed, leftPressed, rightPressed;

    private KeyHandle() {
        spacePressed = false;
        leftPressed = false;
        rightPressed = false;
    }

    public static KeyHandle getInstance() {
        if (instance == null) {
            instance = new KeyHandle();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }


}
