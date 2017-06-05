package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.model.entities.BallModel;
import com.bubblerunner.game.model.entities.EntityModel;

import static com.bubblerunner.game.constants.Constants.BALL_DENSITY;
import static com.bubblerunner.game.constants.Constants.BALL_FRICTION;
import static com.bubblerunner.game.constants.Constants.BALL_RADIUS;
import static com.bubblerunner.game.constants.Constants.BALL_RESTITUTION;

/**
 * Created by Mario on 04/06/2017.
 */

public class BallBody extends EntityBody {

    private BallModel model;

    public BallBody(World world, BallModel model){
        super();

        this.model = model;

        // Create the ball body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);

        // Create the ball body
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        body.setTransform(Constants.VIEWPORT_WIDTH / 2, (Constants.VIEWPORT_WIDTH * ratio) / 2, 0); // Middle of the viewport, no rotation

        // Create circle shape
        CircleShape circle = new CircleShape();
        circle.setRadius(BALL_RADIUS); // 22cm / 2

        // Create ball fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = BALL_DENSITY;      // how heavy is the ball
        fixtureDef.friction =  BALL_FRICTION;    // how slippery is the ball
        fixtureDef.restitution =  BALL_RESTITUTION; // how bouncy is the ball

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        circle.dispose();
    }

    public Circle getBounds(){
        return new Circle(this.getX(), this.getY(), BALL_RADIUS);
    }

    public void setPos(float x, float y){
        this.model.setPosition(x,y);
    }

    public int getHp(){
        return this.model.getHp();
    }

    public void setHp(int hp){
        this.model.setHp(hp);
    }


}
