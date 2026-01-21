
package gameObject.items;
import gameObject.*;

import java.awt.*;

public abstract class Item extends GameObject {
    
    String name;
    int basevalue;
    final boolean craftable;



    public Item(Image img,int layer ,String name, int basevalue, boolean craftable) {
        super(img,false,layer,0,0,0,0);

        sizeX = 0;
        sizeY =0;
        try {
            sizeX= img.getWidth(null);
            sizeY = img.getHeight(null);
        }catch (Exception e){
            throw e;
        }

        this.name = name;
        this.basevalue = basevalue;
        this.craftable = craftable;


    }

    public String getName() {
        return name;
    }

    public int getBasevalue() {
        return basevalue;
    }

    public abstract void buy(int amount);
    
    public abstract void sell(int amount);
        
    
}
