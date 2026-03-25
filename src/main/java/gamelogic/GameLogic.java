package gamelogic;
import gameObject.*;
import gameObject.Menu;
import gameObject.butons.VisibilitySwitcher;
import gameObject.butons.VisibilityToggler;
import helper.Sorter;
import java.awt.*;


/**
 *Klasse in der alle Updates am Spiel ausgeführt werden
 */
public class GameLogic extends MouseInput{
    /**
     * Methode zum Updaten der GameObjekte
     * @param gameObjects die Liste der GameObjekte welche geupdatet wird
     * */
    public static GameObjects update(GameObjects gameObjects){

        //information für Statusmeldung in der Konsole
        int lastGamObjectLength=gameObjects.getSize();
        //System.out.println("started Updating");//Statusmeldung in der Konsole

        /*
        * Interagieren mit ausgewählten GameObject
        * */
            //ausgewähltes GameObject setzten
            GameObject selectedGameObject =mouseIsOnObjekt(gameObjects);

            //prüfen, ob ein Game Objekt ausgewählt wurde
            if (selectedGameObject!=null){

                //prüfen, ob die Maus gedrückt wurde
                if (MouseInput.mouseClicked) {

                    //Fall: es wurde auf ein Lager geklickt
                    if (selectedGameObject instanceof Storage) {
                    //Interagieren mit dem GameObjekt
                    gameObjects = ((Storage) selectedGameObject).interact(
                           gameObjects,                                                             //Übergabe der aktuellen Game Objekte
                           MouseInput.button,                                                       //Übergabe des gedrückten Knopfes
                           selectedGameObject.getPositionX(), selectedGameObject.getPositionY(),    //Übergabe der Lager Position
                           MouseInput.mouseX, MouseInput.mouseY                                     //Übergabe der Maus Position
                    );
                    }
                    //Fall: es wurde auf ein VisibilityToggler geklickt
                    if (selectedGameObject instanceof VisibilityToggler){
                    //Interagieren+ übergabe der aktuellen game Objekte
                    gameObjects = ((VisibilityToggler) selectedGameObject).interact(gameObjects);
                    }
                    if (selectedGameObject instanceof VisibilitySwitcher){
                        gameObjects=((VisibilitySwitcher) selectedGameObject).interact(gameObjects);
                    }



                //Mouse Clicked wieder au falsch setzen
                MouseInput.setMouseClicked(false);
                }
            }
        /*
        * Updated aller mit der Maus in Verbindung stehenden Objekte bezüglich ihrer Position
        * */
            gameObjects=moveMouse(gameObjects);

        /*
        * Durchführen aller mit dem Drücken von Tastaturtasten verbundenen aktionen
        * */
            char key =KeyInput.getAktiveKey();
            if (key!=' '){
                //E Taste wurde Gedrückt
                if (key=='e'){
                    gameObjects =openPayerInventory(gameObjects);// öffnet das SpielerInventar
                }
                //ESC Taste wurde Gedrückt
                if (key==27){
                    gameObjects =closeAllMenus(gameObjects);//Schließt alle Menüs
                }
                // Gedrückte Taste wieder auf lehr setzen
                KeyInput.aktiveKey=' ';
            }

        /*
        * alle child objekte Updaten
        * */
            gameObjects= updateChildObjekts(gameObjects);
        /*
        * die Game Object Liste Sortieren und so auf das Zeichnen vorbereiten
        * */
            gameObjects= Sorter.sortByLayers(gameObjects);

       /*
       * Folgendes sind statusmeldungen in Der Konsole
       * */
       //System.out.println("finished Updating");
        if (lastGamObjectLength!=gameObjects.getSize()){
        System.out.println("GameObject-length "+gameObjects.getSize());}

        /*Rückgabe der neuen (geupdateten) gameObjects*/
        return gameObjects;
    }

