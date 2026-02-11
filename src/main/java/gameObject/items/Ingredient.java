package gameObject.items;

import helper.ImageLoader;

import java.awt.*;

public class Ingredient extends Item{

    public Ingredient(Image img,String name, int value) {
        super(img, name, value,false);


    }

    @Override
    public void buy(int amount) {
    }

    @Override
    public void sell(int amount) {
    }



    public static class IngredientColection{

        static Ingredient placeholder =new Ingredient(ImageLoader.loadImage("TempItem.png"),"This is a temporary Placeholder",1);
       static Ingredient[] cocainingredients ={placeholder};



    }
}
