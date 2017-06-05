package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.model.entities.EntityModel;
import com.bubblerunner.game.model.entities.LedgeModel;

import static com.bubblerunner.game.constants.Constants.LEDGE_DENSITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_FRICTION;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Z;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_RESTITUTION;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY;

/**
 * Created by Mario on 04/06/2017.
 */

public class LedgeBody extends EntityBody {

    private LedgeModel model;
    private boolean hasActor;
    private boolean needsDelete;
    private boolean canDelete;

    public LedgeBody(World world, LedgeModel model){
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

        body.setTransform(model.getX()+model.getWidth()/2, model.getY()+model.getHeight()/2, LEDGE_INITIAL_POS_Z); // Initial position

        this.body.setLinearVelocity(0,LEDGE_INITIAL_VELOCITY);


        // Create rectangular shape
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(model.getWidth()/2, model.getHeight()/2); // Dimensions (half width, half height)

        // Create ground fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = LEDGE_DENSITY;      // how heavy is the ledge
        fixtureDef.friction =  LEDGE_FRICTION;    // how slippery is the ledge
        fixtureDef.restitution =  LEDGE_RESTITUTION; // how bouncy is the ledge

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        rectangle.dispose();
    }

    public void setPos(float x, float y){
        this.model.setPosition(x,y);
    }

    public boolean hasSpawnedAnother(){
        return this.model.getSpawnedAnother();
    }

    public void setSpawnedAnother(boolean value){
        this.model.setSpawnedAnother(value);
    }

    public void delete(){
        this.body.setUserData(null);
        this.body = null;
    }

    public void setVelocity(float yVelocity){
        this.body.setLinearVelocity(0, yVelocity);
    }

    public Rectangle getBounds(){
        return new Rectangle(this.getX(), this.getY(), this.model.getWidth(), this.model.getHeight());
    }

    public LEDGE_LETHALITY getLethality(){
        return this.model.getLethality();
    }

    public float getWidth(){
        return this.model.getWidth();
    }

    public float getHeight(){
        return this.model.getHeight();
    }

    public void setHasActor(boolean value){this.hasActor = value; }

    public boolean getHasActor(){return this.hasActor;}

    public boolean getNeedsDelete(){return this.needsDelete;}

    public void setNeedsDelete(boolean value){this.needsDelete = value; }

    public void setCanDelete(boolean value){this.canDelete = value;}

    public boolean getCanDelete(){return this.canDelete;}

}
