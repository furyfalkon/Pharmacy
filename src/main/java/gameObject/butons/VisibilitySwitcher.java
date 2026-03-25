package gameObject.butons;

import gameObject.GameObjects;
import main.GamePanel;

import java.awt.*;

public class VisibilitySwitcher extends Button {

    GameObjects gameObjectsToToggleVisibility;    //das Objekt dessen Sichtbarkeit geändert wird

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
     * @param gameObjectsToToggleVisibility Die gameObjekte deren Sichtbarkeit geändert wird
     */
    public VisibilitySwitcher(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY, boolean isChildObject, GameObjects gameObjectsToToggleVisibility) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY,isChildObject);
        this.gameObjectsToToggleVisibility=gameObjectsToToggleVisibility;
    }


    /**
     * Methode zum Interagieren mit dem VisibilityToggler
     * @param gameObjects   Liste der GameObjekte vor der Interaktion
     * @return Liste der GameObjekte nach der Interaktion
     */
    @Override
    public GameObjects interact(GameObjects gameObjects) {
        if (gameObjectsToToggleVisibility!=null) {                           //wenn das objekt dessen Sichtbarkeit geändert wird nicht leer ist
            if (gameObjectsToToggleVisibility.getGameObject(0).isVisible()) {                     //und es Sichtbar ist
                gameObjectsToToggleVisibility.getGameObject(0).setVisible(false);                     //wird es unsichtbar gemacht
                gameObjects.updateGameObject(gameObjectsToToggleVisibility.getGameObject(0));//gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }else if (!gameObjectsToToggleVisibility.getGameObject(0).isVisible()) {                    //und es nicht sichtbar ist
                gameObjectsToToggleVisibility.getGameObject(0).setVisible(true);                      //wird es sichtbar gesetzt
                gameObjects.updateGameObject(gameObjectsToToggleVisibility.getGameObject(0));         //gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }
            if (gameObjectsToToggleVisibility.getGameObject(1).isVisible()) {                     //und es Sichtbar ist
                gameObjectsToToggleVisibility.getGameObject(1).setVisible(false);                     //wird es unsichtbar gemacht
                gameObjects.updateGameObject(gameObjectsToToggleVisibility.getGameObject(1));
            }else if (!gameObjectsToToggleVisibility.getGameObject(1).isVisible()) {                    //und es nicht sichtbar ist
                gameObjectsToToggleVisibility.getGameObject(1).setVisible(true);                      //wird es sichtbar gesetzt
                gameObjects.updateGameObject(gameObjectsToToggleVisibility.getGameObject(1));         //gespeichert
                return gameObjects;                                                 //und zurück gegeben
            }
        }
        return gameObjects;
    }


    //Getter und Setter
    public GameObjects getGameObjectsToToggleVisibility() {
        return gameObjectsToToggleVisibility;
    }

    public void setGameObjectsToToggleVisibility(GameObjects gameObjectsToToggleVisibility) {
        this.gameObjectsToToggleVisibility = gameObjectsToToggleVisibility;
    }

}
