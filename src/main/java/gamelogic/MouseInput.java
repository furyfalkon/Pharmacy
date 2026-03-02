
package gamelogic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseInput extends MouseAdapter {

    public static int button;
    public static int mouseX;
    public static int mouseY;
  public static boolean mouseClicked;
  public static  boolean mouseMoved;
@Override
    public void mouseClicked(MouseEvent e){
   button= e.getButton();
    mouseX=e.getX();
    mouseY=e.getY();
    mouseClicked =true;
}

@Override
public void mouseMoved(MouseEvent e){
    mouseMoved=true;
    mouseX=e.getX();
    mouseY=e.getY();
}
@Override
public void mouseDragged(MouseEvent e){
    mouseMoved=true;
    mouseX=e.getX();
    mouseY=e.getY();
}

    public static void setMouseClicked(boolean mouseClicked) {
        MouseInput.mouseClicked = mouseClicked;
    }

    public static void setMouseMoved(boolean mouseMoved) {
        MouseInput.mouseMoved = mouseMoved;
    }
}
