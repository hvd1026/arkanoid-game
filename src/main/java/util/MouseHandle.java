package util;

import objects.ui.button.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandle implements MouseListener, MouseMotionListener {
    private Point mousePos;
    private static MouseHandle instance;
    private boolean mousePressed;
    private Component component;

    private MouseHandle() {
        mousePos = new Point(0, 0);
        mousePressed = false;
        component = null;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public static MouseHandle getInstance() {
        if (instance == null) {
            instance = new MouseHandle();
        }

        return instance;
    }

    public boolean isHoverOn(Button btn) {
        boolean hover = btn.getBounds().contains(mousePos);
        if (hover) {
            component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            component.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return hover;
    }

    public boolean isClickOn(Button btn) {
        return isHoverOn(btn) && mousePressed;
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
