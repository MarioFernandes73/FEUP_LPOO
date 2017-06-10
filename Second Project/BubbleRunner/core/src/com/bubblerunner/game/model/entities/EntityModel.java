package com.bubblerunner.game.model.entities;

import com.bubblerunner.game.utils.Point;

/**
 * An abstract model representing an entity belonging to a game model.
 */
public abstract class EntityModel {
    public enum ModelType {BALL, MEDIUMLEDGE, SMALLLEDGE}

    /**
     * The x-coordinate of this model in meters.
     */
    private float x;

    /**
     * The y-coordinate of this model in meters.
     */
    private float y;


    /**
     * Constructs a model with a position and a rotation.
     *
     * @param coordinates The x-coordinate of this entity in meters.
     */
    EntityModel(Point<Float> coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }


    public abstract ModelType getType();
}
