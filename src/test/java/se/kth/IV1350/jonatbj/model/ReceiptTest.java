package se.kth.IV1350.jonatbj.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

/**
 *
 * @author jonat
 */
public class ReceiptTest {

    private LocalDateTime localtime;

    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        localtime = LocalDateTime.now();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of updateTotalPrice method, of class Receipt.
     */
    @org.junit.jupiter.api.Test
    public void testUpdateTotalPrice() {
        System.out.println("updateTotalPrice");
        float itemPrice = 0.0F;
        Receipt instance = new Receipt(localtime);
        instance.updateTotalPrice(itemPrice);
        
    }

    /**
     * Test of updatetotalVAT method, of class Receipt.
     */
    @org.junit.jupiter.api.Test
    public void testUpdatetotalVAT() {
        System.out.println("updatetotalVAT");
        float itemVAT = 0.0F;
        float price = 0.0F;
        Receipt instance = new Receipt(localtime);
        instance.updatetotalVAT(itemVAT, price);
        
    }

    /**
     * Test of getTotalPrice method, of class Receipt.
     */
    @org.junit.jupiter.api.Test
    public void testGetTotalPrice() {
        System.out.println("getTotalPrice");
        Receipt instance = new Receipt(localtime);
        float expResult = 0.0F;
        float result = instance.getTotalPrice();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setAmountPaid method, of class Receipt.
     */
    @org.junit.jupiter.api.Test
    public void testSetAmountPaid() {
        System.out.println("setAmountPaid");
        int _amountPaid = 0;
        Receipt instance = new Receipt(localtime);
        instance.setAmountPaid(_amountPaid);
        
    }

    /**
     * Test of getTotalVAT method, of class Receipt.
     */
    @org.junit.jupiter.api.Test
    public void testGetTotalVAT() {
        System.out.println("getTotalVAT");
        Receipt instance = new Receipt(localtime);
        float expResult = 0.0F;
        float result = instance.getTotalVAT();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of addItemToList method, of class Receipt.
     */
    @org.junit.jupiter.api.Test
    public void testAddItemToList() {
        System.out.println("addItemToList");
        Item item = null;
        Receipt instance = new Receipt(localtime);
        instance.addItemToList(item);
    }
    
}
