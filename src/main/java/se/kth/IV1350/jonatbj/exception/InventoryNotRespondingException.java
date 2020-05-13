package se.kth.IV1350.jonatbj.exception;

/**
 * This class handles when the database is not responding
 */
public class InventoryNotRespondingException extends Exception {
    
    /**
     * Creates an instance of the exception in question
     * @param errorMessage the message that will be displayed to the user when the exception occurs
     */
    public InventoryNotRespondingException(String errorMessage)
    {
        super(errorMessage);
    }
}