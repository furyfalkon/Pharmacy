package helper;
import gameObject.*;
import javax.swing.*;
import java.awt.*;


/**
 * Klasse zum Laden von Bildern
 */
public class ImageLoader {
    /**
     * Standart-Methode zum Laden von Bildern aus dem resources Ordner
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @return Geladenes Bild
     */
    public static Image loadImage(String filename){
        Image image = new ImageIcon("resources/"+filename).getImage();
        return image;
    }

    /**
     * methode zum Laden von Bildern aus dem resources Ordner welche sich in Unterordnern befinden
     * @param path Pfad des Unterordners (nach einem Ordnernamen folgt ein /)
     *             Beispiel zum Laden eines Bsp. Bildes im resources Ordner in einem Bsp. Ordner: "Bsp/"
     * @param filename  name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @return Geldenes Bild
     */
    public static Image loadImage(String path, String filename){
        Image image = new ImageIcon("resources/"+path+filename).getImage();
        return image;
    }


}
