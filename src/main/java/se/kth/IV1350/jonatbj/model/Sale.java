/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.model;

import java.time.LocalDateTime;
import java.util.*;
import se.kth.IV1350.jonatbj.integration.*;

/**
 * Represents one sale from one customer
 */
public class Sale {
    Receipt receipt;
    LocalDateTime saleTime;
    List<Item> items;
    private int usingThisToAddTheFirstItemInTheList = 0;
    Scanner scanner = new Scanner(System.in);

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
    public void registerItem(int itemID, ExternalInventorySystem externalInventorySystem)
    {
        Item itemToBeAdded = externalInventorySystem.getItem(itemID);
        if(usingThisToAddTheFirstItemInTheList == 0)
        {
            items.add(itemToBeAdded);
            usingThisToAddTheFirstItemInTheList++;
        }
        else
        {   
            boolean itemAlreadyRegistered = false;
            for(int i = 0; i< items.size() && itemAlreadyRegistered != true; i++) {
                itemAlreadyRegistered = itemsEqualToEachOther(items.get(i++), itemToBeAdded);
            }
            if(itemAlreadyRegistered == false)
                items.add(itemToBeAdded);
            else
            {
                for (Item item : items) {
                    if(itemsEqualToEachOther(item, itemToBeAdded))
                        item.updateQuantity();
                }
            }
        }
        receipt.updatetotalVAT(itemToBeAdded.getVATrate(), itemToBeAdded.getPrice());
        receipt.updateTotalPrice(itemToBeAdded.getPrice());
        System.out.println("Item Discription: " + itemToBeAdded.getItemDiscription() + " and the running total is: " + receipt.getTotalPrice());
        receipt.addItemToList(itemToBeAdded);
    }

    boolean itemsEqualToEachOther(Item existingItem, Item itemToBeChecked)
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
     */
    public void paymentProcess(float amountPaid)
    {
        float change = amountPaid - receipt.getTotalPrice();
        while(change < 0)
        {
            System.out.println("Not enough paid, there is still " + Math.abs(amountPaid-receipt.getTotalPrice()) + " kr left to pay, enter again:");
            amountPaid += scanner.nextFloat();
            change = amountPaid - receipt.getTotalPrice();
        }
        System.out.println("Change = " + change + " kr.");
    }

    public Receipt getReceipt()
    {
        return receipt;
    }
}