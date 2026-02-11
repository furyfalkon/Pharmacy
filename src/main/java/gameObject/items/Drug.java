/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObject.items;

import helper.ImageLoader;

import java.awt.*;

/**
 *
 * @author administrator
 */
public  class Drug extends Item implements crafteable{
    
    Ingredient[] ingredients;
    int qualety;

    public Drug(String name,int value, Ingredient[] ingredients, Image img) {
        super(img,name, value, true);
        this.ingredients = ingredients;
        this.qualety= 0;
    }
    public Drug(String name, int value,Image img) {
        super(img,name, value, false);
        this.ingredients = null;
        this.qualety=0;
    }

    public Drug(Drug templateDrug){
        super(templateDrug);
        this.ingredients=templateDrug.ingredients;
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




}
