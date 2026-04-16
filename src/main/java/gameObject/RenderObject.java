package gameObject;

import java.awt.*;

public class RenderObject extends GameObject{
    public RenderObject(GameObject gameObject) {
        super(gameObject.getImg(), gameObject.isVisible(), gameObject.getLayer(),gameObject.getPositionX(),gameObject.getPositionY());
    }
}
