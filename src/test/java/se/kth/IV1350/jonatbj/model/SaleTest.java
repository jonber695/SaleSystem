/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.IV1350.jonatbj.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.IV1350.jonatbj.integration.ExternalInventorySystem;

/**
 *
 * @author jonat
 */
public class SaleTest {

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    

    /**
     * Test of endingSale method, of class Sale.
     */
    @org.junit.jupiter.api.Test
    public void testEndingSale() {
        System.out.println("endingSale");
        Sale instance = new Sale();
        instance.endingSale();
        
    }

    /**
     * Test of paymentProcess method, of class Sale.
     */
    @org.junit.jupiter.api.Test
    public void testPaymentProcess() {
        System.out.println("paymentProcess");
        int amountPaid = 0;
        Sale instance = new Sale();
        instance.paymentProcess(amountPaid);
        
    }

    
}
