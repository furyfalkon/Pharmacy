
package items;


public abstract class Item {
    
    String name;
    int value;

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    
    public abstract void buy(int amount);
    
    public abstract void sell(int amount);
        
    
}
