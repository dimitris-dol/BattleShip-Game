package GameFiles;

import Models.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public Carrier carrier = null;
    public Battleship battleship = null;
    public Cruiser cruiser = null;
    public Submarine submarine= null;
    public Destroyer destroyer = null;
    public boolean wasShot = false;

    private Board board;

    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            if (!ship.isAlive()) {
                board.ships--;
            }
            return true;
        }

        if (carrier != null) {
            carrier.hit();
            setFill(Color.RED);
            if (!carrier.isAlive()) {
                board.ships--;
            }
            return true;
        }

        if (cruiser != null) {
            cruiser.hit();
            setFill(Color.RED);
            if (!cruiser.isAlive()) {
                board.ships--;
            }
            return true;
        }

        if (battleship != null) {
            battleship.hit();
            setFill(Color.RED);
            if (!battleship.isAlive()) {
                board.ships--;
            }
            return true;
        }

        if (submarine != null) {
            submarine.hit();
            setFill(Color.RED);
            if (!submarine.isAlive()) {
                board.ships--;
            }
            return true;
        }

        if (destroyer != null) {
            destroyer.hit();
            setFill(Color.RED);
            if (!destroyer.isAlive()) {
                board.ships--;
            }
            return true;
        }

        return false;
    }

}