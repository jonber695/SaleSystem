/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.controller;

import se.kth.IV1350.jonatbj.model.*;
import se.kth.IV1350.jonatbj.integration.*;

/**
 * Controls the program and forwards the method calls from View to the right method in the model layer
 */
public class Controller {
    private ExternalAccountingSystem externalAccountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    private Register register;
    private Sale sale;
    private Printer printer;
    private SaleLog saleLog;

    /**
     * creates an instance of controller that is used in the program,
     * to dicates where the method calls from view goes
     * 
     * @param _AccountingSystem This is the external accounting system that is used for the accounting
     * @param _InventorySystem External inventory system used for finding the items and updating
     * @param _register the register that stores the total amount of money in the register
     * @param _Printer only used to print the receipt at the end of the program
     */
    public Controller(ExternalAccountingSystem _AccountingSystem, ExternalInventorySystem _InventorySystem, Register _register, Printer _Printer)
    {
        externalAccountingSystem = _AccountingSystem;
        externalInventorySystem = _InventorySystem;
        register = _register;
        saleLog = new SaleLog();
        printer = _Printer;
    }

    /**
     * Starts the sale by creating a sale object
     */
    public void startSale()
    {
        sale = new Sale();
    }

    /**
     * Scans one item by calling the method registerItem in sale
     * 
     * @param itemID used to identify which item is suppose to be added
     */
    public void scanItems(int itemID)
    {
        sale.registerItem(itemID, externalInventorySystem);
    }

    /**
     * Ends sale by calling the endingSale method in sale
     */
    public void endSale()
    {
        sale.endingSale();
    }

    /**
     * updates the saleLog with the current sale
     */
    public void updateingSaleLog()
    {
        saleLog.addSaleToSaleLog(sale);
    }

    /**
     * preformes the payment sequence
     * 
     * @param amountPaid The amount of money that the customer paid with
     * @return returns either true or false depending on whether the amount paid is enough
     */
    public boolean payment(int amountPaid)
    {
        return sale.paymentProcess(amountPaid);
    }

    /**
     * increases the amount of money in the register with the amount paid
     * 
     * @param amountPaid the amount of money the customer paid
     */
    public void increaseAmountInRegister(float amountPaid)
    {
        register.increaseAmountInRegister(amountPaid);
    }

    /**
     * updates the external accounting system
     */
    public void updateAccountingSystem()
    {
        externalAccountingSystem.updateAccountingSystem(sale);
    }

    /**
     * updates the external inventory system
     */
    public void updateInventorySystem()
    {
        externalInventorySystem.updateInventorySystem(sale);
    }
    
    /**
     * Prints the receipt.
     */
    public void printReceipt()
    {
        printer.printsReceipt(sale);
    }
    
    public void showReceipt()
    {
        System.out.println(sale.printReceipt());
    }
}