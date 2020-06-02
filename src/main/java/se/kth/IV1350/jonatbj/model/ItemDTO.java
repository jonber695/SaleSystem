package se.kth.IV1350.jonatbj.model;

public class ItemDTO {
    private Item item;

    public ItemDTO(Item _item)
    {
        item = _item;
    }

    public int getItemID()
    {
        return item.getItemID();
    }

    public String getName()
    {
        return item.getName();
    }

    public float getPrice()
    {
        return item.getPrice();
    }

    public String getItemDiscription()
    {
        return item.getItemDiscription();
    }

    public float getVATrate()
    {
        return item.getVATrate();
    }

    public int getQuantity()
    {
        return item.getQuantity();
    }

    public String toString()
    {
        return "Name: " + item.getName() + " Price : " + item.getPrice() + " Quantity: " + item.getQuantity();
    }
}