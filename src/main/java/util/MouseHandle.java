package util;

import objects.ui.button.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * MouseHandle class manages mouse interactions within the application.
 * It tracks mouse position and provides methods to check
 * for hover and click events on button.
 * Implements MouseListener and MouseMotionListener interfaces.
 */

public class MouseHandle implements MouseListener, MouseMotionListener {
    private Point mousePos;
    private static MouseHandle instance;
    private boolean mousePressed;
    private Component component;
    private long lastClickTime = 0;

    private MouseHandle() {
        mousePos = new Point(0, 0);
        mousePressed = false;
    }


    public void setComponent(Component component) {
        this.component = component;
    }

    public void changeToHandCursor() {
        if (component != null) {
            component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    public void changeToDefaultCursor() {
        if (component != null) {
            component.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public static MouseHandle getInstance() {
        if (instance == null) {
            instance = new MouseHandle();
        }

        return instance;
    }

    public boolean isHoverOn(Button btn) {
        return btn.getBounds().contains(mousePos);
    }

    public boolean isClickOn(Button btn) {
        boolean isClick = isHoverOn(btn) && mousePressed;
        if (isClick) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime > Constant.CLICK_DELAY_MS) {
                lastClickTime = currentTime;
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos = e.getPoint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
