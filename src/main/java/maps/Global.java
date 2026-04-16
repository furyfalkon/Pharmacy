package maps;

import gameObject.*;
import gameObject.Menu;
import gameObject.butons.VisibilityToggler;
import gameObject.items.Drug;
import gameObject.items.DrugColection;
import gameObject.items.Item;
import helper.ImageLoader;

import java.awt.*;

public class Global {

    public static GameObjects buildGlobal() {
        GameObjects globalGameObjects = new GameObjects();
        globalGameObjects.addGameObject(buildMouseStorage());
        globalGameObjects.addGameObject(buildPlayerInventory());
        globalGameObjects.addGameObject(buildPlayer());
        return globalGameObjects;
    }

    private static GameObject buildMouseStorage(){

        //Mouse Storage Bauen
        Storage mouseStorage =new Storage(ImageLoader.loadImage("empty.png"),"mouseStorage",100,0,0,1,1);
        mouseStorage.setVisible(true);
        mouseStorage.interactable=false;
      return mouseStorage;
    }

    private static GameObject buildPlayerInventory(){

        //Menü des PlayerInventars erstellen
        Menu menu =new Menu(ImageLoader.loadImage("TempPixel.png",32*10,32),false,15,10,256,32*10,192,new GameObjects());
        VisibilityToggler visibilityToggler = new VisibilityToggler(ImageLoader.loadImage("exit.png"),true,1,9*32,0,32,32,true,menu);
        menu.addMenuGameObject(visibilityToggler);

        Storage playerInventory = new Storage(ImageLoader.loadImage("InvSlot.png"),"playerInventory",1,0,32,10,5);
        playerInventory.setImg(ImageLoader.loadTiledImage("InvSlot.png",playerInventory.getColumns(),playerInventory.getRows()));
        playerInventory.setVisible(true);
        Item testItem =new Drug(DrugColection.getCocain());
        playerInventory.setItem(testItem,0);
        playerInventory.setAmount(0,3);
        menu.addMenuGameObject(playerInventory);

        return menu;

    }

    private static GameObject buildPlayer(){

        Image idling;
        Image[] movingDown = new Image[11];
        idling= ImageLoader.loadImage("frames_runningvorne/","ausgangspos.png",0.2);
        movingDown[0]= ImageLoader.loadImage("frames_runningvorne/","links_1.png",0.2);
        movingDown[1]=movingDown[0];
        movingDown[2]=ImageLoader.loadImage("frames_runningvorne/","links_2.png",0.2);
        movingDown[3]=movingDown[2];
        movingDown[4]=ImageLoader.loadImage("frames_runningvorne/","ausgangspos.png",0.2);
        movingDown[5]=movingDown[4];
        movingDown[6]=ImageLoader.loadImage("frames_runningvorne/","rechts_1.png",0.2);
        movingDown[7]=movingDown[6];
        movingDown[8]=ImageLoader.loadImage("frames_runningvorne/","rechts_2.png",0.2);
        movingDown[9]=movingDown[8];
        movingDown[10]=movingDown[4];
        Image[] movingUp =new Image[9];
        movingUp[0]=ImageLoader.loadImage("running_hinten/","oben 1.png",3);
        movingUp[1]=ImageLoader.loadImage("running_hinten/","oben 2.png",3);
        movingUp[2]=ImageLoader.loadImage("running_hinten/","oben 3.png",3);
        movingUp[3]=ImageLoader.loadImage("running_hinten/","oben 4.png",3);
        movingUp[4]=ImageLoader.loadImage("running_hinten/","oben 5.png",3);
        movingUp[5]=ImageLoader.loadImage("running_hinten/","oben 6.png",3);
        movingUp[6]=ImageLoader.loadImage("running_hinten/","oben 7.png",3);
        movingUp[7]=ImageLoader.loadImage("running_hinten/","oben 8.png",3);
        movingUp[8]=movingUp[0];
        Player player = new Player(idling,true,9,600,512);
        player.setMovingDown(movingDown);
        player.setMovingUp(movingUp);

        return player;
    }

}
