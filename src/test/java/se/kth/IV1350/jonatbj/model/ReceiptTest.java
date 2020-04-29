package se.kth.IV1350.jonatbj.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;


public class ReceiptTest {

    private LocalDateTime localtime;

    /**
     * Test of updateTotalPrice method, of class Receipt. With zero, so it should be
     * the same after as before
     */
    @org.junit.jupiter.api.Test
    public void testUpdateTotalPrice() {
        System.out.println("updateTotalPrice");
        float itemPrice = 0.0F;
        Receipt instance = new Receipt(localtime);
        float totalPriceBefore = instance.getTotalPrice();
        instance.updateTotalPrice(itemPrice);
        assertTrue(totalPriceBefore == instance.getTotalPrice(), "it works");
    }
    
    /**
     * Test of updateTotalPrice method, when everything works like as it should
     */
    @Test
    public void testUpdatePriceWhenItShouldWorkFine()
    {
        float itemPrice = 10.3F;
        Receipt instance = new Receipt(localtime);
        float priceToCheckAgainst = 10.3F;
        instance.updateTotalPrice(itemPrice);
        assertTrue(instance.getTotalPrice() == priceToCheckAgainst, "works fine");
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
        assertTrue(instance.getTotalVAT() == 0.0F, "works fine");
    }






    
    
}
