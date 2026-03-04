package helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


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

    /**
     * Standart-Methode zum Laden von Bildern aus dem resources Ordner
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @return Geladenes Bild
     */
    public static Image loadImage(String filename,int saclingFaktor){
        Image image = new ImageIcon("resources/"+filename).getImage();
        int h =image.getHeight(null);
        int w= image.getWidth(null);
       Image scaledImg = image.getScaledInstance(w*saclingFaktor,h*saclingFaktor,Image.SCALE_DEFAULT);
        return scaledImg;
    }

    /**
     * methode zum Laden von Bildern aus dem resources Ordner welche sich in Unterordnern befinden
     * @param path Pfad des Unterordners (nach einem Ordnernamen folgt ein /)
     *             Beispiel zum Laden eines Bsp. Bildes im resources Ordner in einem Bsp. Ordner: "Bsp/"
     * @param filename  name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @return Geldenes Bild
     */
    public static Image loadImage(String path, String filename,int saclingFaktor){
        Image image = new ImageIcon("resources/"+path+filename).getImage();
        int h =image.getHeight(null);
        int w= image.getWidth(null);
        Image scaledImg = image.getScaledInstance(w*saclingFaktor,h*saclingFaktor,Image.SCALE_DEFAULT);
        return scaledImg;
    }

    public static Image loadTiledImage(String filename, int colums , int rows){
       Image tiledImage =loadImage(filename+"Tiled-"+colums+"x"+rows+".png");
       if (tiledImage.getWidth(null)==-1){
        Image image = new ImageIcon("resources/"+filename).getImage();
        BufferedImage  tiledBufferedImage= new BufferedImage(
                colums*image.getWidth(null),
                rows*image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB );
        Graphics graphics = tiledBufferedImage.getGraphics();
        for (int x = 0; x <colums; x++) {
            for (int y = 0; y <rows; y++) {
                graphics.drawImage(image,x*image.getWidth(null),y*image.getHeight(null),null);
            }
        }
        File f = new File( "resources/"+filename+"Tiled-"+colums+"x"+rows+".png" );
        try {
            ImageIO.write( tiledBufferedImage, "PNG", f );
        }
        catch ( IOException x ) {
            x.printStackTrace();
        }

        tiledImage = (Image) tiledBufferedImage;
       }
        return (Image) tiledImage;
    }


}
