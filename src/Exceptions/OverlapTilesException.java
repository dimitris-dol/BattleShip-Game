package Exceptions;

/**
 * <p>Exception class on Overlapped Tiles</p>
 *
 * @author Dologlou Dimitrios
 * @version 2.0
 * @since 2020-1-8
 */
public class OverlapTilesException extends Exception{
    /**
     * The main method simply calls the Exception message from the Board Class check.
     * If the player or the enemy tries to place ships that will overlap other ships ,
     * this message will be shown.
     *
     * @param message The message shown when the exception is thrown
     */
    public OverlapTilesException(String message){
        super(message);
    }
}
