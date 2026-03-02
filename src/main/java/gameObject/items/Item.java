
package gameObject.items;
import gameObject.*;

import java.awt.*;

public abstract class Item extends GameObject {
    
    String name;
    int basevalue;
    final boolean craftable;





    public Item(Image img ,String name, int basevalue, boolean craftable) {
        super(img,false,0,0,0,32,32);
        this.name = name;
        this.basevalue = basevalue;
        this.craftable = craftable;


    }

    public Item(Item templateItem){
        super(templateItem.getImg(),false,0,0,0,32,32);
        this.name=templateItem.getName();
        this.basevalue=templateItem.getBasevalue();
        this.craftable=templateItem.craftable;
    }

    public String getName() {
        return name;
    }

    public int getBasevalue() {
        return basevalue;
    }

    public abstract void buy(int amount);
    
    public abstract void sell(int amount);

    public boolean sameTypAs(Item item2){
        if (this.name==item2.getName()){
            return true;
        }
        return false;
    }

}
