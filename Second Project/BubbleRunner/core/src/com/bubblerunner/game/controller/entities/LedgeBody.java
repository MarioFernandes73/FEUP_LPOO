package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.model.entities.EntityModel;
import com.bubblerunner.game.model.entities.LedgeModel;

import static com.bubblerunner.game.constants.Constants.LEDGE_DENSITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_FRICTION;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Z;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_RESTITUTION;

/**
 * Created by Mario on 04/06/2017.
 */

public class LedgeBody extends EntityBody {

    public LedgeBody(World world, LedgeModel model){
        super();

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

}
