package GameFiles;

import Models.Ship;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;
    public int highscore=0;
    public int perc=0;
    private Board board;

    Cell(int x, int y, Board board) {
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