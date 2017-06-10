package com.bubblerunner.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.controller.entities.BallBody;
import com.bubblerunner.game.utils.AssetsManager;

import static com.bubblerunner.game.utils.Constants.BALL_FRAMES_DURATION;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_FRAME;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_X;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.BALL_NUMBER_FRAMES;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;

/**
 * Ball view extending LIBGdx Actor class.
 */
public class BallActor extends Actor {

    /**
     * Base sprite
     */
    private Sprite sprite;

    /**
     * Winged sprite
     */
    private Sprite sprite2;

    /**
     * Base animation
     */
    private Animation<TextureRegion> animation;

    /**
     * Winged animation
     */
    private Animation<TextureRegion> animation2;

    /**
     * Base texture
     */
    private Texture texture;

    /**
     * Winged texture
     */
    private Texture texture2;

    /**
     * Time to pass animation frames
     */
    private float stateTime = 0;

    /**
     * Ball's body which textures are mapped to.
     */
    private BallBody body;

    /**
     * Last updated position which is used to trigger
     * the isFalling flag.
     */
    private float lastYPos;

    /**
     * Flag used to select which animation to draw.
     */
    private boolean isFalling;

    /**
     * Constructs a new ball actor.
     *
     * @param assetsManager the manager which holds all textures.
     * @param body the ball's body.
     */
    public BallActor(AssetsManager assetsManager, BallBody body) {
        this.texture = assetsManager.ball;
        this.texture2 = assetsManager.flyingBall;
        createAnimations();
        this.setPosition(BALL_INITIAL_POS_X, BALL_INITIAL_POS_Y);
        this.lastYPos = BALL_INITIAL_POS_Y;
        this.isFalling = true;
        this.body = body;
    }

    /**
     * Override of setPostion method to adapt to a circle.
     *
     * @param x ball's x coordinate
     * @param y ball's y coordinate
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }

    /**
     * Override of positionChanged method to adapt the sprites.
     */
    @Override
    protected void positionChanged() {
        super.positionChanged();
        this.sprite.setPosition(getX(), getY());
        this.sprite2.setPosition(getX(), getY());
    }

    /**
     * Override of rotation changed even though the ball has no rotation.
     */
    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        this.sprite.setRotation(getRotation());
        this.sprite2.setRotation(getRotation());
    }

    /**
     * Act method to check if either the ball is falling or not
     * and to update the sprite's region.
     *
     * @param delta time variation.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        if (body.getY() / PIXEL_TO_METER < lastYPos) {
            isFalling = true;
        } else {
            isFalling = false;
        }
        lastYPos = body.getY() / PIXEL_TO_METER;
        this.setPosition(body.getX() / PIXEL_TO_METER, body.getY() / PIXEL_TO_METER);
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
        sprite2.setRegion(animation2.getKeyFrame(stateTime, true));
    }

    /**
     * Draw method which selects which sprite to draw.
     *
     * @param batch The batch to draw in.
     * @param parentAlpha parent's Alpha value
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isFalling) {
            sprite2.setColor(getColor());
            sprite2.draw(batch);
        } else {
            sprite.setColor(getColor());
            sprite.draw(batch);
        }
    }

    /**
     * Animation's creation method
     */
    private void createAnimations() {
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

    /**
     * Sets body's linear velocity, which is used by the controller.
     *
     * @param x value of the velocity
     * @param y value of the velocity
     */
    public void setBodyLinearVelocity(float x, float y) {
        this.body.setLinearVelocity(x, y);
    }

}
