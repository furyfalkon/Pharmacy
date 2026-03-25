package maps;

import gameObject.*;
import gameObject.butons.VisibilityToggler;
import gameObject.items.Drug;
import gameObject.items.DrugColection;
import gameObject.items.Item;
import helper.ImageLoader;

public class Kitchen implements MapBuilder{
    public static GameObjects buildMap(){
        GameObjects gameObjects=new GameObjects();
        gameObjects.addGameObjects(buildBackgrounds());
        gameObjects.addGameObjects(buildDecoration());
        gameObjects.addGameObjects(buildStorages());
        gameObjects.addGameObjects(buildMenus());

        return gameObjects;
    }

    private static GameObjects buildBackgrounds(){
        GameObjects gameObjects=new GameObjects();

        Background tempBackground =new Background( ImageLoader.loadImage("Räume/","Muster_Raum.png",2));
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
        VisibilityToggler visibilityTogglerMainStorage2 = new VisibilityToggler(ImageLoader.loadImage("Chest.png",0.04),true,10,100,500,64,64,false,storageMenu);
        gameObjects.addGameObject(visibilityTogglerMainStorage2);

        return gameObjects;
    }

    /**
     * Klasse zum Erstellen aller dekorativen Objekte
     * @return Liste aller dekorativen Objekte
     */
    static GameObjects buildDecoration(){

        //temporäre test Objekte
        GameObjects gameObjects =new GameObjects();
        return gameObjects;
    }

}
