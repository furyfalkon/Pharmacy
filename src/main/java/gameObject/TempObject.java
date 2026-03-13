package gameObject;

import helper.ImageLoader;

import java.awt.*;

public class TempObject extends GameObject{

    public TempObject( boolean visible, int layer, int positionX, int positionY) {
        super(ImageLoader.loadImage("TempSprite.png"), visible, layer, positionX, positionY);
    }
}
