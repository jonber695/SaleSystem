/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.view;

import se.kth.IV1350.jonatbj.controller.Controller;
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
    }

    /**
     * This is where the program happens, what would be the user interface, but these are just hard
     * coded method calls
     */
    public void userInterface()
    {
        int itemID = 0;
        contr.startSale();
        System.out.println("Sale started");
        System.out.println("Enter itemID for scanning:");
        boolean loopToCheckThatItemIdIsANumber = true;
        do
        {
            try
            {
                itemID = scanner.nextInt();
                loopToCheckThatItemIdIsANumber = false;
            }
            catch (Exception e)
            {
                System.out.println("You did not enter a number, please try again");
            }
        }while(loopToCheckThatItemIdIsANumber);
        float totalPrice = 0;
        while(itemID != 0)
        {
            ItemDTO item = contr.scanItems(itemID);
            totalPrice += item.getPrice();
            System.out.println("Item disctiption: " + item.toString() + "\n" + "And the running total is: " + totalPrice);
            System.out.println("Enter next itemID:");
            System.out.println("If there are no more items enter the number zero");
            itemID = scanner.nextInt();
        }
        System.out.println("Sale ended");
        SaleDTO sale = contr.endSale();
        System.out.println("Total price: " + sale.getTotalPrice());
        contr.updateingSaleLog();
        System.out.println("Enter paid amount:");
        int amountPaid = scanner.nextInt();
        while(contr.payment(amountPaid) == false)
        {
            System.out.println("Not enough paid, there is still " + Math.abs(amountPaid-sale.getTotalPrice()) + " kr left to pay, enter again:");
            amountPaid += scanner.nextInt();
        }
            
        contr.printReceipt();
        System.out.println("--------Start of receipt-------");
        System.out.println(sale.getReceipt());
        System.out.println("--------End of receipt----------");
    }
}
