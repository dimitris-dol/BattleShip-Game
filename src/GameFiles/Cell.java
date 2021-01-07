package GameFiles;

import Models.Ship;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * <p>The Cell Class which is populated in the Board</p>
 *
 * @author Dologlou Dimitrios
 * @version 2.0
 * @since 2020-1-8
 */
public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;
    public int highscore=0;
    public int perc=0;
    private Board board;

    /**
     * The main method that creates the Cell
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @param board Board object to know, on which board the cell is
     */
    Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    /**
     * This method decides what happens on the board's Cells and the game's parameters.
     * If a shot is indeed a hit, the colour will change along side with the ship's status,
     * the player point's and the amount of ships, if it is a final hit.
     *
     * @return returns the boolean whether the shot was indeed a hit or not
     */
    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        if (ship != null) {
            ship.hit();
            perc =+1;
            highscore =+ship.hitScore;
            setFill(Color.RED);
            if (!ship.isAlive()) {
                highscore= highscore+ ship.SinkBonus;
                board.ships--;
            }
            return true;
        }

        return false;
    }

}