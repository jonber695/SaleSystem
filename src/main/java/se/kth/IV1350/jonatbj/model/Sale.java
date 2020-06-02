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
    private List<RevenueObserver> revenueObservers;

    /**
     * Creates one new instance for every new customer
     */
    public Sale()
    {
        saleTime = LocalDateTime.now();
        receipt = new Receipt(saleTime);
        items = new ArrayList<>();
        revenueObservers = new ArrayList<>();
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
    public ItemDTO registerItem(int itemID, ExternalInventorySystem externalInventorySystem) throws InventoryNotRespondingException, ItemNotInInventoryException
    {
        if(itemID == 4)
            throw new InventoryNotRespondingException("Inventory system is not responding");
        Item itemToBeAdded = null; 
        itemToBeAdded = externalInventorySystem.getItem(itemID);
        
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
     * @return returns either true or false depending on whether or not the amount paid is enough
     */
    public boolean paymentProcess(int amountPaid)
    {
        float change = amountPaid - receipt.getTotalPrice();
        if(change < 0)
        {
            return false;
        }
        receipt.setAmountPaid(amountPaid);
        receipt.setChange(amountPaid);
        notifyObservers();
        return true;
    }

    private void notifyObservers()
    {
        for (RevenueObserver reObs : revenueObservers) {
            reObs.paymentUpdate(this);
        }
    }

    public void addObserver(RevenueObserver reObs)
    {
        revenueObservers.add(reObs);
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

    public int getReceiptTotalAmountPaid()
    {
        return receipt.getAmountPaid();
    }
}