package main;

import screen.Screen;
import screen.ScreenFactory;
import util.Constant;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Thread gameThread;
    private int currentScreen;
    Screen screen;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); // to improve rendering performance
        this.setFocusable(true); // to receive keyboard inputs
        this.addKeyListener(util.KeyHandle.getInstance()); // add key listener

        gameThread = new Thread(this); // create a new thread for the game loop

        currentScreen = Constant.MENU_SCREEN; // start with the menu screen
        screen = ScreenFactory.createScreen(currentScreen);
    }

    public void startGameThread() {
        gameThread.start(); // start the game thread
    }

    public void update(double deltaTime) {
        // Update game state
        screen.update(deltaTime);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // render game objects
        screen.render(g2);
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
