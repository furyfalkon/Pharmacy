package gameObject.butons;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Interactable;

import java.awt.*;

public abstract class Button extends GameObject implements Interactable {


    public Button(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY, boolean isChildObject) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY);
        setChildObject(isChildObject);
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
