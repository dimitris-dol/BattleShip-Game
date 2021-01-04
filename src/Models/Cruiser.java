package Models;

public class Cruiser extends Ship{
    public int type;
    public boolean vertical = true;

    private int health;

    public Cruiser(int type, boolean vertical) {
        this.type = 3;
        this.vertical = vertical;
        health = 3;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
