package gameObject;

import  java.awt.*;

public class Player extends GameObject{
    int movingCycle;
    Image[] movingUp;
    Image[] movingDown;
    Image[] movingRight;
    Image[] movingLeft;
    Image[] idling;



    public Player(Image[] idling, boolean visible, int layer, int positionX, int positionY) {
        super(idling[0], visible, layer, positionX, positionY,100,10);
        this.setInteractable(false);
        this.movingUp = movingUp;
    }

    public void setMovingUp(Image[] movingUp) {
        this.movingUp = movingUp;
    }

    public Image[] getMovingUp() {
        return movingUp;
    }

    public void setMovingDown(Image[] movingDown) {
        this.movingDown = movingDown;
    }

    public Image[] getMovingDown() {return movingDown;}

    public void setMovingRight(Image[] movingRight) {
        this.movingRight = movingRight;
    }

    public Image[] getMovingRight() {return movingRight;}

    public void setMovingLeft(Image[] movingLeft) {
        this.movingLeft = movingLeft;
    }

    public Image[] getMovingLeft() {return movingLeft;}

    public void setIdling(Image[] idling) {
        this.idling = idling;
    }

    public Image[] getIdling() {return idling;}

    public int getMovingCycle() {
        return movingCycle;
    }

    public void setMovingCycle(int movingCycle) {
        this.movingCycle = movingCycle;
    }
}
