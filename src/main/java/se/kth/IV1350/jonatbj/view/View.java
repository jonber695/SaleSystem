/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.view;

import se.kth.IV1350.jonatbj.controller.Controller;
import se.kth.IV1350.jonatbj.exception.InventoryNotRespondingException;
import se.kth.IV1350.jonatbj.exception.ItemNotInInventoryException;
import se.kth.IV1350.jonatbj.model.ItemDTO;
import se.kth.IV1350.jonatbj.model.SaleDTO;

import java.util.*;

/**
 * View is the interface between the cashier and the system in this program
 */
public class View 
{
    Controller contr;
    Scanner scanner = new Scanner(System.in);
    /**
     * Creates a new instance that uses the controller to work between layers
     * 
     * @param _contr is used to work between layers
     */
    public View(Controller _contr)
    {
        contr = _contr;
        contr.addObserver(new TotalRevenueView());
    }

    /**
     * This is where the program happens, what would be the user interface, but these are just hard
     * coded method calls
     */
    public void userInterface()
    {
        int loopingToTestObserverPattern = 0;
        ItemDTO item = null;
        while(loopingToTestObserverPattern < 2)
        {
            float totalPrice = 0;
            contr.startSale();
            System.out.println("Sale started");
            System.out.println("Enter itemID for scanning:");
            int itemID = enterNumber();
            
            while(itemID != 0)
            {
                try
                {
                    item = contr.scanItems(itemID);
                }
                catch(InventoryNotRespondingException e)
                {
                    System.out.println("To user: The inventory is not responding, please try again");
                    System.out.println("To developer: " + e.getLocalizedMessage());
                }
                catch(ItemNotInInventoryException e)
                {
                    System.out.println("To user: You have entered an invalid item ID, please try again");
                    System.out.println("To developer: " + e.getLocalizedMessage());
                }
                if(item != null)
                {
                    totalPrice += item.getPrice();
                    System.out.println("Item Discription: " + item.getItemDiscription() + " and the running total is: " + totalPrice);
                }    
                System.out.println("Enter next itemID:");
                System.out.println("If there are no more items enter zero");
                itemID = enterNumber();
                item = null;
            }
            System.out.println("Sale closed");
            SaleDTO sale = contr.endSale();
            System.out.println("Total price: " + sale.getTotalPrice());
            contr.updateingSaleLog();
            System.out.println("Enter paid amount:");
            int amountPaid = enterNumber();
            while(contr.payment(amountPaid) == false)
            {
                System.out.println("Not enough paid, there is still " + Math.abs(amountPaid - sale.getTotalPrice()) + " kr left to pay, enter again:");
                amountPaid += enterNumber();
            }
            contr.increaseAmountInRegister(amountPaid);
            contr.updateAccountingSystem();
            contr.updateInventorySystem();
            System.out.println(sale.getReceipt());
            contr.printReceipt();
            loopingToTestObserverPattern++;
            System.out.println("-------- end of sale -------");
        } 
    }

    private int enterNumber()
    {
        boolean loopToCheckThatItemIdIsANumber = true;
        int itemID = 0;
        do
        {
            try
            {
                itemID = scanner.nextInt();
                loopToCheckThatItemIdIsANumber = false;
            }
            catch (Exception e)
            {
                System.out.println("You did not enter a number, please try again:");
            }
        }while(loopToCheckThatItemIdIsANumber);
        return itemID;
    }
}
