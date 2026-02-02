package helper;

import gameObject.GameObject;
import gameObject.GameObjects;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    public static GameObjects sortByLayers(GameObjects gameObjects){
         Collections.sort(gameObjects.getGameObjects(),Comparator.comparingInt(GameObject::getLayer));
         return gameObjects;
    }
}
