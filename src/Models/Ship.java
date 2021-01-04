package Models;


import javafx.scene.Parent;

public class Ship extends Parent {
    public int type;
    public boolean vertical;
    public int hitScore;
    public int SinkBonus;
    public String shipType;

    private int health;

    public Ship(int type, boolean vertical, int hitScore, int SinkBonus, String shipType) {
        this.type = type;
        this.vertical = vertical;
        health = type;
        this.hitScore = hitScore;
        this.SinkBonus = SinkBonus;
        this.shipType = shipType;
    }
        public void hit() {
            health--;
        }

        public boolean isAlive () {
            return health > 0;
        }
}