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
    float change;

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
    
    /**
     * sends which items quantity needs to be increased by one
     * @param item this is the items whos quantity need to be increased
     */
    public void increaseQuantityOfItem(Item item)
    {
        item.updateQuantity();
    }
    
    /**
     * Sets the change
     * @param amountPaid the amount paid by the customer
     */
    public void setChange(int amountPaid)
    {
        change = amountPaid - totalPrice;
    }
    
    public float getChange()
    {
        return change;
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
    
    /**
     * This is the toString method that overwrites the default tostring
     * @return the string that I want to be printed in the output
     */
    public String toString()
    {
        return "Receipt: \n" + "Store name: " + storeName + "\n" + "Store address: " + storeAddress + "\n" + 
                "Items: " + printItems() + "Total price: " + totalPrice + "\n" + "Total VAT: " + totalVAT
                + "\n" + "Amount paid: " + amountPaid + "kr \n" + "Change: " + change;
    }
}
