package gameObject;

import java.awt.*;

public  abstract class GameObject {

    Image img;//Bild des GameObjektes
    boolean visible;//Sichtbarkeitsstatus
    int layer;//Layer in dem sas Bild gezeichnet wird, wenn es sichtbar ist(0 unten)

    //Position des Objektes (Linke obere ecke des Bildes)
    int positionX;
    int positionY;

    public boolean interactable;//Interagierbarkeitsstatus
    //Größe der "Hitbox" des Objektes
    public int sizeX;
    public int sizeY;

    /**
     * Constructor für ein GameObjekt mit dem nicht interagiert werden kann
     * @param img
     * @param visible
     * @param layer
     * @param positionX
     * @param positionY
     */
    public GameObject(Image img,boolean visible,int layer,int positionX,int positionY){
     this.img=img;
     this.visible=visible;
     this.layer=layer;
     this.positionX =positionX;
     this.positionY=positionY;
     this.sizeX=0;
     this.sizeY =0;
     this.interactable=false;
    }

    /**
     * Constructor für ein GameObjekt mit dem interagiert werden kann
     * @param img
     * @param visible
     * @param layer
     * @param positionX
     * @param positionY
     * @param sizeX
     * @param sizeY
     */
    public GameObject(Image img,boolean visible,int layer,int positionX,int positionY ,int sizeX, int sizeY){
        this.img=img;
        this.visible=visible;
        this.layer=layer;
        this.positionX =positionX;
        this.positionY=positionY;
        this.sizeX=sizeX;
        this.sizeY =sizeY;
        this.interactable=true;
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

    public boolean isInteractable() {
        return interactable;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}
