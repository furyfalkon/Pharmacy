package maps;
import gameObject.*;
import gameObject.butons.VisibilityToggler;
import gameObject.items.Drug;
import gameObject.items.DrugColection;
import gameObject.items.Item;
import helper.ImageLoader;

/**
 * Klasse zum Bauen (Initialisiern) der Karte
 */
public  class MainRoom implements MapBuilder{
    /**
     * Methode zum Bauen (Initialisiern) der Karte
     * @param gameObjects Liste aller Gameobjekte der aktuellen Karte
     * @return Liste aller Gameobjekte nach dem Buen (Initialisieren) der Karte
     */
    public static GameObjects buildMap(GameObjects gameObjects){
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

        Storage mouseStorage =new Storage(ImageLoader.loadImage("empty.png"),"mouseStorage",100,0,0,1,1);
        mouseStorage.setVisible(true);
        mouseStorage.interactable=false;
        gameObjects.addGameObject(mouseStorage);





        Storage mainstorage =new Storage(ImageLoader.loadImage("TempSprite.png"),"mainstorage",9,22*32,22*32,10,9);
        mainstorage.setImg(ImageLoader.loadTiledImage("InvSlot.png",mainstorage.getColumns(),mainstorage.getRows()));
        mainstorage.setVisible(true);

        gameObjects.addGameObject(mainstorage);

        return gameObjects;
    }

    private static GameObjects buildMenus(){
        GameObjects gameObjects=new GameObjects();
        GameObjects ChildGameObjects=new GameObjects();



        Storage playerInventory = new Storage(ImageLoader.loadImage("InvSlot.png"),"playerInventory",9,0,32,10,5);
        playerInventory.setImg(ImageLoader.loadTiledImage("InvSlot.png",playerInventory.getColumns(),playerInventory.getRows()));
        playerInventory.setVisible(true);
        Item testItem =new Drug(DrugColection.getCocain());
        playerInventory.setItem(testItem,0);
        playerInventory.setAmount(0,3);
        gameObjects.addGameObject(playerInventory);
        ChildGameObjects.addGameObject(playerInventory);


        Menu menu =new Menu(ImageLoader.loadImage("TempPixel.png",32*10,32),true,10,10,256,32*10,32,ChildGameObjects);
        VisibilityToggler visibilityToggler = new VisibilityToggler(ImageLoader.loadImage("TempSprite.png"),true,1,0,0,32,32,true,menu);
        menu.addMenuGameObject(visibilityToggler);
        gameObjects.addGameObject(menu);
        VisibilityToggler visibilityToggler2 = new VisibilityToggler(ImageLoader.loadImage("TempSprite.png"),true,10,0,0,32,32,false,menu);
        gameObjects.addGameObject(visibilityToggler2);
        return gameObjects;
    }



}
