
package gamelogic;

import gameObject.GameObject;
import gameObject.GameObjects;
import main.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput extends MouseAdapter {

    public static int button;
    public static int mouseX;
    public static int mouseY;
  public static boolean mouseClicked;
@Override
    public void mouseClicked(MouseEvent e){
   button= e.getButton();
    mouseX=e.getX();
    mouseY=e.getY();
    mouseClicked =true;
}

    public static void setMouseClicked(boolean mouseClicked) {
        MouseInput.mouseClicked = mouseClicked;
    }
}
