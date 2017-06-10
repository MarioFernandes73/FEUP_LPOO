package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.model.entities.LedgeModel;

import static com.bubblerunner.game.utils.Constants.LEDGE_DENSITY;
import static com.bubblerunner.game.utils.Constants.LEDGE_FRICTION;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Z;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY;
import static com.bubblerunner.game.utils.Constants.LEDGE_RESTITUTION;

/**
 * Representation of a ledge's body.
 */
public class LedgeBody extends EntityBody {

    /**
     * Model of this ledge
     */
    private LedgeModel model;

    /**
     * Flag indicating if this body already has a body.
     */
    private boolean hasActor;

    /**
     * Flag indicating if this body needs to be deleted.
     */
    private boolean needsDelete;

    /**
     * Flag indicating if this body can be deleted.
     */
    private boolean canDelete;


    /**
     * Creates the ledge's body.
     *
     * @param world The world this body belongs to.
     * @param model The basic model of the ledge.
     */
    public LedgeBody(World world, LedgeModel model) {
        super();

        this.needsDelete = false;
        this.canDelete = false;

        this.model = model;

        // Create the ledge body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);

        body.setTransform(model.getX() + model.getWidth() / 2, model.getY() + model.getHeight() / 2, LEDGE_INITIAL_POS_Z); // Initial position

        this.body.setLinearVelocity(0, LEDGE_INITIAL_VELOCITY);


        // Create rectangular shape
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(model.getWidth() / 2, model.getHeight() / 2); // Dimensions (half width, half height)

        // Create ground fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = LEDGE_DENSITY;      // how heavy is the ledge
        fixtureDef.friction = LEDGE_FRICTION;    // how slippery is the ledge
        fixtureDef.restitution = LEDGE_RESTITUTION; // how bouncy is the ledge

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        rectangle.dispose();
    }

    /**
     * Returns the spawnedAnother flag.
     *
     * @return the spawnedAnother flag.
     */
    public boolean hasSpawnedAnother() {
        return this.model.getSpawnedAnother();
    }

    /**
     * Sets the spawnedAnother flag.
     *
     * @param value the value of the spawnedAnother flag.
     */
    public void setSpawnedAnother(boolean value) {
        this.model.setSpawnedAnother(value);
    }

    /**
     * Deletes a ledge.
     */
    public void delete() {
        this.body.setUserData(null);
        this.body = null;
    }

    /**
     * Sets the vertical velocity of this body
     *
     * @param yVelocity the vertical velocity of this body.
     */
    public void setVelocity(float yVelocity) {
        this.body.setLinearVelocity(0, yVelocity);
    }

    /**
     * Returns the bounds of this body.
     *
     * @return the bounds of this body.
     */
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), this.model.getWidth(), this.model.getHeight());
    }

    /**
     * Returns the lethality of this model
     *
     * @return returns either LETHAL if it's a spiked ledge or NONLETHAL if it's normal
     */
    public LEDGE_LETHALITY getLethality() {
        return this.model.getLethality();
    }

    /**
     * Sets this model's lethality
     *
     * @param lethality either LETHAL if it's a spiked ledge or NONLETHAL if it's normal
     */
    public void setLethality(LEDGE_LETHALITY lethality) {
        this.model.setLethality(lethality);
    }

    /**
     * Returns the width of this body.
     *
     * @return the width of this body.
     */
    public float getWidth() {
        return this.model.getWidth();
    }

    /**
     * Returns the height of this body.
     *
     * @return the height of this body.
     */
    public float getHeight() {
        return this.model.getHeight();
    }

    /**
     * Sets the flag hasActor
     *
     * @param value the value of the flag.
     */
    public void setHasActor(boolean value) {
        this.hasActor = value;
    }

    /**
     * Returns the hasActor flag.
     *
     * @return the hasActor flag.
     */
    public boolean getHasActor() {
        return this.hasActor;
    }


    /**
     * Returns the needsDelete flag.
     *
     * @return the needsDelete flag
     */
    public boolean getNeedsDelete() {
        return this.needsDelete;
    }

    /**
     * Sets the needDelete flag.
     *
     * @param value the value of the flag.
     */
    public void setNeedsDelete(boolean value) {
        this.needsDelete = value;
    }

    /**
     * Sets the canDelete flag.
     *
     * @param value the value of the flag.
     */
    public void setCanDelete(boolean value) {
        this.canDelete = value;
    }

    /**
     * Returns the canDelete flag.
     *
     * @return the canDelete flag.
     */
    public boolean getCanDelete() {
        return this.canDelete;
    }

}
