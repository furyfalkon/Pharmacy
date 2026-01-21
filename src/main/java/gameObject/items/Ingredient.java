package gameObject.items;

import java.awt.*;

public class Ingredient extends Item{

    public Ingredient(Image img,int layer,String name, int value) {
        super(img,layer, name, value,false);


    }

    @Override
    public void buy(int amount) {
    }

    @Override
    public void sell(int amount) {
    }



    public static class IngredientColection{

        static Ingredient placeholder =new Ingredient(null,2,"This is a temporary Placeholder",1);
       static Ingredient[] cocainingredients ={placeholder};



    }
}
