/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.model;

import java.time.LocalDateTime;
import java.util.*;
import se.kth.IV1350.jonatbj.integration.*;
import se.kth.IV1350.jonatbj.exception.*;

/**
 * Represents one sale from one customer
 */
public class Sale {
    Receipt receipt;
    LocalDateTime saleTime;
    List<Item> items;
    private int usingThisToAddTheFirstItemInTheList = 0;

    /**
     * Creates one new instance for every new customer
     */
    public Sale()
    {
        saleTime = LocalDateTime.now();
        receipt = new Receipt(saleTime);
        items = new ArrayList<>();
    }

    /**
     * Registers the items into the list of items
     * 
     * @param itemID                  This is the ID of an item, used to fetch the
     *                                information from the external system
     * @param externalInventorySystem Uses the ID to fetch information from its
     *                                inventory system
     * @throws InvetoryNotRespondingException Throws an exception when the inventory is not reachable
     */
    public void registerItem(int itemID, ExternalInventorySystem externalInventorySystem) throws InventoryNotRespondingException
    {
        if(itemID == 4)
            throw new InventoryNotRespondingException("Inventory system is not responding");
        Item itemToBeAdded = null; 
        try
        {
            itemToBeAdded = externalInventorySystem.getItem(itemID);
        }
        catch(ItemNotInInventoryException e)
        {
            System.out.println("For user: " + e.getMessage());
            System.out.println("For developer: " + e);
            return;
        }
        if(usingThisToAddTheFirstItemInTheList == 0)
        {
            items.add(itemToBeAdded);
            receipt.addItemToList(itemToBeAdded);
            usingThisToAddTheFirstItemInTheList++;
        }
        else
        {   
            boolean itemAlreadyRegistered = false;
            for(int i = 0; i < items.size() && itemAlreadyRegistered == false; i++) {
                itemAlreadyRegistered = itemsEqualToEachOther(items.get(i), itemToBeAdded);
            }
            if(itemAlreadyRegistered == false)
            {
                items.add(itemToBeAdded);
                receipt.addItemToList(itemToBeAdded);
            }
            else
            {
                for (Item item : items) {
                    if(itemsEqualToEachOther(item, itemToBeAdded))
                    {
                        item.updateQuantity();
                        receipt.increaseQuantityOfItem(itemToBeAdded);
                    }
                        
                }
                
            }
        }
        receipt.updatetotalVAT(itemToBeAdded.getVATrate(), itemToBeAdded.getPrice());
        receipt.updateTotalPrice(itemToBeAdded.getPrice());
        System.out.println("Item Discription: " + itemToBeAdded.getItemDiscription() + " and the running total is: " + receipt.getTotalPrice());
    }

    private boolean itemsEqualToEachOther(Item existingItem, Item itemToBeChecked)
    {
        return (existingItem.getItemID() == itemToBeChecked.getItemID()) ? true : false;
    }

    /**
     * Ends the current sale and prints the total price and all of the items that are bought
     */
    public void endingSale()
    {
        System.out.println("Sale closed");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("Total price = " + receipt.getTotalPrice() + " kr.");
    }

    /**
     * This is the payment process
     * 
     * @param amountPaid the amount payed by the customer
     * @return returns either true or false depending on whether or not the amount paid is enough
     */
    public boolean paymentProcess(int amountPaid)
    {
        float change = amountPaid - receipt.getTotalPrice();
        if(change < 0)
        {
            System.out.println("Not enough paid, there is still " + Math.abs(amountPaid-receipt.getTotalPrice()) + " kr left to pay, enter again:");
            return false;
        }
        receipt.setAmountPaid(amountPaid);
        receipt.setChange(amountPaid);
        return true;
    }


    public Receipt getReceipt()
    {
        return receipt;
    }
    
    /**
     * returns the string of the receipt
     * @return returns the string of the receipt
     */
    public String printReceipt()
    {
        return receipt.toString();
    }
    
    public List<Item> getItems()
    {
        return items;
    }
}