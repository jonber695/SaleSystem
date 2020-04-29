/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.integration;

import se.kth.IV1350.jonatbj.model.Sale;

/**
 * Represents the external system printer in the program
 */
public class Printer {

    /**
     * prints the receipt from the current sale
     * 
     * @param sale the sale from which the receipt is printed
     */
    public void printsReceipt(Sale sale)
    {
        sale.getReceipt();
    }
}
