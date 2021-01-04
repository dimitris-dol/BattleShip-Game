package Models;

public class Carrier extends Ship {
    public int type;
    public boolean vertical = true;

    private int health;

    public Carrier(int type, boolean vertical) {
        this.type = 5;
        this.vertical = vertical;
        health = 5;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}


