package gameObject.butons;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Player;
import gameObject.music.EinmaligesAbspielen;

import java.awt.*;

/**
 * Ein Sichtbarkeitsschalter zum Ändern der Sichtbarkeit eines bestimmten objektes als spezieller Button
 */
public class VisibilityToggler extends Button {
    GameObject gameObjectToToggleVisibility;    //das Objekt dessen Sichtbarkeit geändert wird
    int range; //Distance, aus der interagiert, werden kann -1 ist eine unendlich hohe distance

    /**
     * Methode zum Erstellen eines Sichtbarkeitsschalters
     * @param img Das Bild des Menüs
     * @param visible der Sichtbarkeitsstatus des Menüs
     * @param layer die Anzeigeebene
     * @param positionX die X-Position des Menüs
     * @param positionY die Y-Position des Menüs
     * @param sizeX die Breite des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param sizeY die Höhe des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param gameObjectToToggleVisibility das Objekt dessen Sichtbarkeit geändert wird
     */
    public VisibilityToggler(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY, GameObject gameObjectToToggleVisibility) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY);
        this.gameObjectToToggleVisibility=gameObjectToToggleVisibility;
        this.range=-1;
    }

    /**
     * Methode zum Interagieren mit dem VisibilityToggler
     * @param gameObjects   Liste der GameObjekte vor der Interaktion
     * @return Liste der GameObjekte nach der Interaktion
     */
    @Override
    public GameObjects interact(GameObjects gameObjects) {

        if (range<0||distanceToPlayer(gameObjects)<range){
        if (gameObjectToToggleVisibility!=null) {                           //wenn das objekt dessen Sichtbarkeit geändert wird nicht leer ist
            if (gameObjectToToggleVisibility.isVisible()) {                     //und es Sichtbar ist
                gameObjectToToggleVisibility.setVisible(false);                 //wird es unsichtbar gemacht
                EinmaligesAbspielen.play("schnippsen.wav");             //es wird ein Sound abgespielt
                gameObjects.updateGameObject(gameObjectToToggleVisibility);         //gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }
            if (!gameObjectToToggleVisibility.isVisible()) {                    //und es nicht sichtbar ist
                gameObjectToToggleVisibility.setVisible(true);                  //wird es sichtbar gesetzt
                EinmaligesAbspielen.play("boxoeffnen.wav");         //es wird ein Sound abgespielt
                gameObjects.updateGameObject(gameObjectToToggleVisibility);         //gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }
        }
        }




        return gameObjects;
    }

    private    int distanceToPlayer(GameObjects gameObjects){
        for (int i = 0; i < gameObjects.getSize(); i++) {
            GameObject aktiveGameObjekt = gameObjects.getGameObject(i);
        if (aktiveGameObjekt instanceof Player){
            Point buttonPos = new Point(this.getPositionX(),this.getPositionY());
            Point playerPos= new Point(aktiveGameObjekt.getPositionX(), aktiveGameObjekt.getPositionY());
             double distance= buttonPos.distance(playerPos);
             return (int)distance;

        }
        }
        return 0;
    }



    //Getter und Setter
    public GameObject getGameObjectToToggleVisibility() {
        return gameObjectToToggleVisibility;
    }

    public void setGameObjectToToggleVisibility(GameObject gameObjectToToggleVisibility) {
        this.gameObjectToToggleVisibility = gameObjectToToggleVisibility;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
