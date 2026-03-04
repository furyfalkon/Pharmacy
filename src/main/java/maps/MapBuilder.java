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
        gameObjects.addGameObjekts(buildMenus());


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
    private static GameObjects buildStorges(){
        GameObjects gameObjects=new GameObjects();

        Storage mouseStorage =new Storage(ImageLoader.loadImage("empty.png"),"mouseStorage",10,0,0,1,1);
        mouseStorage.setVisible(true);
        mouseStorage.interactable=false;
        gameObjects.addGameObject(mouseStorage);

        Storage playerInventory = new Storage(ImageLoader.loadImage("InvSlot.png"),"playerInventory",9,64,0,10,5);
        playerInventory.setImg(ImageLoader.loadTiledImage("InvSlot.png",playerInventory.getColums(),playerInventory.getRows()));
        playerInventory.setVisible(true);
        Item testItem =new Drug(DrugColection.getCocain());
        playerInventory.setItem(testItem,0);
        playerInventory.setAmount(0,3);
        gameObjects.addGameObject(playerInventory);



        Storage mainstorage =new Storage(ImageLoader.loadImage("TempSprite.png"),"mainstorage",9,22*32,22*32,10,9);
        mainstorage.setImg(ImageLoader.loadTiledImage("InvSlot.png",mainstorage.getColums(),mainstorage.getRows()));
        mainstorage.setVisible(true);

        gameObjects.addGameObject(mainstorage);

        return gameObjects;
    }

    private static GameObjects buildMenus(){
        GameObjects gameObjects=new GameObjects();
        GameObjects ChildGameObjects=new GameObjects();

        TempObject temp1 = new TempObject(true,1,0,0);
        ChildGameObjects.addGameObject(temp1);
        TempObject temp2 = new TempObject(true,1,32,32);
        ChildGameObjects.addGameObject(temp2);

        Menu menu =new Menu(ImageLoader.loadImage("emty.png"),true,10,10,256,100,100,ChildGameObjects);

        gameObjects.addGameObject(menu);
        return gameObjects;
    }



}
