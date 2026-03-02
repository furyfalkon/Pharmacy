package gamelogic;

import gameObject.*;
import gameObject.items.Item;
import helper.Sorter;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *Klasse in der alle Updates am Spiel ausgeführt werden
 */
public class GameLogic extends MouseInput{

    public static GameObjects update(GameObjects gameObjects, ActionEvent actionEvent){
        int lastGamObjectLength=gameObjects.getGameObjects().size();
        //System.out.println("started Updating");//Statusmeldung in der Konsole

        /*
        * Interagieren mit ausgewählten GameObject
        * */
       GameObject selectedGameObject =mouseIsOnObjekt(gameObjects);
        if (selectedGameObject!=null){
           System.out.println("in Objekt");
            if (selectedGameObject instanceof Storage) {
               gameObjects= ((Storage) selectedGameObject).interact(gameObjects,MouseInput.button, selectedGameObject.getPositionX(),selectedGameObject.getPositionY(),MouseInput.mouseX,MouseInput.mouseY);
            }

        }
        gameObjects=moveMouse(gameObjects);






        gameObjects= updateItems(gameObjects);
       gameObjects= Sorter.sortByLayers(gameObjects);
       //System.out.println("finished Updating");//Statusmeldung in der Konsole
        if (lastGamObjectLength!=gameObjects.getGameObjects().size()){
        System.out.println("GameObject-length "+gameObjects.getGameObjects().size());}
        return gameObjects;
    }

    /**
     * Methode zum Prüfen, ob die Maus auf einem sichtbaren Objekt ist (mit diesem Interagieren kann)
     * dabei werden alle Game Objekte durchsucht bis ein passendes objekt gefunden wurde oder alle geprüft wurden
     * Deshalb ist es wichtig, dass die "Hitboxen" von objekten sich nicht überlagern, wenn diese beide Sichtbar sind!!!
     * @param gameObjects Liste der zu prüfenden Game Objekte
     * @return
     */
    private  static GameObject mouseIsOnObjekt(GameObjects gameObjects){
        if (MouseInput.mouseClicked){
            MouseInput.setMouseClicked(false);

        int mouseX =MouseInput.mouseX;
        int mouseY =MouseInput.mouseY;

        for (int i = 0; i <gameObjects.getGameObjects().size(); i++) {

            GameObject gameObjectI =gameObjects.getGameObjects().get(i);
            if (gameObjectI.isVisible()){
                if (gameObjectI.isInteractable()){
                    if (gameObjectI.getPositionX()<mouseX){
                        if (gameObjectI.getPositionY()<mouseY){
                            if (gameObjectI.getPositionX()+gameObjectI.getZiseX()>mouseX){
                                if (gameObjectI.getPositionY()+gameObjectI.getZiseY()>mouseY){

                                    return gameObjectI;
                                }
                            }

                        }
                    }
                }
            }
        }


    }
        return  null;
    }

    public  static GameObjects moveMouse(GameObjects gameObjects){
        if (MouseInput.mouseMoved) {
            System.out.println("Moved");
            MouseInput.setMouseMoved(false);
            Point point = new Point(MouseInput.mouseX,MouseInput.mouseY);
         Storage mouseStorage =Storage.getMouseStorage(gameObjects);
         mouseStorage.setPosition(point);
       gameObjects=  mouseStorage.updateStorage(gameObjects);
       }
    return  gameObjects;
    }

    public   static GameObjects updateItems(GameObjects gameObjects) {
        for (int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            if (gameObjects.getGameObjects().get(i) instanceof Item) {
                gameObjects.setGameObjekt(null, i);
            }
        }


        for (int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            GameObject aktiveGameobject = gameObjects.getGameObjects().get(i);
            if (aktiveGameobject instanceof Storage) {
                Storage aktiveStorage = (Storage) aktiveGameobject;
                for (int j = 0; j < aktiveStorage.getItems().length; j++) {
                    if (aktiveStorage.getItems()[j]!=null){
                        Point localItemCoordinates = aktiveStorage.getLocalItemCoordinates(j);
                        int absoluteItemPositionX = localItemCoordinates.x + aktiveStorage.getPositionX();
                        int absoluteItemPositionY = localItemCoordinates.y + aktiveStorage.getPositionY();
                        aktiveStorage.getItems()[j].setPositionX(absoluteItemPositionX);
                        aktiveStorage.getItems()[j].setPositionY(absoluteItemPositionY);
                        aktiveStorage.getItems()[j].setLayer(aktiveStorage.getLayer() + 1);
                        aktiveStorage.getItems()[j].setVisible(aktiveStorage.isVisible());
                        aktiveStorage.getItems()[j].setTextToDisplay(aktiveStorage.getAmounts()[j] + "");
                        gameObjects.addGameObjekt(aktiveStorage.getItems()[j]);}
                }
            }
        }



        return gameObjects;
    }







}
