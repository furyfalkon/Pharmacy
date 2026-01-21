package gameObject;

import java.awt.*;

public abstract class GameObject {
    Image img;
    boolean visible;

    int layer;
    int positionX;
    int positionY;
    public int sizeX;
    public int sizeY;
    public GameObject(Image img,boolean visible,int layer,int positionX,int positionY ,int sizeX, int sizeY){
     this.img=img;
     this.visible=visible;
     this.layer=layer;
     this.positionX =positionX;
     this.positionY=positionY;
     this.sizeX=sizeX;
     this.sizeY =sizeY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
