/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.controller;

import se.kth.IV1350.jonatbj.model.*;

import java.util.ArrayList;
import java.util.List;

import se.kth.IV1350.jonatbj.exception.InventoryNotRespondingException;
import se.kth.IV1350.jonatbj.exception.ItemNotInInventoryException;
import se.kth.IV1350.jonatbj.integration.*;

/**
 * Controls the program and forwards the method calls from View to the right
 * method in the model layer
 */
public class Controller {
    private ExternalAccountingSystem externalAccountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    private Register register;
    private Sale sale;
    private Printer printer;
    private SaleLog saleLog;
    private List<RevenueObserver> revenueObservers;

    /**
     * creates an instance of controller that is used in the program, to dicates
     * where the method calls from view goes
     * 
     * @param _AccountingSystem This is the external accounting system that is used
     *                          for the accounting
     * @param _InventorySystem  External inventory system used for finding the items
     *                          and updating
     * @param _register         the register that stores the total amount of money
     *                          in the register
     * @param _Printer          only used to print the receipt at the end of the
     *                          program
     */
    public Controller(ExternalAccountingSystem _AccountingSystem, ExternalInventorySystem _InventorySystem,
            Register _register, Printer _Printer) {
        externalAccountingSystem = _AccountingSystem;
        externalInventorySystem = _InventorySystem;
        register = _register;
        saleLog = new SaleLog();
        printer = _Printer;
        revenueObservers = new ArrayList<>();
    }

    /**
     * Adds an ovserver to the list of observers
     * @param reObs
     */
    public void addObserver(RevenueObserver reObs) {
        revenueObservers.add(reObs);
    }

    /**
     * Starts the sale by creating a sale object
     */
    public void startSale() {
        sale = new Sale();
        sale.addObserver(revenueObservers.get(0));
    }

    /**
     * Scans one item by calling the method registerItem in sale
     * 
     * @param itemID used to identify which item is suppose to be added
     * @throws ItemNotInInventoryException throws an exception up to the view if the cashier has entered an invalid ID
     * @throws InventoryNotRespondingException throws an exception up to the view if the inventory is not responding
     * 
     * @return Forwards the ItemDTO from the method in Sale
     */
    public ItemDTO scanItems(int itemID) throws ItemNotInInventoryException, InventoryNotRespondingException
    {
        return sale.registerItem(itemID, externalInventorySystem);
    }

    /**
     * Ends sale by calling the endingSale method in sale
     * @return Forwards the SaleDTO from the method in Sale
     */
    public SaleDTO endSale()
    {
        return sale.endingSale();
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
     * @return returns either true or false depending on whether or not the amount paid is enough
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
}