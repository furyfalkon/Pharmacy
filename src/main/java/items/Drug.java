/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package items;

/**
 *
 * @author administrator
 */
public class Drug extends Item {
    
    Ingredient[] ingredients;

    public Drug(String name, int value, Ingredient[] ingredients) {
        super(name, value);
        this.ingredients = ingredients;
    }

    @Override
    public void buy(int amount) {
    }

    @Override
    public void sell(int amount) {
    }

    
        
    
}
