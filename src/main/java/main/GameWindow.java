package main;

import gameObject.GameObjects;

import javax.swing.*;

/**
 * Fenster, in welchem das Spiel ausgeführt wird (bitte nicht anfassen ohne zu fragen!!!!)
 */
public class GameWindow {
public static GamePanel gamePanel;
    public static void openWindow(){
    JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pharmacy");
       // GameObjects gameObjects;
    gamePanel =new GamePanel();
            window.add(gamePanel);
            window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.run();}


}
