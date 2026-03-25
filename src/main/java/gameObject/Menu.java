package gameObject;

import gameObject.butons.VisibilityToggler;
import java.awt.*;
/**
 * Menüs/Container als spezielles GameObjekt welches andere Game Objekte enthält
 * */
public class Menu extends  GameObject  {

    GameObjects menuGameObjects;        //Lise der im Menü/Container gespeicherten GameObjekte
    Point localMousePosition;           //die Position der Maus im Menü


    /**
     * ein constructor für ein Menü
     * @param img Das Bild des Menüs
     * @param visible der Sichtbarkeitsstatus des Menüs
     * @param layer die Anzeigeebene
     * @param positionX die X-Position des Menüs
     * @param positionY die Y-Position des Menüs
     * @param sizeX die Breite des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param sizeY die Höhe des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param menuGameObjects Lise der im Menü/Container gespeicherten GameObjekte
     */
    public Menu(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY,GameObjects menuGameObjects) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY);
        this.menuGameObjects=menuGameObjects;
        setDraggable(true);
    }

    /**
     * Die Lokale Maus Position anhand der globalen maus position berechnen
     * @param globalMousePosition Die globale Maus Position
     * @return lokale Maus Position
     */
    public Point calculateLocalMousePosition(Point globalMousePosition){
        return new Point((int) (globalMousePosition.getX()- this.getPositionX()), (int) (globalMousePosition.getY()- this.getPositionY()));
    }

    /**Methode zum Hinzufügen von Menü Objekten
     * @param menuGameObject Die Liste der bisherigen Objekte
     * */
    public void addMenuGameObject(GameObject menuGameObject){
        this.menuGameObjects.addGameObject(menuGameObject);
    }

    /**
     * update Methode für ein menü und seine Child Objekte
     * @param gameObjects die aktuellen game Objekte
     * @return die geupdateten GameObjekte
     */
    public  GameObjects updateMenu(GameObjects gameObjects){
        Menu aktiveMenu = this;
        GameObjects aktiveMenuGameObjects = aktiveMenu.getMenuGameObjects();
        for (int j = 0; j <aktiveMenuGameObjects.getSize(); j++) {                      //alle Child Objekte durchgehen
            GameObject aktiveChildGameObject = aktiveMenuGameObjects.getGameObject(j);  //das aktuelle ChildGameObjekt speichern
            //die Absolute/globale position des child Objektes berechnen
            int absoluteChildObjectPositionX =aktiveMenu.getPositionX()+aktiveChildGameObject.getPositionX();
            int absoluteChildObjectPositionY =aktiveMenu.getPositionY()+aktiveChildGameObject.getPositionY();
            int absoluteChildObjectLayer= aktiveMenu.getLayer()+aktiveChildGameObject.getLayer();   //den absoluten/globalen layer des Child Objekts berechnen
            //Objekt sichtbarkeit im Globalen kontext berechnen
            boolean absoluteChildObjectVisibility=false;    //alle Objekte sind im absoluten/globalen Kontext nicht Sichtbar
            if (aktiveMenu.isVisible()){                    //außer das Menü ist sichtbar
                if (aktiveChildGameObject.isVisible()){     //und das Objekt ist im Lokalen Kontext(im Menü) auch sichtbar
                    absoluteChildObjectVisibility=true;
                }
            }
            //ein neues nun globales Game objekt erstellen welches alle wichtigen eigenschaften des Child Objektes beibehält
            GameObject globalChildGameObject = null;

                Image img =aktiveChildGameObject.getImg();
                if (aktiveChildGameObject instanceof Background){
                    globalChildGameObject= new Background(img);
                }

                   if (aktiveChildGameObject instanceof gameObject.butons.VisibilityToggler){
                       globalChildGameObject= new VisibilityToggler(img,false,0,0,0,aktiveChildGameObject.getSizeX(),aktiveChildGameObject.getSizeY(),false,((VisibilityToggler) aktiveChildGameObject).getGameObjectToToggleVisibility());
                    }

                if (aktiveChildGameObject instanceof Storage){
                    globalChildGameObject =new Storage(img,((Storage) aktiveChildGameObject).getName(),1,0,0,((Storage) aktiveChildGameObject).getColumns(),((Storage) aktiveChildGameObject).getRows());
                    ((Storage) globalChildGameObject).setItems(((Storage) aktiveChildGameObject).getItems());
                    ((Storage) globalChildGameObject).setAmounts(((Storage) aktiveChildGameObject).getAmounts());
                }
                if (aktiveChildGameObject instanceof TempObject){
                    globalChildGameObject = new TempObject(false,0,0,0);
                }
                if (aktiveChildGameObject instanceof Menu){
                    globalChildGameObject =new Menu(aktiveChildGameObject.getImg(),false,0,0,0,0,0,((Menu) aktiveChildGameObject).getMenuGameObjects());
                }

            //Wen das globale child Game Objekt erfolgreich initialisiert wurde und nicht mehr null ist
            if (globalChildGameObject != null){
                //werden die errechneten eigenschaften auf dieses übertragen und gespeichert
                globalChildGameObject.setPositionX(absoluteChildObjectPositionX);
                globalChildGameObject.setPositionY(absoluteChildObjectPositionY);
                globalChildGameObject.setLayer(absoluteChildObjectLayer);
                globalChildGameObject.setVisible(absoluteChildObjectVisibility);
                globalChildGameObject.setChildObject(true);
                gameObjects.addGameObject(globalChildGameObject);
                if (globalChildGameObject instanceof Storage){
                    gameObjects=((Storage) globalChildGameObject).updateStorage(gameObjects);
                }
            }
        }
        return gameObjects; //rückgabe der geupdateten game Objekte
    }

    //Getter und Setter

    public Point getLocalMousePosition() {
        return localMousePosition;
    }

    public void setLocalMousePosition(Point localMousePosition) {
        this.localMousePosition = localMousePosition;
    }

    public GameObjects getMenuGameObjects() {
        return menuGameObjects;
    }

    public void setMenuGameObjects(GameObjects menuGameObjects) {
        this.menuGameObjects = menuGameObjects;
    }
}
