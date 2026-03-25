package helper;

import javax.imageio.*;
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
     * Standard Methode zum Laden von Bildern aus dem resources Ordner
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @return Geladenes Bild
     */
    public static Image loadImage(String filename){
        Image image = new ImageIcon("resources/"+filename).getImage();
        return image;
    }

    /**
     * Standard methode zum Laden von Bildern aus dem resources Ordner welche sich in Unterordnern befinden
     * @param path Pfad des Unterordners (nach einem Ordnernamen folgt ein /)
     *             Beispiel zum Laden eines Bsp. Bildes im resources Ordner in einem Bsp. Ordner: "Bsp/"
     * @param filename  name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @return Geladenes Bild
     */
    public static Image loadImage(String path, String filename){
        return loadImage(path+filename);
    }

    /**
     * Methode zum Laden von Bildern aus dem resources Ordner
     * und einer Vergrößerung/Verkleinerung
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @param scalingFaktor Faktor mit dem sowohl Höhe und Breite des Bildes gestreckt wird
     * @return Geladenes und Bild
     */
    public static Image loadImage(String filename,double scalingFaktor){
        Image image = new ImageIcon("resources/"+filename).getImage();
        double h =image.getHeight(null);
        double w= image.getWidth(null);
        int X = (int) (w*scalingFaktor);
        int Y = (int) (h*scalingFaktor);
       Image scaledImg = image.getScaledInstance(X,Y,Image.SCALE_DEFAULT);
        return(Image) scaledImg;
    }

    /**
     * Methode zum Laden von Bildern aus dem resources Ordner welche sich in Unterordnern befinden
     * und einer Vergrößerung/Verkleinerung
     * @param path Pfad des Unterordners (nach einem Ordnernamen folgt ein /)
     *             Beispiel zum Laden eines Bsp. Bildes im resources Ordner in einem Bsp. Ordner: "Bsp/"
     * @param filename  name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @param scalingFaktor Faktor mit dem sowohl Höhe und Breite des Bildes gestreckt wird
     * @return Geladenes Bild
     */
    public static Image loadImage(String path, String filename,double scalingFaktor){
        return loadImage(path+filename,scalingFaktor);
    }


    /**
     * Methode zum Laden von Bildern aus dem resources Ordner
     * und einer unabhängigen Vergrößerung/Verkleinerung der einzelnen Seien
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @param scalingFaktorX Faktor mit dem die Breite des Bildes gestreckt wird
     * @param scalingFaktorY Faktor mit dem die Höhe des Bildes gestreckt wird
     * @return Geladenes Bild
     */
    public static Image loadImage(String filename,int scalingFaktorX,int scalingFaktorY){
        Image image = new ImageIcon("resources/"+filename).getImage();
        int h =image.getHeight(null);
        int w= image.getWidth(null);
        Image scaledImg = image.getScaledInstance(w*scalingFaktorX,h*scalingFaktorY,Image.SCALE_DEFAULT);
        return scaledImg;
    }

    /**
     * methode zum Laden von Bildern aus dem resources Ordner welche sich in Unterordnern befinden
     * und einer unabhängigen Vergrößerung/Verkleinerung der einzelnen Seien
     * @param path Pfad des Unterordners (nach einem Ordnernamen folgt ein /)
     *             Beispiel zum Laden eines Bsp. Bildes im resources Ordner in einem Bsp. Ordner: "Bsp/"
     * @param filename  name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @param scalingFaktorX Faktor mit dem die Breite des Bildes gestreckt wird
     * @param scalingFaktorY Faktor mit dem die Höhe des Bildes gestreckt wird
     * @return Geladenes Bild
     */
    public static Image loadImage(String path, String filename,int scalingFaktorX,int scalingFaktorY){
        return loadImage(path+filename,scalingFaktorX,scalingFaktorY);
    }


    /**
     * Methode zum Laden von Bildern aus dem resources Ordner
     * und einem tilen des geladenen Bildes
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @param columns Anzahl der Spalten in denen das Bild horizontal getiled wird
     * @param rows Anzahl der Zeilen in denen das Bild vertikal getiled wird
     * @return Geladenes Bild
     */
    public static Image loadTiledImage(String filename, int columns , int rows){
       Image tiledImage =loadImage(filename+"Tiled-"+columns+"x"+rows+".png"); //es wird versucht, das Image welches durch tilen entstehen soll zu laden (damit fals es bereits existiert keine neue version /kopie erstellt wird)
       if (tiledImage.getWidth(null)==-1){                                     //wenn das Bild noch nicht existiert
        Image image = loadImage(filename);                                             //wird das zu tilende Image geladen
        BufferedImage  tiledBufferedImage= new BufferedImage(                          //es wird ein neues Buffered Image erstellt
                columns*image.getWidth(null),
                rows*image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB );
        Graphics graphics = tiledBufferedImage.getGraphics();                           //dieses wird einem Graphics objekt hinzugefügt
        for (int x = 0; x <columns; x++) {                                              //es wird jede Position im Tiling Raster einmal durch eine kombination aus zwei schleifen angesteuert
            for (int y = 0; y <rows; y++) {
                graphics.drawImage(image,x*image.getWidth(null),y*image.getHeight(null),null);  //asn jede Position des Rasters wird das zu tilende bild auf das graphics objekt gemalt
            }
        }
        File f = new File( "resources/"+filename+"Tiled-"+columns+"x"+rows+".png" );        //es wird eine neue Datei erstellt
        try {
            ImageIO.write( tiledBufferedImage, "PNG", f );                                 //und versucht das in Form des Buffered Image gespeicherte Bild als Png gespeichert
        }
        catch ( IOException x ) {
            x.printStackTrace();
        }

        tiledImage = tiledBufferedImage;                //das durch tilen erzeugte BufferedImage wird als Image gespeichert
       }
        return  tiledImage;  // es wird das durch tilen erzeugte bild zurückgegeben
    }

    /**
     * methode zum Laden von Bildern aus dem resources Ordner welche sich in Unterordnern befinden
     * und einem tilen des geladenen Bildes
     * @param path Pfad des Unterordners (nach einem Ordnernamen folgt ein /)
     *             Beispiel zum Laden eines Bsp. Bildes im resources Ordner in einem Bsp. Ordner: "Bsp/"
     * @param filename name des zu ladenden Bildes (mit Dateiendung) Bsp: "Beispeil.png"
     * @param columns Anzahl der Spalten in denen das Bild horizontal getiled wird
     * @param rows Anzahl der Zeilen in denen das Bild vertikal getiled wird
     * @return Geladenes Bild
     */
    public static Image loadTiledImage(String path,String filename, int columns , int rows){
       return loadTiledImage(path+filename,columns,rows);

    }


}
