package GameFiles;

import Models.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board extends Parent {
    private VBox rows = new VBox();
    private boolean enemy = false;
    public int ships = 5;
    private Carrier carrier;
    private Battleship battleship;
    private Cruiser cruiser;
    private Submarine submarine;
    private Destroyer destroyer;

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

   /* public boolean placeShip(Ship ship, int x, int y) {
            if (canPlaceShip(ship, x, y)) {
                int length = ship.type;

                if (ship.vertical) {
                    for (int i = y; i < y + length; i++) {
                        Cell cell = getCell(x, i);
                        cell.ship = ship;
                        if (!enemy) {
                            cell.setFill(Color.LIGHTGREEN);
                            cell.setStroke(Color.GREEN);
                        }
                    }
                } else {
                    for (int i = x; i < x + length; i++) {
                        Cell cell = getCell(i, y);
                        cell.ship = ship;
                        if (!enemy) {
                            cell.setFill(Color.LIGHTGREEN);
                            cell.setStroke(Color.GREEN);
                        }
                    }
                }

                return true;
            }

            return false;
    } */

    public boolean placeCarrier(Carrier carrier, int x, int y) {
        if (canPlaceCarrier(carrier, x, y)) {
            int length = carrier.type;

            if (carrier.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.carrier = carrier;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.carrier = carrier;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public boolean placeBattleship(Battleship battleship, int x, int y) {
        if (canPlaceBattleship(battleship, x, y)) {
            int length = battleship.type;

            if (battleship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.battleship = battleship;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.battleship = battleship;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public boolean placeCruiser(Cruiser cruiser, int x, int y) {
        if (canPlaceCruiser(cruiser, x, y)) {
            int length = cruiser.type;

            if (cruiser.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.cruiser = cruiser;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.cruiser = cruiser;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public boolean placeSubmarine(Submarine submarine, int x, int y) {
        if (canPlaceSubmarine(submarine, x, y)) {
            int length = submarine.type;

            if (submarine.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.submarine = submarine;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.submarine = submarine;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public boolean placeDestroyer(Destroyer destroyer, int x, int y) {
        if (canPlaceDestroyer(destroyer, x, y)) {
            int length = destroyer.type;

            if (destroyer.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.destroyer = destroyer;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.destroyer = destroyer;
                    if (!enemy) {
                        cell.setFill(Color.LIGHTGREEN);
                        cell.setStroke(Color.GREEN);
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

        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

   /*  private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        }

        return true;
    } */

    private boolean canPlaceCarrier(Carrier carrier, int x, int y) {
        int length = carrier.type;

        if (carrier.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean canPlaceBattleship(Battleship battleship, int x, int y) {
        int length = battleship.type;

        if (battleship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean canPlaceCruiser(Cruiser cruiser, int x, int y) {
        int length = cruiser.type;

        if (cruiser.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean canPlaceSubmarine(Submarine submarine, int x, int y) {
        int length = submarine.type;

        if (submarine.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean canPlaceDestroyer(Destroyer destroyer, int x, int y) {
        int length = destroyer.type;

        if (destroyer.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.carrier != null || cell.battleship!=null || cell.cruiser!=null || cell.submarine!=null || cell.destroyer!=null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.carrier != null || neighbor.battleship!=null || neighbor.cruiser!=null || neighbor.submarine!=null || neighbor.destroyer!=null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
}
