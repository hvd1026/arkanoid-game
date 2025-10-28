package main;

import screen.MenuScreen;
import screen.ScreenManager;
import util.AssetManager;
import util.Constant;
import util.MouseHandle;

import javax.swing.*;
import java.awt.*;

/**
 * Main panel for the game, extends JPanel from Java Swing and implements Runnable from Java Thread:
 * - Handle game loop
 * - Update and render game objects
 */
public class GamePanel extends JPanel implements Runnable {
    private final Thread gameThread;

    public GamePanel() {
        // setup panel
        this.setPreferredSize(new java.awt.Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setDoubleBuffered(true); // to improve rendering performance
        gameThread = new Thread(this); // create a new thread for the game loop

        // keyboard event
        this.setFocusable(true); // to receive keyboard input
        this.addKeyListener(util.KeyHandle.getInstance()); // add key listener

        // mouse event
        this.addMouseListener(util.MouseHandle.getInstance()); // add mouse listener: click, press, release
        this.addMouseMotionListener(util.MouseHandle.getInstance()); // add mouse motion listener: move, drag
        MouseHandle.getInstance().setComponent(this); // set component for mouse handling (change cursor, ...)

        // load assets
        AssetManager.getInstance();
        // initialize screen manager, start with menu screen
        ScreenManager.getInstance().switchScreen(new MenuScreen());
    }

    public void startGameThread() {
        gameThread.start();
    }

    /**
     * Update game objects
     *
     * @param deltaTime time elapsed since last update (seconds)
     */
    public void update(double deltaTime) {
        ScreenManager.getInstance().getCurrentScreen().update(deltaTime); // update current screen
    }

    /**
     * Render game objects
     *
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ScreenManager.getInstance().getCurrentScreen().render(g2); // render current screen
        Toolkit.getDefaultToolkit().sync(); // fix rendering issues on some systems
        g2.dispose();
    }

    /**
     * Game loop
     */
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
