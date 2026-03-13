package main;



import gameObject.GameObject;
import gameObject.GameObjects;
import gamelogic.GameLogic;
import gamelogic.MouseInput;
import maps.MainRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;

/**
 * Game Pannel in welchem das Spiel Läuft
 */
public class GamePanel extends JPanel implements Runnable {
    GameObjects allGameObjects; //liste aller gameObjekt im aktuellen gamePanel
    /**
     * Das Feld wird in Tiles unterteilt um Sicherzustellen,
     * dass die Gesammtauflösung ein Vielfaches der Tilezise ist und somit das tilen von Objkten der Standart Auflösung möglich ist
     */
    final int TileZise = 32;// größe der Tiles
    final int colums =32;//Spalten
    final int rows =32;// Zeilen
    final int defaultwidth = TileZise*colums;//Breite
    final int defaultheight = TileZise*rows;//Höhe
    /**
     * Das Spiel wird in einem bestimmten Interwall geupdtate
     */
    final int FpsUps=30; //Updates Per Second
    final int delay =1000/FpsUps; //-> darau resuliterende Pause

    /**
     * Construkor für ein GamePannel
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(defaultwidth,defaultheight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());
    }

    /**
     * führt das Gamepannel aus
     */
    @Override
    public void run() {

        allGameObjects =new GameObjects();//Legt die GameObjekt Liste an
        allGameObjects = MainRoom.buildMap(allGameObjects); //Baut die Map (Initialisiert die GameObjekt Liste)

        /**
         * Game Loop (Update -> repaint -> Pause)
         */
        Timer t = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                allGameObjects =GameLogic.update(allGameObjects,actionEvent);
                repaint();
            }
        });
        t.start();

    }

    /**
     * Methode zum Malen der Komponenten
     * Alle GameObjekte, die sichtbar sind, werden in der Reihenfolge wie sie in der Gameobjekt Liste liegen, gezeichnet.
     * Dh. Objekte die am anfang der GameObjekt Liste Liegen werden zuerst also unten gezeichnet
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
       // System.out.println("started Painting"); //Statusmeldung in der Konsole

        Graphics2D g2d= (Graphics2D) g;


        for (int i = 0; i < allGameObjects.getGameObjects().size(); i++) {
           GameObject aktuellesGameobjekt= allGameObjects.getGameObjects().get(i);
           if (aktuellesGameobjekt.isVisible()){
            g2d.drawImage(aktuellesGameobjekt.getImg(),aktuellesGameobjekt.getPositionX(),aktuellesGameobjekt.getPositionY(),null);
            if (aktuellesGameobjekt.getTextToDisplay()!=null&&aktuellesGameobjekt.getTextToDisplay()!=""){

                String text = aktuellesGameobjekt.getTextToDisplay()+"";
                int x = aktuellesGameobjekt.getPositionX();
                int y = aktuellesGameobjekt.getPositionY();
                Font font = new Font("Georgia", Font.ITALIC, 20);
                TextLayout textLayout = new TextLayout(text, font, g2d.getFontRenderContext());
                g2d.setPaint(Color.BLACK);
                textLayout.draw(g2d, x, y+32);
            }

           }
        }

       // System.out.println("finished Painting"); //Statusmeldung in der Konsole
    }
}
