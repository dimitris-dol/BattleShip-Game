package Models;


import javafx.scene.Parent;

/**
 * <p>The Ship Model of the game/p>
 *
 * @author Dologlou Dimitrios
 * @version 2.0
 * @since 2020-1-8
 */
public class Ship extends Parent {
    public int length;
    public boolean vertical;
    public int hitScore;
    public int SinkBonus;
    public String shipType;

    public int health;

    /**
     *
     * This method defines the model of the ship. Specifically the ship length, health, type,
     * points for hitting and points for hitting are all described to be used in the game.
     *
     * @param length the length of the ship which also describes its health
     * @param vertical a boolean that shows whether the ships is placed vertically or horizontally
     * @param hitScore the amount of points the user earns if he hits the ship
     * @param SinkBonus the amount of points the user earns if he sinks the ship
     * @param shipType the type(name) of the ship
     */
    public Ship(int length, boolean vertical, int hitScore, int SinkBonus, String shipType) {
        this.length = length;
        this.vertical = vertical;
        health = length;
        this.hitScore = hitScore;
        this.SinkBonus = SinkBonus;
        this.shipType = shipType;
    }

    /**
     * Simple method that deducts ship health if the ship is hit
     */
        public void hit() {
            health--;
        }

    /**
     * This method checks if a ship is still alive
     *
     * @return returns the boolean of whether it is alive or not
     */
        public boolean isAlive () {
            return health > 0;
        }

    /**
     * This method registers hit ships by checking their difference between their
     * length and health.
     *
     * @return returns the boolean on whether a ship is indeed hit or not
     */
    public boolean isHit(){
        return !(health == length);
    }
}