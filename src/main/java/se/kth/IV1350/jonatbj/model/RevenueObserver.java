package se.kth.IV1350.jonatbj.model;

public interface RevenueObserver {
    
    /**
     * Updates the amount paid to the display
     */
    void paymentUpdate(Sale sale);

}