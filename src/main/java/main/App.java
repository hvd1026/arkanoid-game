package main;

import util.Constant;

import javax.swing.*;

/**
 * Main application class to set up the game window and start the game loop.
 */
public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle(Constant.TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // add GamePanel to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        // Start the game loop
        gamePanel.startGameThread();

        window.setVisible(true);
        window.setLocationRelativeTo(null); // Center the window
    }
}
