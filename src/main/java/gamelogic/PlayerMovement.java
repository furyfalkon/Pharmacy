package gamelogic;

import gameObject.GameObject;
import gameObject.GameObjects;
import gameObject.Player;

import java.awt.*;

public class PlayerMovement {


        //Anfangsgeschwindigkeit für player
        static int speed = 10;

        //Ausführen der bewegung wenn die variable true
        public static GameObjects move(GameObjects gameObjects,Player player) {
            int playerX = player.getPositionX();
            int playerY = player.getPositionY();

            if (KeyInput.up) {
                playerY = playerY - speed;
                player=animationUp(player);
            }

            if (KeyInput.down) {
                playerY = playerY + speed;
                player=animationDown(player);
            }

            if (KeyInput.left) {
                playerX = playerX - speed;
              player=  animationDown(player);
            }

            if (KeyInput.right) {
                playerX = playerX + speed;
               player= animationDown(player);
            }

            Image image;
            image=player.getImg();
            Player testPlayer =new Player(image,false,0,playerX,playerY);;

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
                if (aktiveGameObjekt.isCollidable()){
                    if (pointIsInObject(playerTopLeft,aktiveGameObjekt)
                            ||pointIsInObject(playerTopRight,aktiveGameObjekt)
                            ||pointIsInObject(playerBottomLeft,aktiveGameObjekt)
                            ||pointIsInObject(playerBottomRight,aktiveGameObjekt)){
                      return false;
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
               if (point.getY()>position.getY()&& point.getY()<position.getY()+SizeY){
                   return true;
               }
           }
           return false;
        }

}
