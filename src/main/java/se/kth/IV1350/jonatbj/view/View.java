/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.view;

import se.kth.IV1350.jonatbj.controller.Controller;
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
        int itemID;
        contr.startSale();
        System.out.println("Sale started");
        System.out.println("Enter itemID for scanning:");
        itemID = scanner.nextInt();
        while(itemID != 0)
        {
            contr.scanItems(itemID);
            System.out.println("Enter next itemID:");
            System.out.println("If there are no more items enter zero");
            itemID = scanner.nextInt();
        }
        contr.endSale();
        contr.updateingSaleLog();
        System.out.println("Enter paid amount:");
        float amountPaid = scanner.nextInt();
        contr.payment(amountPaid);
        contr.printReceipt();
    }
}
