package gameObject.butons;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Interactable;

import java.awt.*;

public class VisibilityToggler extends Button {
    GameObject gameObjectToToggleVisibility;
    public VisibilityToggler(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY, boolean isChildObject, GameObject gameObjectToToggleVisibility) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY,isChildObject);
        this.gameObjectToToggleVisibility=gameObjectToToggleVisibility;
    }

    @Override
    public GameObjects interact(GameObjects gameObjects) {
        if (gameObjectToToggleVisibility!=null) {
            if (gameObjectToToggleVisibility.isVisible()) {
                gameObjectToToggleVisibility.setVisible(false);
                gameObjects.updateGameObject(gameObjectToToggleVisibility);
                return gameObjects;
            }
            if (!gameObjectToToggleVisibility.isVisible()) {
                gameObjectToToggleVisibility.setVisible(true);
                gameObjects.updateGameObject(gameObjectToToggleVisibility);
                return gameObjects;
            }
        }




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



    public GameObject getGameObjectToToggleVisibility() {
        return gameObjectToToggleVisibility;
    }

    public void setGameObjectToToggleVisibility(GameObject gameObjectToToggleVisibility) {
        this.gameObjectToToggleVisibility = gameObjectToToggleVisibility;
    }
}
