package com.bubblerunner.game.model.entities;

import com.bubblerunner.game.utils.Point;

import static com.bubblerunner.game.utils.Constants.STARTING_HP;

/**
 * A model representing the user ball.
 */
public class BallModel extends EntityModel {

    /**
     * The hp of the ball.
     */
    private int hp;

    /**
     * Creates a new ball model in a certain position.
     *
     * @param coordinates the coordinate in meters
     */
    public BallModel(Point<Float> coordinates) {
        super(coordinates);
        this.hp = STARTING_HP;
    }

    /**
     * Returns the ball's hp.
     *
     * @return the ball's hp.
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Sets the ball's hp.
     *
     * @param hp the hp the ball has.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns the model's type.
     *
     * @return the model's type.
     */
    @Override
    public ModelType getType() {
        return ModelType.BALL;
    }
}
