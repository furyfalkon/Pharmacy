package gameObject;

import gameObject.items.Drug;
import gameObject.items.Ingredient;
import gameObject.items.Item;
import gamelogic.GameLogic;

import java.awt.*;


/**
 * Klasse zum Verwalten von Items in Lagern
 */
public class Storage extends GameObject implements Interactable {

    Item[] items; //Liste aller Items im Lager
    int amount[];//Liste der mengen der Items
    int colums;//Spalten des Lagers
    int rows;//Zeilen des Lagers

    String name;


    /**
     * Construktor für ein Lager
     *
     * @param colums    Spalten des Lagers
     * @param rows      Zeilen des Lagers
     * @param positionX Position der oberen Linken Ecke des Lagers (x-Koordinate)
     * @param positionY Position der oberen Linken Ecke des Lagers (y-Koordinate)
     */
    public Storage(Image img, String name, int layer, int positionX, int positionY, int colums, int rows) {
        super(img, false, layer, positionX, positionY, colums * 32, rows * 32);
        int Zise = rows * colums;
        this.rows = rows;
        this.colums = colums;
        this.items = new Item[Zise];
        this.items[0] = null;
        this.name = name;
        this.amount = new int[Zise];
        this.amount[0] = 0;
    }

    /**
     * Construktor für ein Lager
     *
     * @param colums    Spalten des Lagers
     * @param rows      Zeilen des Lagers
     * @param positionX Position der oberen Linken Ecke des Lagers (x-Koordinate)
     * @param positionY Position der oberen Linken Ecke des Lagers (y-Koordinate)
     */
    public Storage(Image img, String name, int layer, int positionX, int positionY, int colums, int rows, boolean isChildObject) {
        super(img, false, layer, positionX, positionY, colums * 32, rows * 32);
        int Zise = rows * colums;
        this.rows = rows;
        this.colums = colums;
        this.items = new Item[Zise];
        this.items[0] = null;
        this.name = name;
        this.amount = new int[Zise];
        this.amount[0] = 0;
        setChildObject(isChildObject);
    }

    /**
     * alle items eines Lagers bekommen
     *
     * @return item Liste
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * alle items eines lagers setzen
     *
     * @param items zu setzende Items
     */
    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setItem(Item item, int pos) {
        this.items[pos] = item;
    }


    /**
     * alle item mengen im lager bekommen
     *
     * @return item mengen liste
     */
    public int[] getAmounts() {
        return amount;
    }

    /**
     * Menge eines Items in einer bestimmten poition eines bestimmten Lagers setzen
     *
     * @param pos    betroffene Position
     * @param amount neue Menge
     */
    public void setAmount(int pos, int amount) {
        this.amount[pos] = amount;
    }

    /**
     * Item an einer bestimmten Position in einem bestimmten lager bekommen
     *
     * @param pos Position des Items im Lager
     * @return Item an der Position pos
     */
    public Item getItem(int pos) {
        if (items.length > pos) {
            return items[pos];
        }
        return null;
    }

    public int getAmount(int pos) {
        if (amount.length > pos) {
            return amount[pos];
        }
        return 0;
    }


    public int getColums() {
        return colums;
    }

