package main;

import util.Constant;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle(Constant.TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null); // Center the window
        window.setVisible(true);

        // add GamePanel to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
    }
}
