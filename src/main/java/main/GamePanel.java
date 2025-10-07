package main;

import util.Constant;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); // to improve rendering performance
        this.setFocusable(true); // to receive keyboard inputs

        gameThread = new Thread(this); // create a new thread for the game loop
    }

    public void startGameThread() {
        gameThread.start(); // start the game thread
    }

    public void update(double deltaTime) {
        // Update game state
        double fps = 1.0 / deltaTime;
        System.out.println("FPS: " + fps);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // render game objects

        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1.0 / Constant.FPS; // time per frame (in seconds)
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastTime) / 1000000000.0; // time since last frame (in seconds)
            lastTime = currentTime;
            deltaTime += elapsedTime; // accumulate elapsed time
            while (deltaTime >= drawInterval) {
                update(deltaTime);
                repaint();
                deltaTime -= drawInterval;
            }
        }

    }
}
