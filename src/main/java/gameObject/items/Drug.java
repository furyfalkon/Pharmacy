/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObject.items;

import java.awt.*;

/**
 *
 * @author administrator
 */
public class Drug extends Item implements crafteable{
    
    Ingredient[] ingredients;
    int qualety;

    public Drug(String name, int layer,int value, Ingredient[] ingredients, Image img) {
        super(img,layer,name, value, true);
        this.ingredients = ingredients;
        this.qualety= 0;
    }
    public Drug(String name,int layer, int value,Image img) {
        super(img,layer,name, value, false);
        this.ingredients = null;
        this.qualety=0;
    }
    public Drug(String name,int layer, int value, Ingredient[] ingredients,int qualety, Image img) {
        super(img,layer,name, value, true);
        this.ingredients = ingredients;
        this.qualety= 0;
    }
    public Drug(String name,int layer, int value,int qualety,Image img) {
        super(img,layer,name, value, false);
        this.ingredients = null;
        this.qualety=0;
    }

    @Override
    public void buy(int amount) {
    }

    @Override
    public void sell(int amount) {
    }


    @Override
    public void craft(int amount, Ingredient[] incregredients) {

    }


    private class DrugColection{

        Drug Cocain= new Drug("Koks",5,100,Ingredient.IngredientColection.cocainingredients,0,null);

        Drug MagicMushroom = new Drug("Magic-mushroom",5,10,null);

        Drug Skittlez = new Drug("Skittlez",5,20,null);
        Drug LemonHaze= new Drug("Lemon Haze",5,12,null);
        Drug OGKush =new Drug("OG Kush",5,10,null);

    }
}
