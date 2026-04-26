package gameObject.butons;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Interactable;

import java.awt.*;

/**
 * Knöpfe als Spezielles GameObjekt
 */
public abstract class Button extends GameObject implements Interactable {

    /**
     * Methode zum Erstellen eines Knopfes
     * @param img Das Bild des Menüs
     * @param visible der Sichtbarkeitsstatus des Menüs
     * @param layer die Anzeigeebene
     * @param positionX die X-Position des Menüs
     * @param positionY die Y-Position des Menüs
     * @param sizeX die Breite des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param sizeY die Höhe des Menüs (des bereiches mit dem Interagiert werden kann)
     */
    public Button(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY);
    }

    @Override
    public GameObjects interact(GameObjects gameObjects) {
        return gameObjects;
    }

    @Override
    public GameObjects interact(GameObjects gameObjects, int button) {
        return gameObjects;
    }

    @Override
    public GameObjects interact(GameObjects gameObjects, int button, int xPosObjekt, int yPosObjekt, int xMouse, int yMouse) {
        return gameObjects;
    }
}
