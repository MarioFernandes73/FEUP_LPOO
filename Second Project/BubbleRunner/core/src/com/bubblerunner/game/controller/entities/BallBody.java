package com.bubblerunner.game.controller.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.model.entities.BallModel;

import static com.bubblerunner.game.utils.Constants.BALL_DENSITY;
import static com.bubblerunner.game.utils.Constants.BALL_FRICTION;
import static com.bubblerunner.game.utils.Constants.BALL_RADIUS;
import static com.bubblerunner.game.utils.Constants.BALL_RESTITUTION;


/**
 * Representation of a ball's body.
 */
public class BallBody extends EntityBody {

    /**
     * Model of the current ball
     */
    private BallModel model;


    /**
     * Creates the ball's body.
     *
     * @param world The world this body belongs to.
     * @param model The basic model of the ball.
     */
    public BallBody(World world, BallModel model) {
        super();

        this.model = model;

        // Create the ball body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);

        // Create the ball body
        body.setTransform(model.getX(), model.getY(), 0); // Middle of the viewport, no rotation

        // Create circle shape
        CircleShape circle = new CircleShape();
        circle.setRadius(BALL_RADIUS); // 22cm / 2

        // Create ball fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = BALL_DENSITY;      // how heavy is the ball
        fixtureDef.friction = BALL_FRICTION;    // how slippery is the ball
        fixtureDef.restitution = BALL_RESTITUTION; // how bouncy is the ball

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        circle.dispose();
    }


    /**
     * Returns the outer limits of the ball.
     *
     * @return The ball's bounds.
     */
    public Circle getBounds() {
        return new Circle(this.getX(), this.getY(), BALL_RADIUS);
    }

    /**
     * Returns the ball's HP.
     *
     * @return The ball's HP.
     */
    public int getHp() {
        return this.model.getHp();
    }


    /**
     * Set's the ball's HP
     *
     * @param hp the hp the ball will have.
     */
    public void setHp(int hp) {
        this.model.setHp(hp);
    }


}
