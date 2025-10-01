package main;

import util.Constant;

import javax.swing.*;

public class GamePanel extends JPanel {
    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); // to improve rendering performance
        this.setFocusable(true); // to receive keyboard inputs
    }
}