    /**
     * Methode zum Prüfen, ob die Maus auf einem sichtbaren Objekt ist (mit diesem Interagieren kann)
     * dabei werden alle Game Objekte durchsucht bis ein passendes objekt gefunden wurde oder alle geprüft wurden
     * deshalb ist es wichtig, dass die "Hit boxen" von objekten sich nicht überlagern, wenn diese beide Sichtbar sind und in der gleichen ebne sind!!!
     * @param gameObjects Liste der zu prüfenden Game Objekte
     * @return Null oder das Objekt auf dem die maus ist
     */
    private  static GameObject mouseIsOnObjekt(GameObjects gameObjects){
        //setzen der maus Position
        int mouseX =MouseInput.mouseX;
        int mouseY =MouseInput.mouseY;
        //Schleife über alle Game Objekte
        int size = gameObjects.getSize();
        for (int i = 1; i <=size; i++) {
            //Game Objekte der Reihe nach von oben nach unten Durchgehen
            GameObject gameObjectI =gameObjects.getGameObject(size-i);
            if (gameObjectI.isVisible()){                                                       //Sichtbarkeit Prüfen
                if (gameObjectI.isInteractable()){                                              //Interagierbarkeit Prüfen
                    if (gameObjectI.getPositionX()<mouseX){                                     //Maus Position Prüfen
                        if (gameObjectI.getPositionY()<mouseY){
                            if (gameObjectI.getPositionX()+gameObjectI.getSizeX()>mouseX){
                                if (gameObjectI.getPositionY()+gameObjectI.getSizeY()>mouseY){

                                    return gameObjectI;                                         //Game Objekt zurückgeben
                                }
                            }

                        }
                    }
                }
            }
        }
        return  null;   //nichts zurück geben, wenn nichts gefunden wurde
    }
/**
 * Methode zum Updaten aller auf die Maus bezogenen Objekte
 * wie dem MouseStorage oder gedraggten Objekten
 * @param gameObjects alle zu updatenden GameObjekte
 * */
    public  static GameObjects moveMouse(GameObjects gameObjects){
        //Prüfen, ob die maus bewegt wurde
        if (MouseInput.mouseMoved) {

            System.out.println("Moved");                                 //Konsolen ausgabe
            MouseInput.setMouseMoved(false);                             //Bewegungsstatus zurücksetzen
            Point mousePosition = new Point(MouseInput.mouseX,MouseInput.mouseY);//Mouse Position laden und speichern


            /*
            * Mouse Storage Updaten/bewegen
            * */
                Storage mouseStorage =Storage.getMouseStorage(gameObjects);     //Maus Storage laden
                mouseStorage.setPosition(mousePosition);                        //Position des Maus Storages ändern
                gameObjects=  mouseStorage.updateStorage(gameObjects);          //Game objekt liste Updaten und speichern

            /*
            * festgehaltene Objekte bewegen
            * */
                boolean dragging=false;  // status, ob bereits ein objekt gedraggt (mit der maus mitbewegt wird)

                //alle Game Objekte durchgehen
                for (int i = 0; i <gameObjects.getSize(); i++) {
                    //Das aktuelle Game Objekt Setzen
                    GameObject aktiveGameObject=gameObjects.getGameObject(i);
                    //Das aktuelle Game Objekt ist, nicht lehr
                    if (aktiveGameObject!=null){
                        //Das aktuelle Game Objekt ist an die maus gebunden und die Maus ist gedrückt
                        if (aktiveGameObject.isLockedToMouse()&& MouseInput.mousePressed){
                            //das aktuelle Game Objekt ist ein Menü
                            if (aktiveGameObject instanceof Menu){
                                //Errechnen und Speichern der absoluten Position
                                Point absolutePosition =new Point(
                                    (int) (mousePosition.getX()-((Menu) aktiveGameObject).getLocalMousePosition().getX()),
                                    (int) (mousePosition.getY()-((Menu) aktiveGameObject).getLocalMousePosition().getY())
                                );
                                //Setzen der neuen Position des Menüs
                                aktiveGameObject.setPosition(absolutePosition);
                            }else {
                                //Setzen der neuen Position des aktuellen Gameobjekts
                                aktiveGameObject.setPosition(mousePosition);
                            }

                            gameObjects.updateGameObject(aktiveGameObject);  //das aktuelle Game Objekt updaten
                            dragging=true;                                    //der Dragging Status wird auf wahr gesetzt


                        }else if (aktiveGameObject.isLockedToMouse()&& !MouseInput.mousePressed){
                            //Das aktuelle Game Objekt ist an die maus gebunden und die Maus ist nicht gedrückt
                            aktiveGameObject.setLockedToMouse(false);       //die Bindung zur Maus aufheben
                            aktiveGameObject.setInteractable(true);         //das aktuelle Game Objekt wieder interagierbar machen
                            gameObjects.updateGameObject(aktiveGameObject); //das aktuelle Game Objekt updaten

                    }
                }
            }

            //Die Maus ist gedrückt aber es wird noch kein Game Objekt gedraggt
            if (MouseInput.mousePressed && !dragging){
                GameObject onObjekt =mouseIsOnObjekt(gameObjects); //Ausgewähltes GameObjekt setzten
                if (onObjekt!=null){                               //prüfen, ob ein GameObjekt ausgewählt wurde
                    if (onObjekt.isDraggable()){                    //das ausgewählte GameObjekt ist Draggable
                        onObjekt.setLockedToMouse(true);           //das ausgewählte GameObjekt an die Maus Binden
                        onObjekt.setInteractable(false);           //das ausgewählte GameObjekt nicht interagierbar machen
                                                                        //mit gedraggten GameObjekten soll nicht interagiert werden
                        if (onObjekt instanceof Menu){              //prüfen, ob das ausgewählte GameObjekt ein menü ist
                            //Locale maus Position setzen
                            ((Menu) onObjekt).setLocalMousePosition(((Menu) onObjekt).calculateLocalMousePosition(mousePosition));
                        }
                        gameObjects.updateGameObject(onObjekt);   // das aktuelle GameObjekt updaten
                    }}

            }



       }
    return  gameObjects;
    }

/**
 * Methode zum Updaten aller ChildObjekte
 * also von Objekten, die Teil eines anderen Objektes sind
 * Updaten, heißt hierbei, das das Objekt in der GameObjektliste zum Zeichnen hinzugefügt wird
 * bzw. auf den neusten Stand geupdatet wird
 * @param gameObjects  alle zu updatenden GameObjekte
 * */
    public   static GameObjects updateChildObjekts(GameObjects gameObjects) {

        /*
        alle Game Objekte welche Child Objekte sind aus der haupt Game Objekt list Löschen
        */
        for (int i = 0; i < gameObjects.getSize(); i++) {         //alle GameObjekt durchgehen
            if (gameObjects.getGameObject(i)!=null) {                    //das GameObjekt ist nicht null
                if (gameObjects.getGameObject(i).isChildObject()) {      //das GameObjekt ist ein ChildObject
                    gameObjects.setGameObject(null, i);              //das GameObjekt nullen
                }
            }
        }

        for (int i = 0; i < gameObjects.getSize(); i++) {         //alle GameObjekt durchgehen
            GameObject aktiveGameObject = gameObjects.getGameObject(i);  //aktives GameObjekt setzen

            if (aktiveGameObject instanceof Storage) {                                  //Das GameObjekt ist ein Lager
               gameObjects=((Storage) aktiveGameObject).updateStorage(gameObjects);     //Das Lager Updaten
                                                                                    // → die Child Objekte der Hauptliste geupdatet hinzufügen
            }

            if (aktiveGameObject instanceof Menu){                                      //das GameObjekt ist ein Menü
                gameObjects=((Menu)aktiveGameObject).updateMenu(gameObjects);           //das Menü updaten
                                                                                    // → die Child Objekte der Hauptliste geupdatet hinzufügen
            }
        }
        return gameObjects;     // Die geupdateten Gameobjekte zurückgeben
    }


