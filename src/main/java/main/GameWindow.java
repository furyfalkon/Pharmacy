package main;
import gamelogic.KeyInput;
import helper.ImageLoader;
import maps.Kitchen;
import maps.MainRoom;

import javax.swing.*;

/**
 * Fenster, in welchem das Spiel ausgeführt wird (bitte nicht anfassen, ohne es abzusprechen!!!!)
 */
public class GameWindow {
    /**
     * Methode zum Öffnen des Fensters
     */

    public static GamePanel mainGamePanel;
    public static GamePanel kitchenGamePanel;
    public static void openWindow(){

    JFrame window = new JFrame(); //Erstellen des Fensters als JFrame
                                                                            //Setzen aller Wichtigen eigenschafen wie:
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              //was passiert, wenn das fenster geschlossen wird
        window.setResizable(false);                                         //kann die größe geändert werden
        window.setTitle("Pharmacy");                                        //Titel des Fensters
        window.setIconImage(ImageLoader.loadImage("Logo.png"));     //Icon des Fensters
        window.setFocusable(true);                                          //Fokussierbar (wichtig für KeyListener)
        window.requestFocus();                                              //Fragt fokus an (wichtig für KeyListener)
        window.addKeyListener(new KeyInput());                              //Key Listener wid Hinzugefügt

                                                      //Game Panel wird erstellt
    mainGamePanel =new GamePanel();
    mainGamePanel.build(MainRoom.buildMap());
    mainGamePanel.setVisible(true);
        window.add(mainGamePanel);                   //und dem Fenster hinzugefügt
        window.pack();



        window.setLocationRelativeTo(null);                                 // die Position des Fensters wird festgelegt
        window.setVisible(true);                                            // und es wird sichtbar gemacht

        mainGamePanel.run();}                                                   //das start GamePanel wird ausgeführt
}
