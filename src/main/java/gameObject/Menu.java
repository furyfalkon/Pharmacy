package gameObject;

import gameObject.butons.Button;
import gameObject.butons.VisibilityToggler;

import java.awt.*;

public class Menu extends  GameObject  {
    GameObjects menuGameObjects;
    Point localMousePosition;
    public Menu(Image img, boolean visible, int layer, int positionX, int positionY, int sizeX, int sizeY,GameObjects menuGameObjects) {
        super(img, visible, layer, positionX, positionY, sizeX, sizeY);
        this.menuGameObjects=menuGameObjects;
        setDragable(true);
    }

    public Point calculateLocalMousePosition(Point globalMousePosition){
        return new Point((int) (globalMousePosition.getX()- this.getPositionX()), (int) (globalMousePosition.getY()- this.getPositionY()));
    }

    public Point getLocalMousePosition() {
        return localMousePosition;
    }

    public void setLocalMousePosition(Point localMousePosition) {
        this.localMousePosition = localMousePosition;
    }

    public GameObjects getMenuGameObjects() {
        return menuGameObjects;
    }

    public void setMenuGameObjects(GameObjects menuGameObjects) {
        this.menuGameObjects = menuGameObjects;
    }

    public void addMenuGameObject(GameObject menuGameObject){this.menuGameObjects.addGameObject(menuGameObject);}
    public  GameObjects updateMenu(GameObjects gameObjects){
        Menu aktiveMenu = this;
        GameObjects aktiveMenuGameObjects = aktiveMenu.getMenuGameObjects();
        for (int j = 0; j <aktiveMenuGameObjects.getGameObjects().size(); j++) {
            GameObject aktiveChildGameObject = aktiveMenuGameObjects.getGameObjects().get(j);
            int absoluteChildObjectPositionX =aktiveMenu.getPositionX()+aktiveChildGameObject.getPositionX();
            int absoluteChildObjectPositionY =aktiveMenu.getPositionY()+aktiveChildGameObject.getPositionY();
            int absoluteChildObjectLayer= aktiveMenu.getLayer()+aktiveChildGameObject.getLayer();
            boolean absoluteChildObjectVisibility=false;
            if (aktiveMenu.isVisible()){
                if (aktiveChildGameObject.isVisible()){
                    absoluteChildObjectVisibility=true;
                }
            }
            GameObject globalChildGameObject = null;
            if (aktiveChildGameObject!= null){
                Image img =aktiveChildGameObject.getImg();
                if (aktiveChildGameObject instanceof Background){
                    globalChildGameObject= new Background(img);
                }

                   if (aktiveChildGameObject instanceof gameObject.butons.VisibilityToggler){
                       globalChildGameObject= new VisibilityToggler(img,false,0,0,0,aktiveChildGameObject.getZiseX(),aktiveChildGameObject.getZiseY(),false,((VisibilityToggler) aktiveChildGameObject).getGameObjectToToggleVisibility());
                    }

                if (aktiveChildGameObject instanceof Storage){
                    globalChildGameObject =new Storage(img,((Storage) aktiveChildGameObject).getName(),1,0,0,((Storage) aktiveChildGameObject).getColumns(),((Storage) aktiveChildGameObject).getRows());
                    ((Storage) globalChildGameObject).setItems(((Storage) aktiveChildGameObject).getItems());
                    ((Storage) globalChildGameObject).setAmounts(((Storage) aktiveChildGameObject).getAmounts());
                }
                if (aktiveChildGameObject instanceof TempObject){
                    globalChildGameObject = new TempObject(false,0,0,0);
                }}



            if (globalChildGameObject != null){
                globalChildGameObject.setPositionX(absoluteChildObjectPositionX);
                globalChildGameObject.setPositionY(absoluteChildObjectPositionY);
                globalChildGameObject.setLayer(absoluteChildObjectLayer);
                globalChildGameObject.setVisible(absoluteChildObjectVisibility);
                globalChildGameObject.setChildObject(true);
                gameObjects.addGameObject(globalChildGameObject);
                if (globalChildGameObject instanceof Storage){
                    gameObjects=((Storage) globalChildGameObject).updateStorage(gameObjects);
                }
            }
        }
        return gameObjects;
    }
}