   /**
    * Methode zum Öffnen/Sichtbar machen des SpielerInventars
    * @param gameObjects  alle zu updatenden GameObjekte
    * */
   public static GameObjects openPayerInventory(GameObjects gameObjects){
       for (int i = 0; i <gameObjects.getSize(); i++) {           //alle GameObjekte durchgehen
            GameObject aktiveGameObject =gameObjects.getGameObject(i);   //das aktive GameObjekt setzen
            if (aktiveGameObject instanceof Menu){                              //das aktuelle Game Objekt ist ein Menü
               checkMenu(gameObjects, (Menu) aktiveGameObject);
            }
       }
        return gameObjects;     // Die geupdateten Gameobjekte zurückgeben
   }

   public static GameObjects checkMenu(GameObjects gameObjects,Menu aktiveGameObject){
       if (((Menu) aktiveGameObject).getMenuGameObjects()!=null){      //die ChildGameObjekte des Menüs sind nicht null
           //die ChildGameObjekte durchgehen
           for (int j = 0; j <((Menu) aktiveGameObject).getMenuGameObjects().getSize(); j++) {
               //das aktive ChildGameObjekt setzen
               GameObject childObject =((Menu) aktiveGameObject).getMenuGameObjects().getGameObject(j);
               if (childObject instanceof Storage){   //das aktive ChildGameObjekt ist ein Lager
                   if (((Storage) childObject).getName().equals("playerInventory")){ //das aktive ChildGameObjekt ist das SpielerInventar
                       aktiveGameObject.setVisible(true); //das Menü sichtbar machen

                   }
               }
               if (childObject instanceof Menu){
                  checkMenu(gameObjects, (Menu) childObject);
               }
           }
           gameObjects.updateGameObject(aktiveGameObject);  // das aktuelle GameObjekt updaten
       }
       return gameObjects;
   }
    /**
     * Methode zum Schließen/unsichtbar machen aller Menüs
     * @param gameObjects   alle zu updatenden GameObjekte
     * */
   public static GameObjects closeAllMenus(GameObjects gameObjects){
       for (int i = 0; i <gameObjects.getSize(); i++) {           //alle GameObjekte durchgehen
           GameObject aktiveGameObject =gameObjects.getGameObject(i);   //das aktive GameObjekt setzen
           if (aktiveGameObject instanceof Menu){                              //das aktuelle Game Objekt ist ein Menü
              aktiveGameObject.setVisible(false);                              //das Menü schließen/unsichtbar machen
           }
       }
       return gameObjects;      // Die geupdateten Gameobjekte zurückgeben

   }
}
