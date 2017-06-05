package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.model.entities.EntityModel;

/**
 * Created by Mario on 04/06/2017.
 */

public abstract class EntityBody {

    /**
     * The Box2D body that supports this body.
     */
    protected Body body;


    /**
     * Constructs a body representing a model in a certain world.
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
     * @param velocityX the new linear velocity angle for this body
     */
    public void setLinearVelocity(float velocityX, float velocityY) {
        body.setLinearVelocity(velocityX, velocityY);
    }

    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }

    public void setUserData(Object userData){this.body.setUserData(userData);}

    public Vector2 getPos(){return body.getPosition();}

    public Body getBody(){return this.body;}
}
