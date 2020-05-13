package se.kth.IV1350.jonatbj.exception;

/**
 * This class deals with when the item itentifier is invalid
 */
public class ItemNotInInventoryException extends Exception 
{
    /**
     * Creates an instance of the exception in question
     * @param errorMessage the message that will be displayed to the user when the exception occurs
     */
    public ItemNotInInventoryException(String errorMessage)
    {
        super(errorMessage);
    }
}