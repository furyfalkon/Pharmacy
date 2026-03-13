package gameObject;

import java.awt.*;

public  abstract class GameObject {

    Image img;//Bild des GameObjektes
    String textToDisplay;
    boolean visible;//Sichtbarkeitsstatus
    int layer;//Layer in dem sas Bild gezeichnet wird, wenn es sichtbar ist(0 unten)

    //Position des Objektes (Linke obere ecke des Bildes)
    int positionX;
    int positionY;

    public boolean interactable;//Interagierbarkeitsstatus
    public boolean dragable;
    public boolean lockedToMouse;
    //Größe der "Hitbox" des Objektes
    public int ziseX;
    public int ziseY;

    boolean childObject;

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
     this.ziseX =0;
     this.ziseY =0;
     this.interactable=false;
     this.textToDisplay ="";
     this.childObject =false;
     this.dragable=false;
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
        this.ziseX =sizeX;
        this.ziseY =sizeY;
        this.interactable=true;
        this.textToDisplay="";
        this.childObject =false;
        this.dragable=false;
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

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    public String getTextToDisplay() {
        return textToDisplay;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setPosition(Point position) {
       this.positionX=position.x;
       this.positionY=position.y;
    }

    public boolean isInteractable() {
        return interactable;
    }

    public int getZiseX() {
        return ziseX;
    }

    public int getZiseY() {
        return ziseY;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setZiseX(int ziseX) {
        this.ziseX = ziseX;
    }

    public void setZiseY(int ziseY) {
        this.ziseY = ziseY;
    }

    public boolean isChildObject() {
        return childObject;
    }

    public void setChildObject(boolean childObject) {
        this.childObject = childObject;
    }

    public boolean isDragable() {
        return dragable;
    }

    public void setDragable(boolean dragable) {
        this.dragable = dragable;
    }

    public boolean isLockedToMouse() {
        return lockedToMouse;
    }

    public void setLockedToMouse(boolean lockedToMouse) {
        this.lockedToMouse = lockedToMouse;
    }
}
