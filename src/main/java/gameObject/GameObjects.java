package gameObject;
import java.util.ArrayList;

/**
 * Klasse zum Verwalten und Speichern von GameObjekten
 */
public class GameObjects  {
    ArrayList<GameObject> gameObjects = new ArrayList<>();//Liste zum Speichern aller Game Objekte

    /**
     * Lehrer construktor -> Neue Game Objekt listen sind standarmäßig lehr
     */
    public GameObjects(){
    }

    /**
     * Methode zum Bekommen der GameObjekt Liste
     * @return Game Objekt Liste
     */
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Methode zum Setzen der Gameobjekte Liste (bereits vorhandener Inhalt wird überschrieben)
     * @param gameObjects zu setzende GameObjekt Liste
     */
    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Methode zum Hinzufügen eines neuen GameObjektes zu einer bereits bestehenden Liste
     * @param gameObject neues Game Objekt
     */
    public void addGameObject(GameObject gameObject){this.gameObjects.add(gameObject);}

    /**
     * Methode zum Hinzufügen einer Liste an GameObjekten zu einer bereits bestehenden Liste
     * @param gameObjects   Liste der hinzuzufügenden game Objekte
     */
    public void addGameObjekts(GameObjects gameObjects){
        for (int i = 0; i < gameObjects.gameObjects.size(); i++) {
        this.gameObjects.add(gameObjects.gameObjects.get(i));
        }
    }

    public void setGameObject(GameObject gameObject, int pos){
        if (this.gameObjects.size()>pos){
            this.gameObjects.set(pos,gameObject);
        }
    }

    public GameObjects updateGameObject(GameObject gameObject){
        for (int i = 0; i <this.gameObjects.size(); i++) {
          if(  this.gameObjects.get(i)==gameObject){
              this.gameObjects.set(i,gameObject);
          }
        }
        return this;
    }



}
