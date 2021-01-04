package Models;

public class Destroyer extends Ship{
    public int type;
    public boolean vertical = true;

    private int health;

    public Destroyer(int type, boolean vertical) {
        this.type = 2;
        this.vertical = vertical;
        health = 2;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
