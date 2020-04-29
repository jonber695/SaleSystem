/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.model;


/**
 * The class that represents an item
 */
public class Item {
    private int itemID;
    private String name;
    private float price;
    private String itemDiscription;
    private float vatRate;
    private int quantity;

    /**
     * creates an instance of item with these charactaristics:
     * 
     * @param _itemID is used to indentify which item it is
     * @param _name the name of the item
     * @param _price the price of the item
     * @param _itemDiscription Describes the itme
     * @param _VATrate Tells the VAT Rate, either 6, 12 or 25%
     * @param _quantity tells how many of a certain item there is
     */
    public Item(int _itemID, String _name, float _price, String _itemDiscription, float _VATrate, int _quantity)
    {
        itemID = _itemID;
        name = _name;
        price = _price;
        itemDiscription = _itemDiscription;
        vatRate = _VATrate;
        quantity = _quantity;
    }

    public int getItemID()
    {
        return itemID;
    }

    public String getName()
    {
        return name;
    }

    public float getPrice()
    {
        return price;
    }

    public String getItemDiscription()
    {
        return itemDiscription;
    }

    public float getVATrate()
    {
        return vatRate;
    }

    public int getQuantity()
    {
        return quantity;
    }

    /**
     * updates the quantity of items
     */
    public void updateQuantity()
    {
        quantity++;
    }

    public String toString()
    {
        return "Name: " + name + " Price : " + price + " Quantity: " + quantity;
    }
}