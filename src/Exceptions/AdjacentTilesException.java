package Exceptions;

/**
 * <p>Exception class on adjacent Tiles</p>
 *
 * @author Dologlou Dimitrios
 * @version 2.0
 * @since 2020-1-8
 */
public class AdjacentTilesException extends Exception {
    /**
     * The main method simply calls the Exception message from the Board Class check.
     * If the player or the enemy tries to place ships very close to each other,
     * this message will be shown.
     *
     * @param message The message shown when the exception is thrown
     */
    public AdjacentTilesException(String message){
        super(message);
    }
}
