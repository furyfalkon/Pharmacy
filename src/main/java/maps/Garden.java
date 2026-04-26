package maps;

import gameObject.*;

import helper.ImageLoader;

public class Garden  {
    public static Menu buildMap(){
        GameObjects gameObjects=new GameObjects();
        gameObjects.addGameObjects(buildBackgrounds());
        gameObjects.addGameObjects(buildCollisionBoxes());
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


    private static GameObjects buildCollisionBoxes() {
        GameObjects gameObjects = new GameObjects();


        CollisionBox c8 =new CollisionBox(0,300,1024,100);
        CollisionBox c2 =new CollisionBox(0,1020,1024,26);
        CollisionBox c3 =new CollisionBox( 0,510,60,300);
        CollisionBox c4 = new CollisionBox(0,800,500,300);
        CollisionBox c5 =new CollisionBox( 1000,0,60,1024);



        gameObjects.addGameObject(c2);
        gameObjects.addGameObject(c3);
        gameObjects.addGameObject(c4);
        gameObjects.addGameObject(c5);
        gameObjects.addGameObject(c8);
        return gameObjects;

    }


}


