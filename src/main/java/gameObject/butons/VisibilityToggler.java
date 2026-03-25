package gameObject.butons;

import gameObject.GameObject;
import gameObject.GameObjects;
import main.GamePanel;

import java.awt.*;

/**
 * Ein Sichtbarkeitsschalter zum Ändern der Sichtbarkeit eines bestimmten objektes als spezieller Button
 */
public class VisibilityToggler extends Button {
    GameObject gameObjectToToggleVisibility;//das Objekt dessen Sichtbarkeit geändert wird


    /**
     * Methode zum Erstellen eines Sichtbarkeitsschalters
     * @param img Das Bild des Menüs
     * @param visible der Sichtbarkeitsstatus des Menüs
     * @param layer die Anzeigeebene
     * @param positionX die X-Position des Menüs
     * @param positionY die Y-Position des Menüs
     * @param sizeX die Breite des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param sizeY die Höhe des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param isChildObject ist ein Child Objekt
     * @param gameObjectToToggleVisibility das Objekt dessen Sichtbarkeit geändert wird
     */
    public VisibilityToggler(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY, boolean isChildObject, GameObject gameObjectToToggleVisibility) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY,isChildObject);
        this.gameObjectToToggleVisibility=gameObjectToToggleVisibility;
    }


    /**
     * Methode zum Interagieren mit dem VisibilityToggler
     * @param gameObjects   Liste der GameObjekte vor der Interaktion
     * @return Liste der GameObjekte nach der Interaktion
     */
    @Override
    public GameObjects interact(GameObjects gameObjects) {
        if (gameObjectToToggleVisibility!=null) {                           //wenn das objekt dessen Sichtbarkeit geändert wird nicht leer ist
            if (gameObjectToToggleVisibility.isVisible()) {                     //und es Sichtbar ist
                gameObjectToToggleVisibility.setVisible(false);                     //wird es unsichtbar gemacht
                gameObjects.updateGameObject(gameObjectToToggleVisibility);         //gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }
            if (!gameObjectToToggleVisibility.isVisible()) {                    //und es nicht sichtbar ist
                gameObjectToToggleVisibility.setVisible(true);                      //wird es sichtbar gesetzt
                gameObjects.updateGameObject(gameObjectToToggleVisibility);         //gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }
        }




        return gameObjects;
    }


    //Getter und Setter
    public GameObject getGameObjectToToggleVisibility() {
        return gameObjectToToggleVisibility;
    }

    public void setGameObjectToToggleVisibility(GameObject gameObjectToToggleVisibility) {
        this.gameObjectToToggleVisibility = gameObjectToToggleVisibility;
    }
}
