package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Wrapper class that represents an abstract physical
 * body supported by a Box2D body.
 */
public abstract class EntityBody {

    /**
     * The Box2D body that supports this body.
     */
    protected Body body;


    /**
     * Constructs an entity without a body.
     *
     */
    public EntityBody() {
            body = null;
        }


    /**
     * Wraps the getX method from the Box2D body class.
     *
     * @return the x-coordinate of this body.
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Wraps the getY method from the Box2D body class.
     *
     * @return the y-coordinate of this body.
     */
    public float getY() {
        return body.getPosition().y;
    }


    /**
     * Wraps the setTransform method from the Box2D body class.
     *
     * @param x the new x-coordinate for this body
     * @param y the new y-coordinate for this body
     * @param angle the new rotation angle for this body
     */
    public void setTransform(float x, float y, float angle) {
        body.setTransform(x, y, angle);
    }

    /**
     * Sets the angular velocity of this object in the direction it is rotated.
     *
     * @param velocityX the new x component of the linear velocity angle for this body
     * @param velocityY the new y component of the linear velocity angle for this body
     */
    public void setLinearVelocity(float velocityX, float velocityY) {
        body.setLinearVelocity(velocityX, velocityY);
    }


    /**
     * Returns the body of this entity
     *
     * @return the body of this entity
     */
    public Body getBody(){return this.body;}

}
