package objects.brick;

import objects.GameObject;

/**
 * Abstract Brick class representing a brick in the game.
 * It defines properties and methods related to brick behavior.
 */

public abstract class Brick extends GameObject {
    private int hitPoints; // Number of hits the brick can take before being destroyed.

    public Brick(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    // takeHit method to reduce hit points when the brick is hit.
    public abstract void takeHit(int damage);

    // isDestroyed method to check if the brick is destroyed.
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }


}
