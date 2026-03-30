package gameObject;
import java.awt.*;
/**
 * Abstraktes Game Objekt welches als Basis für alle Objekte im Spiel dient
 * */
public  abstract class GameObject {

    //Eigenschaften der Darstellung
    Image img;              //Bild des GameObjektes
    String textToDisplay;   //Mit dem Objekt verbundener Text
    boolean visible;        //Sichtbarkeitsstatus
    int layer;              //Layer in dem sas Bild gezeichnet wird, wenn es sichtbar ist(0 unten)

    //Position des Objektes (Linke obere ecke des Bildes)
    int positionX;
    int positionY;

    //Eigenschaften der Interaktion
    public boolean interactable;        //Interagierbarkeitsstatus
    public boolean collidable;          //Kolidierbarkeitsstatus
    public int sizeX;                   //Breite der "Hitbox" des Objektes
    public int sizeY;                   //Höhe der "Hitbox" des Objektes
    public boolean draggable;           //Verschiebbarkeitsstatus
    public boolean lockedToMouse;       //Status der Bindung an die Maus (Das Objekt wird mit der Maus mitbewegt)

    //Einordnung als Unter/ChildObjekt eines anderen Objektes
    boolean childObject;

    /**
     * Constructor für ein GameObjekt mit dem nicht interagiert werden kann
     * @param img  Das darzustellende Bild
     * @param visible  der Sichtbarkeitsstatus
     * @param layer    die Anzeigeebene
     * @param positionX die X-Koordinate des Objektes
     * @param positionY die Y-Koordinate des Objektes
     */
    public GameObject(Image img,boolean visible,int layer,int positionX,int positionY){
     this.img=img;
     this.visible=visible;
     this.layer=layer;
     this.positionX =positionX;
     this.positionY=positionY;
     this.sizeX =0;
     this.sizeY =0;
     this.interactable=false;
     this.textToDisplay ="";
     this.childObject =false;
     this.draggable =false;
     this.collidable=false;
    }

    /**
     * Constructor für ein GameObjekt mit dem interagiert werden kann
     * @param img  Das darzustellende Bild
     * @param visible  der Sichtbarkeitsstatus
     * @param layer    die Anzeigeebene
     * @param positionX die X-Koordinate des Objektes
     * @param positionY die Y-Koordinate des Objektes
     * @param sizeX Breite der "Hitbox" des Objektes
     * @param sizeY Höhe der "Hitbox" des Objektes
     */
    public GameObject(Image img,boolean visible,int layer,int positionX,int positionY ,int sizeX, int sizeY){
        this.img=img;
        this.visible=visible;
        this.layer=layer;
        this.positionX =positionX;
        this.positionY=positionY;
        this.sizeX =sizeX;
        this.sizeY =sizeY;
        this.interactable=true;
        this.textToDisplay="";
        this.childObject =false;
        this.draggable =false;
        this.collidable=false;
    }

    //Methoden zum Setzen und Abrufen aller Eigenschaften des abstrakten GameObjektes

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

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public boolean isChildObject() {
        return childObject;
    }

    public void setChildObject(boolean childObject) {
        this.childObject = childObject;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isLockedToMouse() {
        return lockedToMouse;
    }

    public void setLockedToMouse(boolean lockedToMouse) {
        this.lockedToMouse = lockedToMouse;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
}
