package maps;

import gameObject.*;
import gameObject.butons.VisibilityToggler;
import helper.ImageLoader;

public class Garden  {
    public static Menu buildMap(){
        GameObjects gameObjects=new GameObjects();
        gameObjects.addGameObjects(buildBackgrounds());
       // gameObjects.addGameObjects(buildCollisionBoxes());
        Menu garden =new Menu(ImageLoader.loadImage("empty.png"),true,0,0,0,1024,1024,gameObjects);
        garden.setInteractable(false);
        return garden;
    }


    private static GameObjects buildBackgrounds(){
        GameObjects gameObjects=new GameObjects();

        Background tempBackground =new Background( ImageLoader.loadImage("Räume/","Garten_Tor.png"));
        gameObjects.addGameObject(tempBackground);

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


        TempObject tempObject1=new TempObject(true,4,420,226);
        tempObject1.setImg(ImageLoader.loadImage("Räume/Türen/","Tür2.png",0.3));
        gameObjects.addGameObject(tempObject1);
        return gameObjects;
    }


    private static GameObjects buildCollisionBoxes() {

return null;
    }


}


