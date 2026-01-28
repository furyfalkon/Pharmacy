package maps;
import gameObject.*;
import helper.ImageLoader;

/**
 * Klasse zum Bauen (Initialisiern) der Karte
 */
public  class MapBuilder {
    /**
     * Methode zum Bauen (Initialisiern) der Karte
     * @param gameObjects Liste aller Gameobjekte der aktuellen Karte
     * @return Liste aller Gameobjekte nach dem Buen (Initialisieren) der Karte
     */
    public  static GameObjects buildMap(GameObjects gameObjects){
        Background tempBackground =new Background( ImageLoader.loadImage("TempHintergrund.png"));
        gameObjects.addGameObjekt(tempBackground);
        Storage tempstorage =new Storage(ImageLoader.loadImage("TempSprite.png"),1,10,10,1,1);
        tempstorage.setVisible(true);
        gameObjects.addGameObjekt(tempstorage);

        return gameObjects;
    }
}
