package main;

import screen.LevelScreen;
import screen.MenuScreen;
import screen.GameScreen;
import screen.ScreenManager;
import util.AssetManager;
import util.Constant;
import util.KeyHandle;
import util.MouseHandle;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new java.awt.Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); // to improve rendering performance
        this.setFocusable(true); // to receive keyboard inputs
        this.addKeyListener(util.KeyHandle.getInstance()); // add key listener
        // mouse event
        this.addMouseListener(util.MouseHandle.getInstance());
        this.addMouseMotionListener(util.MouseHandle.getInstance());

        gameThread = new Thread(this); // create a new thread for the game loop

        ScreenManager.getInstance().switchScreen(new MenuScreen()); // start with menu screen
        AssetManager.getInstance(); // load assets
    }

    public void startGameThread() {
        gameThread.start(); // start the game thread
    }

    public void update(double deltaTime) {
        // Update game state
        ScreenManager.getInstance().getCurrentScreen().update(deltaTime);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // render game objects
        ScreenManager.getInstance().getCurrentScreen().render(g2);
        Toolkit.getDefaultToolkit().sync(); // fix lag on linux
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
