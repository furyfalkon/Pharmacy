package main;



import gameObject.GameObject;
import gameObject.GameObjekts;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
GameObjekts gameObjekts;
    final int orignialTileZise = 32;
    final int colums =32;
    final int rows =32;
    final int defaultwidth = orignialTileZise*colums;
    final int defaultheight = orignialTileZise*rows;
    final int FpsUps=30;
    Thread gameTread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(defaultwidth,defaultheight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
    }

    private void startGameTread(){
        gameTread =new Thread(this);
        gameTread.start();
    }
    @Override
    public void run() {
        startGameTread();
        GameLoop gameLoop= new GameLoop();
        gameObjekts =new GameObjekts();
        gameLoop.startGameLoop(gameTread,gameObjekts,FpsUps);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;

        for (int i = 0; i <gameObjekts.getGameObjects().size(); i++) {
           GameObject aktuellesGameobjekt= gameObjekts.getGameObjects().get(i);
           if (aktuellesGameobjekt.isVisible()){
            g2d.drawImage(aktuellesGameobjekt.getImg(),aktuellesGameobjekt.getPositionX(),aktuellesGameobjekt.getPositionY(),null);
           }
        }

        System.out.println("Painting");
    }




}
