package com.bubblerunner.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.controller.entities.BallBody;
import com.bubblerunner.game.model.entities.BallModel;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.BALL_DENSITY;
import static com.bubblerunner.game.constants.Constants.BALL_FRAMES_DURATION;
import static com.bubblerunner.game.constants.Constants.BALL_FRICTION;
import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_FRAME;
import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_POS_X;
import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.BALL_NUMBER_FRAMES;
import static com.bubblerunner.game.constants.Constants.BALL_RADIUS;
import static com.bubblerunner.game.constants.Constants.BALL_RESTITUTION;
import static com.bubblerunner.game.constants.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.constants.Constants.STARTING_HP;

/**
 * Created by Mario on 12/04/2017.
 */

public class BallActor extends Actor {

    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private Texture texture;
    private float stateTime = 0;
    private BallBody body;

    public BallActor(GraphicsManager graphicsManager, BallBody body) {
        this.texture = graphicsManager.gameGraphics.ball;
        createAnimation();
        this.setPosition(BALL_INITIAL_POS_X, BALL_INITIAL_POS_Y);
        this.body = body;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        this.sprite.setPosition(getX(), getY());
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        this.sprite.setRotation(getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(body.getX()/PIXEL_TO_METER, body.getY()/PIXEL_TO_METER);
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(getColor());
        sprite.draw(batch);
    }

    private void createAnimation(){
        // Split the texture into frame
        TextureRegion[][] thrustRegion = TextureRegion.split(texture, texture.getWidth() / BALL_NUMBER_FRAMES, texture.getHeight());

        // Put the frames into a uni-dimensional array
        TextureRegion[] frames = new TextureRegion[BALL_NUMBER_FRAMES];
        System.arraycopy(thrustRegion[0], 0, frames, 0, BALL_NUMBER_FRAMES);

        // Create the animation
        this.animation = new Animation<TextureRegion>(BALL_FRAMES_DURATION, frames);
        this.sprite = new Sprite(this.animation.getKeyFrame(BALL_INITIAL_FRAME));

        // Necessary so that inputs events are registered correctly
        setWidth(this.animation.getKeyFrame(BALL_INITIAL_FRAME).getRegionWidth());
        setHeight(texture.getHeight());

        // Necessary so that rotations are correctly processed
        setOrigin(getWidth() / 2, getHeight() / 2);
        this.sprite.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void setBodyLinearVelocity(float x, float y){
        this.body.setLinearVelocity(x,y);
    }

}
