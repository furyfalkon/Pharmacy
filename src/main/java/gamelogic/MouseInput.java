
package gamelogic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasse zum Managen aller Maus aktionen
 * */
public class MouseInput extends MouseAdapter {

    public static int button;                   //der gedrückte Knopf
    public static int mouseX;                   //die X Koordinate der Maus
    public static int mouseY;                   //die y Koordinate der Maus
  public static boolean mouseClicked;           //die Maus wurde geklickt
  public static  boolean mouseMoved;            //die Maus wurde bewegt
  public static boolean mousePressed;           //die Maus wurde gedrückt

@Override
    public void mouseClicked(MouseEvent e){
    button= e.getButton();      //der gedrückte Knopf wird gespeichert
    mouseX=e.getX();            //die X Koordinate der Maus wird geupdatet
    mouseY=e.getY();            //die Y Koordinate der Maus wird geupdatet
    mouseClicked =true;         //es wird gespeichert das die Maus gedrückt wurde
}

@Override
public void mouseMoved(MouseEvent e){
    mouseMoved=true;            //es wird gespeichert das die Maus bewegt wurde
    mouseX=e.getX();            //die X Koordinate der Maus wird geupdatet
    mouseY=e.getY();            //die Y Koordinate der Maus wird geupdatet
}
@Override
public void mouseDragged(MouseEvent e){
    mouseMoved=true;            //es wird gespeichert das die Maus bewegt wurde
    mouseX=e.getX();            //die X Koordinate der Maus wird geupdatet
    mouseY=e.getY();            //die Y Koordinate der Maus wird geupdatet
}
@Override
public void mousePressed(MouseEvent e){
    mousePressed =true;         //es wird gespeichert das die Maus gedrückt wurde
}
@Override
public void mouseReleased(MouseEvent e){
    mousePressed =false;        //es wird gespeichert das die Maus losgelassen wurde
                                    //und folglich jetzt nicht mehr gedrückt ist

}

    public static void setMouseClicked(boolean mouseClicked) {
        MouseInput.mouseClicked = mouseClicked;
    }

    public static void setMouseMoved(boolean mouseMoved) {
        MouseInput.mouseMoved = mouseMoved;
    }

}
