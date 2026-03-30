package maps;
import gameObject.*;
import gameObject.Menu;
import gameObject.butons.VisibilityToggler;
import gameObject.items.Drug;
import gameObject.items.DrugColection;
import gameObject.items.Item;
import helper.ImageLoader;

import java.awt.*;


/**
 * Klasse zum Bauen (Initialisation) des HauptRaumes
 */
public  class MainRoom implements MapBuilder{
    /**
     * Methode zum Bauen (Initialisation) des HauptRaumes
     * @param gameObjects Liste aller Gameobjekte der aktuellen Karte
     * @return Liste aller Gameobjekte nach dem Bauen (Initialisieren) der Karte
     */
    public static GameObjects buildMap(GameObjects gameObjects){
       gameObjects.addGameObjects(buildBackgrounds());
       gameObjects.addGameObjects(buildDecoration());
        gameObjects.addGameObjects(buildStorages());
        gameObjects.addGameObjects(buildMenus());
        gameObjects.addGameObjects(buildPlayer());
        return gameObjects;
    }


    private static GameObjects buildBackgrounds(){
        GameObjects gameObjects=new GameObjects();

        Background tempBackground =new Background( ImageLoader.loadImage("Räume/","Wohnzimmer.png",2));
        gameObjects.addGameObject(tempBackground);

        return gameObjects;
    }

    /**
     * Klasse zum Erstellen aller Lager
     * @return gameObject liste aller Lager
     */
    private static GameObjects buildStorages(){
        GameObjects gameObjects=new GameObjects();
        //Mouse Storage Bauen
        Storage mouseStorage =new Storage(ImageLoader.loadImage("empty.png"),"mouseStorage",100,0,0,1,1);
        mouseStorage.setVisible(true);
        mouseStorage.interactable=false;
        gameObjects.addGameObject(mouseStorage);
        return gameObjects;
    }

    /**
     * Klasse zum Erstellen aller Menüs/Container und ihrer Child objekte
     * @return Liste aller Menüs/Container
     */
    private static GameObjects buildMenus(){
        GameObjects gameObjects=new GameObjects();
        GameObjects ChildGameObjects=new GameObjects();


        //Menü des PlayerInventars erstellen
        Storage playerInventory = new Storage(ImageLoader.loadImage("InvSlot.png"),"playerInventory",9,0,32,10,5);
        playerInventory.setImg(ImageLoader.loadTiledImage("InvSlot.png",playerInventory.getColumns(),playerInventory.getRows()));
        playerInventory.setVisible(true);
        Item testItem =new Drug(DrugColection.getCocain());
        playerInventory.setItem(testItem,0);
        playerInventory.setAmount(0,3);
        gameObjects.addGameObject(playerInventory);
        ChildGameObjects.addGameObject(playerInventory);


        Menu menu =new Menu(ImageLoader.loadImage("TempPixel.png",32*10,32),false,10,10,256,32*10,32,ChildGameObjects);
        VisibilityToggler visibilityToggler = new VisibilityToggler(ImageLoader.loadImage("exit.png"),true,1,9*32,0,32,32,true,menu);
        menu.addMenuGameObject(visibilityToggler);
        gameObjects.addGameObject(menu);







        //Menü des Hauptinventars erstellen
        Menu storageMenu =new Menu(ImageLoader.loadImage("TempPixel.png",32*10,32),false,10,10,600,32*10,32,new GameObjects());
        VisibilityToggler visibilityTogglerMainStorage = new VisibilityToggler(ImageLoader.loadImage("exit.png"),true,1,9*32,0,32,32,true,storageMenu);
        storageMenu.addMenuGameObject(visibilityTogglerMainStorage);

        Storage mainstorage =new Storage(ImageLoader.loadImage("TempSprite.png"),"mainStorage",1,0,32,10,9);
        mainstorage.setImg(ImageLoader.loadTiledImage("InvSlot.png",mainstorage.getColumns(),mainstorage.getRows()));
        mainstorage.setVisible(true);
        storageMenu.addMenuGameObject(mainstorage);
        gameObjects.addGameObject(storageMenu);

        //Truhe/Button zum Öffnen des Hauptinventars erstellen
        VisibilityToggler visibilityTogglerMainStorage2 = new VisibilityToggler(ImageLoader.loadImage("Chest.png",1.5),true,4,100,500,96,96,false,storageMenu);
        gameObjects.addGameObject(visibilityTogglerMainStorage2);

        return gameObjects;
    }

    /**
     * Klasse zum Erstellen aller dekorativen Objekte
     * @return Liste aller dekorativen Objekte
     */
  private   static GameObjects buildDecoration(){

        //temporäre test Objekte
        GameObjects gameObjects =new GameObjects();
        TempObject tempObject=new TempObject(true,4,320,600);
        tempObject.setImg(ImageLoader.loadImage("Tisch.png"));
        tempObject.setCollidable(true);
        tempObject.setSizeX(200);
        tempObject.setSizeY(120);
        gameObjects.addGameObject(tempObject);


        TempObject tempObject1=new TempObject(true,4,420,226);
        tempObject1.setImg(ImageLoader.loadImage("Tür.png"));
        gameObjects.addGameObject(tempObject1);
        return gameObjects;
    }

    private static GameObjects buildPlayer(){
        GameObjects gameObjects =new GameObjects();
        Image[] idling = new Image[1];
        Image[] movingDown = new Image[6];
        idling[0]= ImageLoader.loadImage("frames_runningvorne/","ausgangspos.png",0.2);
        movingDown[0]= ImageLoader.loadImage("frames_runningvorne/","links_1.png",0.2);
        movingDown[1]=ImageLoader.loadImage("frames_runningvorne/","links_2.png",0.2);
        movingDown[2]=ImageLoader.loadImage("frames_runningvorne/","ausgangspos.png",0.2);
        movingDown[3]=ImageLoader.loadImage("frames_runningvorne/","rechts_1.png",0.2);
        movingDown[4]=ImageLoader.loadImage("frames_runningvorne/","rechts_2.png",0.2);
        movingDown[5]=movingDown[2];
        Player player = new Player(idling,true,110,512,512);
        player.setMovingDown(movingDown);
        gameObjects.addGameObject(player);

      return gameObjects;
    }


}
