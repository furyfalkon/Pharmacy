package gameObject;

import helper.ImageLoader;

import java.awt.*;

public class CollisionBox extends GameObject {

    public CollisionBox( int positionX, int positionY, int sizeX, int sizeY) {
        super(ImageLoader.loadImage("empty.png"), false, 0, positionX, positionY, sizeX, sizeY);
        this.setInteractable(false);
        this.setCollidable(true);
    }
}
