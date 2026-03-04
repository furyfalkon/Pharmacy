package maps;
import gameObject.*;
import gameObject.items.Drug;
import gameObject.items.DrugColection;
import gameObject.items.Item;
import gamelogic.GameLogic;
import helper.ImageLoader;

/**
 * Klasse zum Bauen (Initialisiern) der Karte
 */
public  class MapBuilder {
    /**
     * Methode zum Bauen (Initialisiern) der Karte
     * @param gameObjects Liste aller Gameobjekte der aktuellen Karte
     * @return Liste aller Gameobjekte nach dem Buen (Initialisieren) der Karte
     */
    public  static GameObjects buildMap(GameObjects gameObjects){
       gameObjects.addGameObjekts(buildBackgrounds());
        gameObjects.addGameObjekts(buildStorges());



        return gameObjects;
    }


    private static GameObjects buildBackgrounds(){
        GameObjects gameObjects=new GameObjects();
        
        Background tempBackground =new Background( ImageLoader.loadImage("Räume/","Wohnzimmer.png",2));
        gameObjects.addGameObjekt(tempBackground);

        return gameObjects;
    }

    /**
     * Klasse zum Erstellen aller Lager
     * @return gameObject liste aller Lager
     */
    private static GameObjects buildStorges(){
        GameObjects gameObjects=new GameObjects();

        Storage mouseStorage =new Storage(ImageLoader.loadImage("empty.png"),"mouseStorage",10,0,0,1,1);
        mouseStorage.setVisible(true);
        mouseStorage.interactable=false;
        gameObjects.addGameObjekt(mouseStorage);

        Storage playerInventory = new Storage(ImageLoader.loadImage("InvSlot.png"),"playerInventory",9,64,0,10,5);
        playerInventory.setImg(ImageLoader.tileImage("InvSlot.png",playerInventory.getColums(),playerInventory.getRows()));
        playerInventory.setVisible(true);
        Item testItem =new Drug(DrugColection.getCocain());
      //  gameObjects.addGameObjekt(testItem);
        playerInventory.setItem(testItem,0);
        playerInventory.setAmount(0,2);
        gameObjects.addGameObjekt(playerInventory);



        Storage mainstorage =new Storage(ImageLoader.loadImage("TempSprite.png"),"mainstorage",9,22*32,22*32,10,9);
        mainstorage.setImg(ImageLoader.tileImage("InvSlot.png",mainstorage.getColums(),mainstorage.getRows()));
        mainstorage.setVisible(true);

        gameObjects.addGameObjekt(mainstorage);

        return gameObjects;
    }



}
