package com.bubblerunner.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.LEDGE_DENSITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_FRICTION;
import static com.bubblerunner.game.constants.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Z;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_RESTITUTION;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.constants.Constants.METER_TO_PIXEL;
import static com.bubblerunner.game.constants.Constants.PIXEL_TO_METER;

/**
 * Created by Mario on 12/04/2017.
 */

public class LedgeActor extends Actor {

    private Body body;
    private final Texture texture;
    private final int posXIndex;
    private final int posYIndex;
    private final int widthIndex;
    private World world;
    private boolean spawnedAnother = false;

    public LedgeActor(World world, GraphicsManager graphicsManager, int posXIndex,int posYIndex, int widthIndex) {
        this.world = world;
        this.posXIndex = posXIndex;
        this.posYIndex = posYIndex;
        this.widthIndex = widthIndex;
        texture = graphicsManager.gameGraphics.ledge;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.createBody(world);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, (body.getPosition().x-LEDGE_WIDTH[widthIndex]/2)*METER_TO_PIXEL, (body.getPosition().y-LEDGE_HEIGHT/2)*METER_TO_PIXEL, 0, 0, (int)(LEDGE_WIDTH[widthIndex] / PIXEL_TO_METER), (int)(LEDGE_HEIGHT / PIXEL_TO_METER));
    }

    private void createBody(World world) {
        // Create the ledge body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        // Create the ledge body
        Body body = world.createBody(bodyDef);
        body.setTransform(LEDGE_INITIAL_POS_X[posXIndex]+LEDGE_WIDTH[widthIndex]/2, LEDGE_INITIAL_POS_Y[posYIndex]+LEDGE_HEIGHT/2, LEDGE_INITIAL_POS_Z); // Initial position

        // Create rectangular shape
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(LEDGE_WIDTH[widthIndex]/2, LEDGE_HEIGHT/2); // Dimensions (half width, half height)

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


        this.body = body;
        this.body.setLinearVelocity(0,LEDGE_INITIAL_VELOCITY);
    }

    public Vector2 getBodyPosition(){
        return this.body.getPosition();
    }

    public void delete(){
        world.destroyBody(this.body);
        this.remove();
        this.body.setUserData(null);
        this.body = null;
    }

    public boolean hasSpawnedAnother(){
        return this.spawnedAnother;
    }

    public void setSpawnedAnother(boolean state){
        this.spawnedAnother = state;
    }

}
