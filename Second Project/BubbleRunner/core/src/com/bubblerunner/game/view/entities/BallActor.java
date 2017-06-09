package com.bubblerunner.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.controller.entities.BallBody;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.utils.Constants.BALL_FRAMES_DURATION;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_FRAME;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_X;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.BALL_NUMBER_FRAMES;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;

/**
 * Created by Mario on 12/04/2017.
 */

public class BallActor extends Actor {

    private Sprite sprite;
    private Sprite sprite2;
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> animation2;
    private Texture texture;
    private Texture texture2;
    private float stateTime = 0;
    private BallBody body;
    private float lastYPos;
    private boolean isFalling;

    public BallActor(GraphicsManager graphicsManager, BallBody body) {
        this.texture = graphicsManager.ball;
        this.texture2 = graphicsManager.flyingBall;
        createAnimations();
        this.setPosition(BALL_INITIAL_POS_X, BALL_INITIAL_POS_Y);
        this.lastYPos = BALL_INITIAL_POS_Y;
        this.isFalling = true;
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
        this.sprite2.setPosition(getX(), getY());
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        this.sprite.setRotation(getRotation());
        this.sprite2.setRotation(getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(body.getY()/PIXEL_TO_METER < lastYPos ){
            isFalling = true;
        } else {
            isFalling = false;
        }
        lastYPos = body.getY()/PIXEL_TO_METER;
        this.setPosition(body.getX()/PIXEL_TO_METER, body.getY()/PIXEL_TO_METER);
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
        sprite2.setRegion(animation2.getKeyFrame(stateTime, true));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isFalling){
            sprite2.setColor(getColor());
            sprite2.draw(batch);
        } else {
            sprite.setColor(getColor());
            sprite.draw(batch);
        }
    }

    private void createAnimations(){
        // Split the texture into frame
        TextureRegion[][] thrustRegion = TextureRegion.split(texture, texture.getWidth() / BALL_NUMBER_FRAMES, texture.getHeight());
        TextureRegion[][] thrustRegion2 = TextureRegion.split(texture2, texture2.getWidth() / BALL_NUMBER_FRAMES, texture2.getHeight());

        // Put the frames into a uni-dimensional array
        TextureRegion[] frames = new TextureRegion[BALL_NUMBER_FRAMES];
        System.arraycopy(thrustRegion[0], 0, frames, 0, BALL_NUMBER_FRAMES);
        TextureRegion[] frames2 = new TextureRegion[BALL_NUMBER_FRAMES];
        System.arraycopy(thrustRegion2[0], 0, frames2, 0, BALL_NUMBER_FRAMES);

        // Create the animation
        this.animation = new Animation<TextureRegion>(BALL_FRAMES_DURATION, frames);
        this.sprite = new Sprite(this.animation.getKeyFrame(BALL_INITIAL_FRAME));
        this.animation2 = new Animation<TextureRegion>(BALL_FRAMES_DURATION, frames2);
        this.sprite2 = new Sprite(this.animation2.getKeyFrame(BALL_INITIAL_FRAME));

        // Necessary so that inputs events are registered correctly
        setWidth(this.animation.getKeyFrame(BALL_INITIAL_FRAME).getRegionWidth());
        setHeight(texture.getHeight());

        // Necessary so that rotations are correctly processed
        setOrigin(getWidth() / 2, getHeight() / 2);
        this.sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        this.sprite2.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void setBodyLinearVelocity(float x, float y){
        this.body.setLinearVelocity(x,y);
    }

}