    public void setColums(int colums) {
        this.colums = colums;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    /**
     * Items werden aus einem Source Lager in den ersten Slot eines Zieh-lagers verschoben, in welchem dieses bereits vorhanden ist,
     * falls das item noch nicht vorhanden ist, wird der erste freie Slot verwendet.
     *
     * @param targetStorage lager in welches die Items verschoben werden sollen
     * @param sourceStorage lager aus dem die Items entnommen werden
     * @param pos           Position des zu verschiebenden Items im Source lager
     */
    public GameObjects transferItem(GameObjects gameObjects, Storage sourceStorage, Storage targetStorage, int pos) {

        for (int i = 0; i < targetStorage.items.length; i++) {
            if (targetStorage.items[i] == sourceStorage.items[pos]) {
                targetStorage.amount[i] = targetStorage.amount[i] + sourceStorage.amount[pos];
                emtyPos(sourceStorage, pos);
                gameObjects = sourceStorage.updateStorage(gameObjects);
                gameObjects = sourceStorage.updateStorage(gameObjects);

                return gameObjects;
            }
        }
        for (int i = 0; i < targetStorage.items.length; i++) {
            if (targetStorage.items[i] == null) {
                targetStorage.items[i] = sourceStorage.items[pos];
                targetStorage.amount[i] = sourceStorage.amount[pos];
                emtyPos(sourceStorage, pos);
                gameObjects = sourceStorage.updateStorage(gameObjects);
                gameObjects = sourceStorage.updateStorage(gameObjects);

                return gameObjects;
            }
        }
        gameObjects = sourceStorage.updateStorage(gameObjects);
        gameObjects = sourceStorage.updateStorage(gameObjects);

        return gameObjects;
    }


    /**
     * Ein Item wird, aus einem Source Lager in den ausgewählten Slot im Ziel-Lager verschoben.
     *
     * @param ziehlStorage  Lager in welches die Items verschoben werden sollen
     * @param sourceStorage Lager aus dem die Items entnommen werden
     * @param sourcePos     Position des zu verschiebenden Items im Source lager
     * @param ziehlPos      Position in die Verschoben wird
     */
    public GameObjects addOneItem(GameObjects gameObjects, Storage sourceStorage, Storage ziehlStorage, int sourcePos, int ziehlPos) {
        if (ziehlStorage.items[ziehlPos] == null) {
            if (sourceStorage.amount[sourcePos] > 0) {
                Item newItem;
                if (sourceStorage.items[sourcePos] instanceof Drug){
                    newItem = new Drug((Drug) sourceStorage.items[sourcePos]);
                }else if (sourceStorage.items[sourcePos] instanceof Ingredient){
                    newItem= new Ingredient((Ingredient) sourceStorage.items[sourcePos]);
                }else newItem =null;
                ziehlStorage.items[ziehlPos] = newItem;
                ziehlStorage.amount[ziehlPos]++;
                sourceStorage.amount[sourcePos]--;
            }
            if (sourceStorage.amount[sourcePos] == 0) {
                emtyPos(sourceStorage, sourcePos);
            }
        } else if (ziehlStorage.items[ziehlPos].sameTypAs(sourceStorage.items[sourcePos])) {
            if (sourceStorage.amount[sourcePos] > 0) {
                ziehlStorage.amount[ziehlPos]++;
                sourceStorage.amount[ziehlPos]--;
            }
            if (sourceStorage.amount[sourcePos] == 0) {
                emtyPos(sourceStorage, sourcePos);
            }
        }

        gameObjects = sourceStorage.updateStorage(gameObjects);
        gameObjects = ziehlStorage.updateStorage(gameObjects);

        return gameObjects;

    }

    /**
     * Ein Stack Items wird, aus einem Source Lager in den ausgewählten Slot im Ziel-Lager verschoben.
     *
     * @param ziehlStorage  Lager in welches die Items verschoben werden sollen
     * @param sourceStorage Lager aus dem die Items entnommen werden
     * @param sourcePos     Position des zu verschiebenden Items im Source lager
     * @param ziehlPos      Position in die Verschoben wird
     */
    public GameObjects addItems(GameObjects gameObjects, Storage sourceStorage, Storage ziehlStorage, int sourcePos, int ziehlPos) {
        System.out.println("Taking item from " + sourceStorage.name + " to " + ziehlStorage.name);//Konsolen Output zum Schnellen Debuggen
        //Prüfen, ob der ausgewählte Zielslot Ler ist
        if (ziehlStorage.items[ziehlPos] == null) {
            if (sourceStorage.amount[sourcePos] > 0) {
                Item newItem;
                if (sourceStorage.items[sourcePos] instanceof Drug){
                    newItem = new Drug((Drug) sourceStorage.items[sourcePos]);
                }else if (sourceStorage.items[sourcePos] instanceof Ingredient){
                    newItem= new Ingredient((Ingredient) sourceStorage.items[sourcePos]);
                }else newItem =null;
                ziehlStorage.items[ziehlPos] = newItem;
                ziehlStorage.amount[ziehlPos] = sourceStorage.amount[sourcePos];
                sourceStorage.amount[sourcePos] = 0;
            }
            if (sourceStorage.amount[sourcePos] == 0) {
                emtyPos(sourceStorage, sourcePos);
            }
        }else if (ziehlStorage.items[ziehlPos].sameTypAs( sourceStorage.items[sourcePos]) ){//Prüfen, ob der ausgewählte Zielslot das einzulagernde Item Enthält
            if (sourceStorage.amount[sourcePos] > 0) {
                ziehlStorage.amount[ziehlPos] = ziehlStorage.amount[ziehlPos] + sourceStorage.amount[sourcePos];
                sourceStorage.amount[sourcePos] = 0;
            }
            if (sourceStorage.amount[sourcePos] == 0) {
                emtyPos(sourceStorage, sourcePos);
            }
        }

        gameObjects = sourceStorage.updateStorage(gameObjects);
        gameObjects = ziehlStorage.updateStorage(gameObjects);

        return gameObjects;

    }


    @Override
    public GameObjects interact(GameObjects gameObjects) {
        return gameObjects;
    }

    @Override
    public GameObjects interact(GameObjects gameObjects, int button) {
        return gameObjects;
    }

    @Override
    public GameObjects interact(GameObjects gameObjects, int button, int xPosObjekt, int yPosObjekt, int xMouse, int yMouse) {
        int colums = this.getColums();
        int xrelativeMousePosition = xMouse - xPosObjekt;
        int yrelativeMousePosition = yMouse - yPosObjekt;
        int mousePos = getMousePos(colums, xrelativeMousePosition, yrelativeMousePosition);
        System.out.println("Mouse in Position " + mousePos);
        Storage mouseStorage = getMouseStorage(gameObjects);
        if (mouseStorage.getItem(0) != null && mouseStorage.getAmount(0) != 0) {
            //Linke Maustaste
            if (button == 1) {
                gameObjects = addItems(gameObjects, mouseStorage, this, 0, mousePos);
            }
            //Rechte maustaste
            if (button == 3) {
                gameObjects = addOneItem(gameObjects, mouseStorage, this, 0, mousePos);
            }
        } else {
            System.out.println("Mouse Storage Emty");
            //Linke Maustaste
            if (button == 1) {
                gameObjects = addItems(gameObjects, this, mouseStorage, mousePos, 0);
            }
            //Rechte maustaste
            if (button == 3) {
                gameObjects = addOneItem(gameObjects, this, mouseStorage, mousePos, 0);
            }
        }


        return gameObjects;
    }

    /**
     * private hilfsmethode zum leren eines lagerslots
     *
     * @param storage lager in welchem ein slott gelert werden soll
     * @param pos     position die gelert werden soll
     */
    private void emtyPos(Storage storage, int pos) {
        storage.amount[pos] = 0;
        storage.items[pos] = null;
    }

    private int getMousePos(int Colums, int xrelativeMousePosition, int yrelativeMousePosition) {
        int activeRow = yrelativeMousePosition / 32;
        int activeColum = xrelativeMousePosition / 32;
        int position = activeColum + activeRow * Colums;
        return position;
    }

    public static Storage getMouseStorage(GameObjects gameObjects) {
        for (int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            if (gameObjects.getGameObjects().get(i) instanceof Storage) {
                if (((Storage) gameObjects.getGameObjects().get(i)).name == "mouseStorage") {
                    Storage mouseStorage = ((Storage) gameObjects.getGameObjects().get(i));
                    return mouseStorage;
                }
            }
        }
        return null;
    }


    public GameObjects updateStorage(GameObjects gameObjects) {
        Storage aktiveStorage = this;
        for (int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            if (gameObjects.getGameObjects().get(i) instanceof Storage) {
                if (((Storage) gameObjects.getGameObjects().get(i)).name == aktiveStorage.name) {
                    gameObjects.getGameObjects().set(i, this);
                }
            }
        }
        return gameObjects;
    }

    public Point getLocalItemCoordinates(int pos) {
        int tileZise = 32;
        int colums = this.getColums();
        int row = pos / colums;
        int colum = pos % colums;
        int posX = colum * tileZise;
        int posY = row * tileZise;
        Point point = new Point(posX, posY);
        return point;
    }








}




