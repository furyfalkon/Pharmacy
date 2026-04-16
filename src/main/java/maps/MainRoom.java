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
public  class MainRoom{
    /**
     * Methode zum Bauen (Initialisation) des HauptRaumes
     * @return Liste aller Gameobjekte nach dem Bauen (Initialisieren) der Karte
     */
    public static Menu buildMap(){
        GameObjects gameObjects=new GameObjects();
       gameObjects.addGameObjects(buildBackgrounds());
       gameObjects.addGameObjects(buildDecoration());
        gameObjects.addGameObjects(buildMenus());
        gameObjects.addGameObjects(buildCollisionBoxes());
        Menu mainRoom =new Menu(ImageLoader.loadImage("empty.png"),true,0,0,0,1024,1024,gameObjects);
        mainRoom.setInteractable(false);
        return mainRoom;
    }


    private static GameObjects buildBackgrounds(){
        GameObjects gameObjects=new GameObjects();

        Background tempBackground =new Background( ImageLoader.loadImage("Räume/","Wohnzimmer.png",2));
        gameObjects.addGameObject(tempBackground);

        return gameObjects;
    }


    /**
     * Klasse zum Erstellen aller Menüs/Container und ihrer Child objekte
     * @return Liste aller Menüs/Container
     */
    private static GameObjects buildMenus(){
        GameObjects gameObjects=new GameObjects();




        //Menü des Hauptinventars erstellen
        Menu storageMenu =new Menu(ImageLoader.loadImage("TempPixel.png",32*10,32),false,10,10,600,32*10,320,new GameObjects());
        VisibilityToggler visibilityTogglerMainStorage = new VisibilityToggler(ImageLoader.loadImage("exit.png"),true,1,9*32,0,32,32,true,storageMenu);
        storageMenu.addMenuGameObject(visibilityTogglerMainStorage);

        Storage mainstorage =new Storage(ImageLoader.loadImage("TempSprite.png"),"mainStorage",1,0,32,10,9);
        mainstorage.setImg(ImageLoader.loadTiledImage("InvSlot.png",mainstorage.getColumns(),mainstorage.getRows()));
        mainstorage.setVisible(true);
        storageMenu.addMenuGameObject(mainstorage);
        gameObjects.addGameObject(storageMenu);

        //Truhe/Button zum Öffnen des Hauptinventars erstellen
        VisibilityToggler visibilityTogglerMainStorage2 = new VisibilityToggler(ImageLoader.loadImage("Chest.png",1.5),true,1,100,500,96,96,false,storageMenu);
        visibilityTogglerMainStorage2.setCollidable(true);
        visibilityTogglerMainStorage2.setRange(200);
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
        tempObject.setImg(ImageLoader.loadImage("Tisch.png",0.35));
        tempObject.setCollidable(true);
        tempObject.setSizeX(260);
        tempObject.setSizeY(120);
        gameObjects.addGameObject(tempObject);



        return gameObjects;
    }


    private static GameObjects buildCollisionBoxes(){
      GameObjects gameObjects = new GameObjects();
      CollisionBox c1= new CollisionBox(0,400,400,100);
      CollisionBox c7 = new CollisionBox(580,400,500,100);
      CollisionBox c8 =new CollisionBox(0,300,1024,100);
      CollisionBox c2 =new CollisionBox(0,1020,1024,26);
      CollisionBox c3 =new CollisionBox( 0,510,60,300);
      CollisionBox c4 = new CollisionBox(0,800,26,300);
      CollisionBox c5 =new CollisionBox( 1000,800,60,300);
      CollisionBox c6 = new CollisionBox(950,510,26,300);

      gameObjects.addGameObject(c1);
      gameObjects.addGameObject(c2);
      gameObjects.addGameObject(c3);
      gameObjects.addGameObject(c4);
      gameObjects.addGameObject(c5);
      gameObjects.addGameObject(c6);
      gameObjects.addGameObject(c7);
      //gameObjects.addGameObject(c8);
      return gameObjects;


    }


}
