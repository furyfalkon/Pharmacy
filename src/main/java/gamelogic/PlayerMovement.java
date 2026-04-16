package gamelogic;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Menu;
import gameObject.Player;
import gameObject.music.EinmaligesAbspielen;
import gameObject.music.UnendlichesAbspielen;

import java.awt.*;

public class PlayerMovement {


        //Anfangsgeschwindigkeit für player
        static int speed = 10;
        static UnendlichesAbspielen playerMoving =new UnendlichesAbspielen();
        static boolean soundRunning;
        //Ausführen der bewegung wenn die variable true
        public static GameObjects move(GameObjects gameObjects,Player player) {
            int playerX = player.getPositionX();
            int playerY = player.getPositionY();


            Boolean playerIsBeingMoved =false;
            if (KeyInput.up) {
                playerY = playerY - speed;
                player=animationUp(player);
                playerIsBeingMoved=true;
            }

            if (KeyInput.down) {
                playerY = playerY + speed;
                player=animationDown(player);
                playerIsBeingMoved=true;
            }

            if (KeyInput.left) {
                playerX = playerX - speed;
              player=  animationDown(player);
                playerIsBeingMoved=true;
            }

            if (KeyInput.right) {
                playerX = playerX + speed;
               player= animationDown(player);
                playerIsBeingMoved=true;
            }

            if (playerIsBeingMoved&&(!soundRunning)){
                playerMoving.play("drinnenlaufen.wav");
                soundRunning =true;
            }
            if ((!playerIsBeingMoved)&&soundRunning){
                playerMoving.stop();
                soundRunning=false;
            }
            Image image;
            image=player.getImg();
            Player testPlayer =new Player(image,false,0,playerX,playerY);

           if (checkCollision(gameObjects,testPlayer)){
               player.setPositionX(playerX);
               player.setPositionY(playerY);
            gameObjects.updateGameObject(player);}
            return gameObjects;
        }


        private  static Player animationDown(Player player){
            int animationSize = player.getMovingDown().length;
            int movingCycle = player.getMovingCycle();

            if (movingCycle <animationSize){
                player.setImg(player.getMovingDown()[movingCycle]);
                movingCycle++;
            }
            if (movingCycle +1==animationSize){
                player.setImg(player.getMovingDown()[movingCycle]);
                movingCycle =0;
            }
            if (movingCycle>=animationSize){
                movingCycle =0;
            }
            player.setMovingCycle(movingCycle);
        return player;
        }
        private static Player animationUp(Player player){
            int animationSize = player.getMovingUp().length;
            int movingCycle = player.getMovingCycle();

            if (movingCycle <animationSize){
                player.setImg(player.getMovingUp()[movingCycle]);
                movingCycle++;
            }
            if (movingCycle +1==animationSize){
                player.setImg(player.getMovingUp()[movingCycle]);
                movingCycle =0;
            }
            if (movingCycle>=animationSize){
                movingCycle =0;
            }
            player.setMovingCycle(movingCycle);
            return player;
        }

        private static boolean checkCollision(GameObjects gameObjects,Player player){
            int hitboxVerticalOffset=190;
            Point playerTopLeft = new Point(player.getPositionX(), player.getPositionY()+hitboxVerticalOffset);
            Point playerTopRight = new Point(player.getPositionX()+player.getSizeX(), player.getPositionY()+hitboxVerticalOffset);
            Point playerBottomLeft = new Point(player.getPositionX(), player.getPositionY()+player.getSizeY()+hitboxVerticalOffset);
            Point playerBottomRight = new Point(player.getPositionX()+player.getSizeX(), player.getPositionY()+player.getSizeY()+hitboxVerticalOffset);
            for (int i = 0; i < gameObjects.getSize(); i++) {
                GameObject aktiveGameObjekt = gameObjects.getGameObject(i);
                if (aktiveGameObjekt instanceof Menu&& aktiveGameObjekt.isVisible()){
                    for (int j = 0; j <((Menu) aktiveGameObjekt).getMenuGameObjects().getSize(); j++) {
                    GameObject aktiveChildGameObject =((Menu) aktiveGameObjekt).getMenuGameObjects().getGameObject(j);
                if (aktiveChildGameObject.isCollidable()){
                    if (pointIsInObject(playerTopLeft,aktiveChildGameObject)
                            ||pointIsInObject(playerTopRight,aktiveChildGameObject)
                            ||pointIsInObject(playerBottomLeft,aktiveChildGameObject)
                            ||pointIsInObject(playerBottomRight,aktiveChildGameObject)){
                      return false;
                    }
                }
            }
            }
            }
            return true;
        }


        private static boolean pointIsInObject(Point point,GameObject gameObject){
           Point position= new Point( gameObject.getPositionX(), gameObject.getPositionY());
           int SizeX = gameObject.getSizeX();
           int SizeY = gameObject.getSizeY();
           if (point.getX()>position.getX()&&point.getX()<position.getX()+SizeX){
               return point.getY() > position.getY() && point.getY() < position.getY() + SizeY;
           }
           return false;
        }





}
