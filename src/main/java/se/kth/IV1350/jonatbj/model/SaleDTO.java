package se.kth.IV1350.jonatbj.model;

import java.util.List;


public class SaleDTO {
    Receipt receipt;
    List<Item> items;

    public SaleDTO(Receipt _receipt, List<Item> _items)
    {
        receipt = _receipt;
        items = _items;
    }

    public Receipt getReceipt()
    {
        return receipt;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public float getTotalPrice()
    {
        return receipt.getTotalPrice();
    }
}