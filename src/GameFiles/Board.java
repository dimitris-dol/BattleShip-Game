package GameFiles;

import Exceptions.AdjacentTilesException;
import Exceptions.OverlapTilesException;
import Exceptions.OversizeException;
import Models.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import java.util.List;

import static jdk.nashorn.internal.runtime.Context.printStackTrace;

/**
 * <p>The Board Class where the game is played</p>
 *
 * @author Dologlou Dimitrios
 * @version 2.0
 * @since 2020-1-8
 */
public class Board extends Parent {
    private VBox rows = new VBox();
    private boolean enemy;
    public int ships = 5;

    public ArrayList<Ship> Ships = new ArrayList<>();

    /**
     *  The main Board method that allows the creation of the 4 total boards,
     *  2 per player.
     *
     * @param enemy the boolean value that checks if the current board is for the player or the enemy
     * @param handler handles the click-event for when the player shoots or places their ships
     */
    public Board(boolean enemy, EventHandler<? super MouseEvent> handler) {
        this.enemy = enemy;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell c = new Cell(x, y, this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }

            rows.getChildren().add(row);
        }

        getChildren().add(rows);
    }

    /**
     *  The placeShip method places the ships for the player and enemy when it is called.
     *
     * @param ship the specific ship placed
     * @param x horizontal coordinate where the ship's start is placed
     * @param y vertical coordinate where the ship's start is placed
     * @return returns true or false depending if the placement of the ship was successful.
     */
    public boolean placeShip(Ship ship, int x, int y) {
            if (canPlaceShip(ship, x, y)) {
                int length = ship.type;

                if (ship.vertical) {
                    for (int i = y; i < y + length; i++) {
                        Cell cell = getCell(x, i);
                        cell.ship = ship;
                        if (!enemy) {
                            switch (ship.shipType) {
                                case "Carrier":
                                    cell.setFill(Color.LIGHTBLUE);
                                    cell.setStroke(Color.BLUE);
                                    break;
                                case "Battleship":
                                    cell.setFill(Color.PLUM);
                                    cell.setStroke(Color.ORANGERED);
                                    break;
                                case "Cruiser":
                                    cell.setFill(Color.GOLD);
                                    cell.setStroke(Color.ORANGE);
                                    break;
                                case "Submarine":
                                    cell.setFill(Color.SANDYBROWN);
                                    cell.setStroke(Color.BROWN);
                                    break;
                                default:
                                    cell.setFill(Color.LIGHTGREEN);
                                    cell.setStroke(Color.GREEN);
                                    break;
                            }

                        }
                    }
                } else {
                    for (int i = x; i < x + length; i++) {
                        Cell cell = getCell(i, y);
                        cell.ship = ship;
                        if (!enemy) {
                            switch (ship.shipType) {
                                case "Carrier":
                                    cell.setFill(Color.LIGHTBLUE);
                                    cell.setStroke(Color.BLUE);
                                    break;
                                case "Battleship":
                                    cell.setFill(Color.PLUM);
                                    cell.setStroke(Color.ORANGERED);
                                    break;
                                case "Cruiser":
                                    cell.setFill(Color.GOLD);
                                    cell.setStroke(Color.ORANGE);
                                    break;
                                case "Submarine":
                                    cell.setFill(Color.SANDYBROWN);
                                    cell.setStroke(Color.BROWN);
                                    break;
                                default:
                                    cell.setFill(Color.LIGHTGREEN);
                                    cell.setStroke(Color.GREEN);
                                    break;
                            }
                        }
                    }
                }
                Ships.add(ship);
                return true;
            }

            return false;
    }

    /**
     * This is a simple method selecting the specified Cell
     *
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @return return that Cell
     */
    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    /**
     *  This method gets a Cell's upper, lower, right and left neighboring Cells
     *
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @return returns the array list of the cell's neighbors
     */
    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<>();

        for (Point2D p : points) {
            try{
                isValidPoint(p);
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }catch (OversizeException e ){
                printStackTrace(e);
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    /**
     * This method checks the ship placement. Specifically every method is implemented in order
     * to be called by its Class. If the ship placement doesn't call any exception, the ship will be placed
     * by the earlier function.
     *
     * @param ship the ship to be placed
     * @param x horizontal coordinate where the ship's start is to be placed
     * @param y vertical coordinate where the ship's start is to be placed
     * @return returns a boolean on whether the ship placement is ok or not
     */
     private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                try {
                    isValidPoint(x, i);
                    Cell cell = getCell(x, i);
                    try {
                        isCellEmpty(cell);
                    } catch (OverlapTilesException e) {
                        return false;
                    }

                    for (Cell neighbor : getNeighbors(x, i)) {
                        try {
                            isValidPoint(x, i);
                            try {
                                isNeighborEmpty(neighbor);
                            } catch (AdjacentTilesException e) {
                                return false;
                            }
                        } catch (OversizeException e) {
                            return false;
                        }
                    }

                } catch (OversizeException e) {
                    return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                try {
                    isValidPoint(i, y);

                    Cell cell = getCell(i, y);
                    try {
                        isCellEmpty(cell);
                    } catch (OverlapTilesException e) {
                        return false;
                    }

                    for (Cell neighbor : getNeighbors(i, y)) {
                        try {
                            isValidPoint(i, y);
                            try {
                                isNeighborEmpty(neighbor);
                            } catch (AdjacentTilesException e) {
                                return false;
                            }
                        } catch (OversizeException e) {
                            return false;
                        }
                    }

                } catch (OversizeException e) {
                    return false;
                }

            }
        }

         return true;
     }

    /**
     * This method checks if the Cell requested is indeed on the Board's limits
     *
     * @param point the cell's (point's) parameters
     * @return returns the boolean on whether the point is ok
     * @throws OversizeException throws the OversizeException if it is not ok
     */
    private boolean isValidPoint(Point2D point) throws OversizeException{
        return isValidPoint(point.getX(), point.getY());
    }

    /**
     * This method is called above, in order to check if the point called is valid. That way
     *  it is decided if the cell selection is ok
     * @param x horizontal coordinate where the cell(point) is
     * @param y vertical coordinate where the cell(point) is
     * @return returns the boolean on whether the choise is within the Board borders
     * @throws OversizeException throws the OversizeException if it is not ok
     */
    private boolean isValidPoint(double x, double y) throws OversizeException {
        if (x >= 0 && x < 10 && y >= 0 && y < 10){
            return true;
        }else{
            throw new OversizeException("Can't place ship. Out of bounds placement.");
        }
    }

    /**
     * This method checks if there is a ship already in the specified cell
     *
     * @param cell The cell to be checked if it is a valid choice
     * @return returns the boolean on whether the cell is empty
     * @throws OverlapTilesException Exception thrown when there is a ship in that cell
     */
    private boolean isCellEmpty(Cell cell) throws OverlapTilesException {
        if (cell.ship == null){
            return true;
        }else{
            throw new OverlapTilesException("There is a ship already in this position.");
        }
    }

    /**
     * In order to check for adjacent tiles, the neighbors of a cell are checked. If they contain
     * a ship the AdjacentTiles Exception will be thrown
     *
     * @param cell cell The cell to be checked if it is a valid choice
     * @return returns the boolean on whether the neighboring cells are empty
     * @throws AdjacentTilesException Exception thrown when there are neighboring cells with ships
     */
    private boolean isNeighborEmpty(Cell cell) throws AdjacentTilesException {
        if (cell.ship == null){
            return true;
        }
        else{
            throw  new AdjacentTilesException("Too close to ship. Choose a different tile.");
        }
    }

}
