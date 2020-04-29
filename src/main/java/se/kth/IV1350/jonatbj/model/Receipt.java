/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.model;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the receipt given to the customer
 */
class Receipt {
    private String storeName;
    private String storeAddress;
    private float totalPrice;
    private float totalVAT;
    private int amountPaid;
    LocalDateTime timeAndDayOfSale;
    List<Item> items;

    /**
     * creates an instance of a receipt
     * 
     * @param _salTime the current time when the receipt is started
     */
    Receipt(LocalDateTime _salTime)
    {
        timeAndDayOfSale = _salTime;
        storeName = "Ica";
        storeAddress = "Stångholmsbacken 38";
        items = new ArrayList<>();
    }

    /**
     * updates the total price onto the receipt
     * 
     * @param itemPrice this is the price for the item that is added
     */
    public void updateTotalPrice(float itemPrice)
    {
        totalPrice += itemPrice;
    }


    /**
     * updates the total tax rate on the receipt
     * 
     * @param itemVAT the VATE rate 6,12 or 25 % 
     * @param price The price of the item
     */
    public void updatetotalVAT(float itemVAT, float price)
    {
        totalVAT += price*itemVAT;
    }

    /**
     * returns the total price of one sale
     * 
     * @return returns the total price of the sale
     */
    public float getTotalPrice()
    {
        return totalPrice;
    }

    /**
     * Sets the amount paid by the customer
     * 
     * @param _amountPaid the amount paid by the customer
     */
    public void setAmountPaid(int _amountPaid)
    {
        amountPaid = _amountPaid;
    }

    public float getTotalVAT()
    {
        return totalVAT;
    }

    /**
     * adds one item to the list of items
     * 
     * @param item the item that is suppose to be added
     */
    public void addItemToList(Item item)
    {
        items.add(item);
    }
    
    public void increaseQuantityOfItem(Item item)
    {
        item.updateQuantity();
    }
    
    private String printItems()
    {
        String itemToReturn = "";
        for(Item item : items)
        {
            itemToReturn += item.toString() + "\n";
        }
        return itemToReturn;
    }
    
    public String toString()
    {
        return "Store name: " + storeName + "\n" + "Store address: " + storeAddress + "\n" + 
                "Items: " + printItems() + "Total price: " + totalPrice + "\n" + "Total VAT: " + totalVAT
                + "\n" + "Amount paid: " + amountPaid + "kr \n" + "Change: " + (amountPaid-totalPrice);
    }
}
