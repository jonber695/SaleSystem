/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.IV1350.jonatbj.model.Sale;

/**
 * Represents the external sale log
 */
public class SaleLog {
    List<Sale> saleLog;

    /**
     * Creates the sale log in the program
     */
    public SaleLog()
    {
        saleLog = new ArrayList<>();
    }

    /**
     * Adds the sale to the log
     * 
     * @param sale the sale to be added to the log
     */
    public void addSaleToSaleLog(Sale sale)
    {
        saleLog.add(sale);
    }
}
