package se.kth.IV1350.jonatbj.model;

public class ItemDTO {
    private int itemID;
    private String name;
    private float price;
    private String itemDiscription;
    private float vatRate;
    private int quantity;

    public ItemDTO(int _itemID, String _name, float _price, String _itemDiscription, float _VATrate, int _quantity)
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

    public String toString()
    {
        return "Name: " + name + " Price : " + price + " Quantity: " + quantity;
    }
}