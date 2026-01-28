package gamelogic;

import gameObject.*;
import helper.Sorter;
import main.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *Klasse in der alle Updates am Spiel ausgeführt werden
 */
public class GameLogic extends MouseInput{



    public static GameObjects update(GameObjects gameObjects, ActionEvent actionEvent){
        System.out.println("started Updating");//Statusmeldung in der Konsole
       if (mouseIsOnObjekt(gameObjects)!=null) System.out.println("in Objekt");







       gameObjects= Sorter.sortByLayers(gameObjects);
       System.out.println("finished Updating");//Statusmeldung in der Konsole
        return gameObjects;
    }

    /**
     * Methode zum Prüfen, ob die Maus auf einem sichtbaren Objekt ist (mit diesem Interagieren kann)
     * dabei werden alle Game Objekte durchsucht bis ein passendes objekt gefunden wurde oder alle geprüft wurden
     * Deshalb ist es wichtig, dass die "Hitboxen" von objekten sich nicht überlagern, wenn diese beide Sichtbar sind!!!
     * @param gameObjects Liste der zu prüfenden Game Objekte
     * @return
     */
    public  static GameObject mouseIsOnObjekt(GameObjects gameObjects){

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
                            if (gameObjectI.getPositionX()+gameObjectI.getSizeX()>mouseX){
                                if (gameObjectI.getPositionY()+gameObjectI.getSizeY()>mouseY){

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




}
