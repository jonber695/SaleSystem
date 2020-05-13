package se.kth.IV1350.jonatbj.view;

import se.kth.IV1350.jonatbj.model.*;

public class TotalRevenueView implements RevenueObserver {
    int totalAmountPaid;

    public void paymentUpdate(Sale sale)
    {
        addToTotalAmountPaid(sale);
        printTotalRevenue();
    }

    private void addToTotalAmountPaid(Sale sale)
    {
        totalAmountPaid += sale.getReceiptTotalAmountPaid();
    }

    private void printTotalRevenue()
    {
        System.out.println("Total revenue: " + totalAmountPaid + " kr.");
    }
}