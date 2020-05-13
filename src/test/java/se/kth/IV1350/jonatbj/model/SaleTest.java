package se.kth.IV1350.jonatbj.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.IV1350.jonatbj.exception.InventoryNotRespondingException;
import se.kth.IV1350.jonatbj.integration.ExternalInventorySystem;
import java.time.*;
import se.kth.IV1350.jonatbj.exception.*;

/**
 *
 * @author jonat
 */
public class SaleTest {
    LocalDateTime localTime = LocalDateTime.now();
    
    /**
     * Test of the registerItem method, of class Sale, with the hardcoded number that makes the method
     * throw Inventory not responding exception
     */
    @Test
   public void testRegisterItemWhenInventoryIsNotResponding()
    {
        Sale instance = new Sale();
        int s = 4;
        try
        {
            instance.registerItem(s, new ExternalInventorySystem());
            fail("Inventory system is working");
        }
        catch(InventoryNotRespondingException e)
        {
            assertTrue(e.getMessage() == "Inventory system is not responding", "it's working");
        }
    }
    
    /**
     * Test of getitem method, When there is a invalid item ID sent to it
     */
    @Test
    public void testGetItemWithInvalidItemID()
    {
        ExternalInventorySystem instance = new ExternalInventorySystem();
        int i = 6;
        try
        {
            instance.getItem(i);
            fail("The Id was not invalid");
        }
        catch(ItemNotInInventoryException e)
        {
            assertTrue(e.getMessage() == "The itemID does not exist, no item was added", "It is working");
        }
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
        float change = amountPaid - instance.receipt.getTotalPrice();
        assertTrue(instance.receipt.getChange() == change, "works");
    }

    
}
