/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.integration;

import se.kth.IV1350.jonatbj.model.*;
/**
 * This class will play the role of the external inventory system in this program
 */
public class ExternalInventorySystem 
{

    public Item getItem(int itemID)
    {
        Item itemToReturn;
        switch(itemID)
        {
            case 1: itemToReturn = new Item(1, "Milk", (float )10.5, "1 L of milk", (float) .12, 1);
            break;
            case 2: itemToReturn = new Item(2, "Chips", (float) 22.5, "One bag of Chips", (float) .12, 1);
            break;
            case 3: itemToReturn = new Item(3, "Flour", (float) 10, "1 Kg of flour", (float) .25, 1);
            break;
            default: itemToReturn = null;
            break;
        }
        return itemToReturn;
    }

    /**
     * Updates the inventory system
     * 
     * @param sale Uses the information from the sale to update the inventory system
     */
    public void updateInventorySystem(Sale sale)
    {
        
    }
}
