package gamelogic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasse zum Managen aller Tastatur Tasten
 * */
public class KeyInput extends KeyAdapter {
        static char aktiveKey =' ';

         static   boolean keyTyped;


    //Variablen für bewegungsrichtung festlegen
    public static boolean up;
    public static  boolean left;
    public static boolean down;
    public static boolean right;

        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode()!=0) {
                keyTyped = true;
                aktiveKey = keyEvent.getKeyChar();

                //festlegen von variablenwert bei bestimmten keyeveents (z.b. up = true wenn w = pressed; up = false wenn w= released
                if (keyEvent.getKeyCode() == KeyEvent.VK_A) left = true;
                if (keyEvent.getKeyCode() == KeyEvent.VK_W) up = true;
                if (keyEvent.getKeyCode() == KeyEvent.VK_S) down = true;
                if (keyEvent.getKeyCode() == KeyEvent.VK_D) right = true;





            }
    }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

            if (keyEvent.getKeyCode()!=0) {
                keyTyped = true;
                aktiveKey = keyEvent.getKeyChar();

                //festlegen von variablenwert bei bestimmten keyeveents (z.b. up = true wenn w = pressed; up = false wenn w= released
                if (keyEvent.getKeyCode() == KeyEvent.VK_A) left = true;
                if (keyEvent.getKeyCode() == KeyEvent.VK_W) up = true;
                if (keyEvent.getKeyCode() == KeyEvent.VK_S) down = true;
                if (keyEvent.getKeyCode() == KeyEvent.VK_D) right = true;
            }
            System.out.println("Taste gedrückt: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
    }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

            if (keyEvent.getKeyCode() == KeyEvent.VK_W) up = false;
            if (keyEvent.getKeyCode() == KeyEvent.VK_A) left = false;
            if (keyEvent.getKeyCode() == KeyEvent.VK_S) down = false;
            if (keyEvent.getKeyCode() == KeyEvent.VK_D) right = false;
    }

    public static char getAktiveKey() {
        return aktiveKey;
    }

    public static boolean isKeyTyped() {
        return keyTyped;
    }
}

