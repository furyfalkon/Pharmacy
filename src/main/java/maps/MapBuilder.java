package maps;

import gameObject.GameObjects;
import gameObject.Menu;
import gameObject.butons.VisibilitySwitcher;
import helper.ImageLoader;

public class MapBuilder {

    public static GameObjects buildAll(GameObjects gameObjects) {

        gameObjects.addGameObjects(Global.buildGlobal());


        Menu mainRoom = MainRoom.buildMap();
        Menu garden = Garden.buildMap();
        garden.setVisible(false);
        VisibilitySwitcher switcher = new VisibilitySwitcher(ImageLoader.loadImage("Räume/Türen/","Tür2.png",0.3),true,4,420,226,145,280,mainRoom,garden);
        switcher.setRange(100);
        mainRoom.addMenuGameObject(switcher);

        VisibilitySwitcher switcher2 = new VisibilitySwitcher(ImageLoader.loadImage("Räume/Türen/","Tür2.png",0.3),true,4,420,500,145,280,garden,mainRoom);
        switcher2.setRange(100);
        garden.addMenuGameObject(switcher2);

        gameObjects.addGameObject(mainRoom);
        gameObjects.addGameObject(garden);



        return gameObjects;
    }
}
