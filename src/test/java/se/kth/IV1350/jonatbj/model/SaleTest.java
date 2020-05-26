package se.kth.IV1350.jonatbj.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.IV1350.jonatbj.integration.ExternalInventorySystem;
import java.time.*;

/**
 *
 * @author jonat
 */
public class SaleTest {
    LocalDateTime localTime = LocalDateTime.now();
    
    /**
     * Test of the registerItem method, of class Sale, with the wrong identifier
     */
    @Test
    public void testRegisterItemWithANullItemID()
    {
        Sale instance = new Sale();
        int s = 7;
        instance.registerItem(s, new ExternalInventorySystem());
        assertTrue(instance.getItems().isEmpty(), "List is empty");
    }
    
    /**
     * Test of registerItem method, when it works as it is supposed to
     */
    @Test
    public void testRetgisterItem()
    {
        Sale instance = new Sale();
        int i = 1;
        Item item = new Item(1, "Milk", (float )10.5, "1 L of milk", (float) .12, 1);
        instance.registerItem(i, new ExternalInventorySystem());
        assertTrue(instance.getItems().get(0).getItemID() == item.getItemID(), "this works");
    }
    
    /**
     * Test of registerItem to see if quantity updates
     */
    @Test
    public void testRegisterItemForUpdatesQuantity()
    {
        Sale instance = new Sale();
        int checkToSeeIfQuantityIsRight = 2;
        instance.registerItem(1, new ExternalInventorySystem());
        instance.registerItem(1, new ExternalInventorySystem());
        assertTrue(instance.getItems().get(0).getQuantity() == checkToSeeIfQuantityIsRight, "Quantity is right");
    }



    /**
     * Test of paymentProcess method, without enough money being paid.
     */
    @Test
    public void testOfPaymentProcessWhenThereIsNotEnougMoneyPaid() {
        System.out.println("paymentProcess");
        int amountPaid = 0;
        Sale instance = new Sale();
        ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
        instance.registerItem(2, externalInventorySystem);
        boolean testingToSeeIfThisVariableWillBeFalse = instance.paymentProcess(amountPaid);
        assertFalse(testingToSeeIfThisVariableWillBeFalse == true, "works");
    }

    /**
     * Test of paymentProcess method, with enough money being paid (optimal scenario)
     */
    @Test
    public void testOfPaymentProcessWhenThereIsEnoughMoneyPaid()
    {
        int amountPaid = 23;
        Sale instance = new Sale();
        ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
        instance.registerItem(2, externalInventorySystem);
        boolean testingToSeeIfThisVariableWillBeTrue = instance.paymentProcess(amountPaid);
        assertTrue(testingToSeeIfThisVariableWillBeTrue == true, "It works");
    }

    /**
     * Testing paymentProcess method to see if change is correct
     */
    @Test
    public void testOfPaymentProcessToSeeIfChangeIsCorrect()
    {
        int amountPaid = 23;
        float change = 0.5f;
        Sale instance = new Sale();
        ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
        instance.registerItem(2, externalInventorySystem);
        instance.paymentProcess(amountPaid);
        assertTrue(instance.receipt.getChange() == change, "It works");
    }
}
