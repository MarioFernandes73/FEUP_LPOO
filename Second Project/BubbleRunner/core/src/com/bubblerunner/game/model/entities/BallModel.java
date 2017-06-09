package com.bubblerunner.game.model.entities;

/**
 * Created by Mario on 04/06/2017.
 */

import com.bubblerunner.game.utils.Point;

import static com.bubblerunner.game.utils.Constants.STARTING_HP;

/**
 * A model representing a the user space ship.
 */
public class BallModel extends EntityModel {
    /**
     * Is this ship accelerating in this update delta
     */
    private boolean falling;
    private int hp;

    /**
     * Creates a new ship model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     */
    public BallModel(Point<Float> coordinates) {
        super(coordinates);
        this.hp = STARTING_HP;
        this.falling = true;
    }

    /**
     * Set the accelerating flag for this ship
     *
     * @param falling the accelerating tag
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * Is the ship accelerating in this update
     *
     * @return the accelerating flag
     */
    public boolean isFalling() {
        return falling;
    }


    public int getHp(){
        return this.hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    @Override
    public ModelType getType() {
        return ModelType.BALL;
    }
}
