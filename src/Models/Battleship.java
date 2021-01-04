package Models;

public class Battleship extends Ship{
    public int type;
    public boolean vertical = true;

    private int health;

    public Battleship(int type, boolean vertical) {
        this.type = 4;
        this.vertical = vertical;
        health = 4;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
