package gameObject;

public interface Interactable {


    public abstract GameObjects interact(GameObjects gameObjects);
    /**
     * interaction for a Button where the Coordinate of the mouse is irrelevant
     * @param button clicked button
     */
    public abstract GameObjects interact(GameObjects gameObjects,int button);

    public abstract GameObjects interact(GameObjects gameObjects,int button,int xPosObjekt,int yPosObjekt,int xMouse,int yMouse);
}
