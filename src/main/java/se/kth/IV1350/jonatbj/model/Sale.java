/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.IV1350.jonatbj.integration.ExternalInventorySystem;

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
     * @param itemID This is the ID of an item, used to fetch the information from the external system
     * @param externalInventorySystem Uses the ID to fetch information from its inventory system
     */
    public ItemDTO registerItem(int itemID, ExternalInventorySystem externalInventorySystem)
    {
        Item itemToBeAdded = externalInventorySystem.getItem(itemID);
        if(itemToBeAdded == null)
            return null;
        if(usingThisToAddTheFirstItemInTheList == 0)
        {
            addingItemToList(itemToBeAdded);
        }
        else
        {   
            boolean itemAlreadyRegistered = false;
            for(int i = 0; i < items.size() && itemAlreadyRegistered == false; i++) {
                itemAlreadyRegistered = itemsEqualToEachOther(items.get(i), itemToBeAdded);
            }
            if(itemAlreadyRegistered == false)
            {
                addingItemToList(itemToBeAdded);
            }
            else
            {
               increaseQuantityOfItem(itemToBeAdded);   
            }
        }
        receipt.updatetotalVAT(itemToBeAdded.getVATrate(), itemToBeAdded.getPrice());
        receipt.updateTotalPrice(itemToBeAdded.getPrice());
        return new ItemDTO(itemToBeAdded);
    }

    private boolean itemsEqualToEachOther(Item existingItem, Item itemToBeChecked)
    {
        return (existingItem.getItemID() == itemToBeChecked.getItemID()) ? true : false;
    }

    private void addingItemToList(Item itemToBeAdded)
    {
        items.add(itemToBeAdded);
        receipt.addItemToList(itemToBeAdded);
        if(usingThisToAddTheFirstItemInTheList == 0)
            usingThisToAddTheFirstItemInTheList++;
    }

    private void increaseQuantityOfItem(Item itemToBeAdded)
    {
        for (Item item : items) {
            if(itemsEqualToEachOther(item, itemToBeAdded))
            {
                item.updateQuantity();
                receipt.increaseQuantityOfItem(itemToBeAdded);
            }                        
        }
    }

    /**
     * Ends the current sale and prints the total price and all of the items that are bought
     */
    public SaleDTO endingSale()
    {
        return new SaleDTO(receipt, items);
    }

    /**
     * This is the payment process
     * 
     * @param amountPaid the amount payed by the customer
     * @return returns either true or false depending on whether the amount paid is enough or not
     */
    public boolean paymentProcess(int amountPaid)
    {
        float change = amountPaid - receipt.getTotalPrice();
        if(change < 0)
        {
            return false;
        }
        receipt.setAmountPaid(amountPaid);
        receipt.setChange();
        return true;
    }

    public Receipt getReceipt()
    {
        return receipt;
    }
    
    public String printReceipt()
    {
        return receipt.toString();
    }
    
    public List<Item> getItems()
    {
        return items;
    }
}