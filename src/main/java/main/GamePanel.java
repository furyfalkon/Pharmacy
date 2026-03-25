package main;

import gameObject.GameObject;
import gameObject.GameObjects;
import gamelogic.GameLogic;
import gamelogic.MouseInput;
import maps.MainRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;

/**
 * Game Panel in welchem das Spiel Läuft
 */
public class GamePanel extends JPanel implements Runnable {
    GameObjects allGameObjects; //liste aller gameObjekt im aktuellen gamePanel
    /**
     * Das Feld wird in Tiles unterteilt, um Sicherzustellen,
     * dass die Gesamtauflösung ein Vielfaches der Tile size ist und somit das tilen von Objekten der Standard Auflösung möglich ist
     */
    final int TileSize = 32;// größe der Tiles
    final int columns =32;//Spalten
    final int rows =32;// Zeilen
    final int defaultWidth = TileSize * columns;//Breite
    final int defaultHeight = TileSize *rows;//Höhe
    /**
     * Das Spiel wird in einem bestimmten Interwall geupdatet
     */
    final int FpsUps=30; //Updates Per Second
    final int delay =1000/FpsUps; //-> daraus resultierende Pause

    /**
     * Constructor für ein GamePanel
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());
    }

    /**
     * führt das GamePanel aus
     */
    @Override
    public void run() {
        /*
         * Game Loop (Update -> repaint -> Pause)
         */
        Timer t = new Timer(delay, actionEvent -> {
            allGameObjects =GameLogic.update(allGameObjects);
            repaint();
        });
        t.start();

    }

    public void build(GameObjects gameObjects){
        allGameObjects =new GameObjects();//Legt die GameObjektListe an
        this.allGameObjects =gameObjects; //Baut die Map (Initialisiert die GameObjektListe)
    }

    /**
     * Methode zum Malen der Komponenten
     * alle GameObjekte, die sichtbar sind, werden in der Reihenfolge, wie sie in der GameobjektListe liegen, gezeichnet.
     * Dh. Objekte die am anfang der GameObjektListe Liegen werden zuerst also unten gezeichnet
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
       // System.out.println("started Painting"); //Statusmeldung in der Konsole

        Graphics2D g2d= (Graphics2D) g;

        if (allGameObjects!=null){
        for (int i = 0; i < allGameObjects.getSize(); i++) {
           GameObject aktuellesGameobjekt= allGameObjects.getGameObject(i);
           //zeichnet das Bild des aktuellen Gameobjekts, wenn dieses sichtbar ist
           if (aktuellesGameobjekt.isVisible()){
            g2d.drawImage(aktuellesGameobjekt.getImg(),aktuellesGameobjekt.getPositionX(),aktuellesGameobjekt.getPositionY(),null);

            //Zeichnet den Text, der mit dem Game Objekt verbunden ist
            if (aktuellesGameobjekt.getTextToDisplay()!=null&& !aktuellesGameobjekt.getTextToDisplay().isEmpty()){
                String text = aktuellesGameobjekt.getTextToDisplay();
                int x = aktuellesGameobjekt.getPositionX();
                int y = aktuellesGameobjekt.getPositionY();
                Font font = new Font("Georgia", Font.BOLD, 20);
                TextLayout textLayout = new TextLayout(text, font, g2d.getFontRenderContext());
                g2d.setPaint(Color.BLACK);
                textLayout.draw(g2d, x, y+32);
            }

           }
        }}

       // System.out.println("finished Painting"); //Statusmeldung in der Konsole
    }


}
