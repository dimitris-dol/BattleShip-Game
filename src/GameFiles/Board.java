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

public class Board extends Parent {
    private VBox rows = new VBox();
    private boolean enemy;
    public int ships = 5;

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
                                    cell.setFill(Color.LIGHTCORAL);
                                    cell.setStroke(Color.RED);
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
                                    cell.setFill(Color.LIGHTCORAL);
                                    cell.setStroke(Color.RED);
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

                return true;
            }

            return false;
    }


    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

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

    private boolean isValidPoint(Point2D point) throws OversizeException{
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) throws OversizeException {
        if (x >= 0 && x < 10 && y >= 0 && y < 10){
            return true;
        }else{
            throw new OversizeException("Ship out of board!!!");
        }
    }

    private boolean isCellEmpty(Cell cell) throws OverlapTilesException {
        if (cell.ship == null){
            return true;
        }else{
            throw new OverlapTilesException("Already a ship in this position!!!");
        }
    }

    private boolean isNeighborEmpty(Cell cell) throws AdjacentTilesException {
        if (cell.ship == null){
            return true;
        }
        else{
            throw  new AdjacentTilesException("Too close to another ship!!!");
        }
    }

}
