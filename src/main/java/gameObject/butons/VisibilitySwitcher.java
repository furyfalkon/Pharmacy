package gameObject.butons;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Player;
import gameObject.music.EinmaligesAbspielen;

import java.awt.*;

public class VisibilitySwitcher extends Button{
    GameObject gameObjectToToggleVisibility1;
    GameObject gameObjectToToggleVisibility2;
    int range; //Distance, aus der interagiert, werden kann -1 ist eine unendlich hohe distance
    /**
     * Methode zum Erstellen eines Sichtbarkeitsschalters
     *
     * @param img                          Das Bild des Menüs
     * @param visible                      der Sichtbarkeitsstatus des Menüs
     * @param layer                        die Anzeigeebene
     * @param positionX                    die X-Position des Menüs
     * @param positionY                    die Y-Position des Menüs
     * @param sizeX                        die Breite des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param sizeY                        die Höhe des Menüs (des bereiches mit dem Interagiert werden kann)
     * @param gameObjectToToggleVisibility1 das Objekt dessen Sichtbarkeit geändert wird
     */
    public VisibilitySwitcher(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY, GameObject gameObjectToToggleVisibility1,GameObject gameObjectToToggleVisibility2) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY);
        this.gameObjectToToggleVisibility2=gameObjectToToggleVisibility2;
        this.gameObjectToToggleVisibility1=gameObjectToToggleVisibility1;
    }
    @Override
    public GameObjects interact(GameObjects gameObjects) {
        if (range<0||distanceToPlayer(gameObjects)<range){
            if (gameObjectToToggleVisibility1!=null) {                           //wenn das objekt dessen Sichtbarkeit geändert wird nicht leer ist
                if (gameObjectToToggleVisibility1.isVisible()) {                     //und es Sichtbar ist
                    gameObjectToToggleVisibility1.setVisible(false);                  //wird es sichtbar gesetzt
                    gameObjectToToggleVisibility2.setVisible(true);                 //wird es unsichtbar gemacht
                    gameObjects.updateGameObject(gameObjectToToggleVisibility1);
                    gameObjects.updateGameObject(gameObjectToToggleVisibility2) ;  //gespeichert
                    return gameObjects;                                                 //und zurück gegeben
                }
                if (!gameObjectToToggleVisibility1.isVisible()) {                    //und es nicht sichtbar ist
                    gameObjectToToggleVisibility1.setVisible(true);                  //wird es sichtbar gesetzt
                   gameObjectToToggleVisibility2.setVisible(false);
                    gameObjects.updateGameObject(gameObjectToToggleVisibility1);
                    gameObjects.updateGameObject(gameObjectToToggleVisibility2) ;    //gespeichert
                    return gameObjects;                                                 //und zurück gegeben
                }
            }
        }

        return gameObjects;
    }
    private   int distanceToPlayer(GameObjects gameObjects){
        for (int i = 0; i < gameObjects.getSize(); i++) {
            GameObject aktiveGameObjekt = gameObjects.getGameObject(i);
            if (aktiveGameObjekt instanceof Player){
                Point buttonPos = new Point(this.getPositionX(),this.getPositionY());
                Point playerPos= new Point(aktiveGameObjekt.getPositionX(), aktiveGameObjekt.getPositionY());
                double distance= buttonPos.distance(playerPos);
                return (int)distance;

            }
        }
        return 0;
    }
    public void setRange(int range) {
        this.range = range;
    }
}
