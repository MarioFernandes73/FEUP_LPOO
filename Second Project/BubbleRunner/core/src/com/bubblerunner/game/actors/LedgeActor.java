package com.bubblerunner.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.utils.Point;

import static com.bubblerunner.game.constants.Constants.LEDGE_DENSITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_FRICTION;
import static com.bubblerunner.game.constants.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Z;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.constants.Constants.LEDGE_RESTITUTION;
import static com.bubblerunner.game.constants.Constants.METER_TO_PIXEL;
import static com.bubblerunner.game.constants.Constants.PIXEL_TO_METER;

/**
 * Created by Mario on 12/04/2017.
 */

public class LedgeActor extends Actor {

    protected Body body;
    protected final Texture texture;
    protected final float width;
    protected final float height;
    private final Point<Float> startingCoordinates;
    private World world;
    private boolean spawnedAnother = false;
    private LEDGE_LETHALITY lethality;

    public LedgeActor(Texture texture, World world, Point<Float> startingCoordinates, float width) {
        this.world = world;
        this.startingCoordinates = startingCoordinates;
        this.width = width;
        this.height = LEDGE_HEIGHT;
        this.lethality = NONLETHAL;
        this.texture = texture;
        this.texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, startingCoordinates.getX()*METER_TO_PIXEL, startingCoordinates.getY()*METER_TO_PIXEL, 0, 0, (int)(width / PIXEL_TO_METER), (int)(height / PIXEL_TO_METER));
    }

    private void createBodyFixture(Body body){
        // Create rectangular shape
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width/2, height/2); // Dimensions (half width, half height)

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

    protected void createBody() {
        // Create the ledge body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        // Create the ledge body
        Body body = world.createBody(bodyDef);
        body.setTransform(startingCoordinates.getX()+width/2, startingCoordinates.getY()+height/2, LEDGE_INITIAL_POS_Z); // Initial position

        this.createBodyFixture(body);

        this.body = body;
        this.body.setLinearVelocity(0,LEDGE_INITIAL_VELOCITY);
    }

    public void setVelocity(float yVelocity){
        this.body.setLinearVelocity(0, yVelocity);
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

    public int getLethality(){
        return this.lethality.getValue();
    }

    public void setLethality(Constants.LEDGE_LETHALITY lethality){
        this.lethality = lethality;
    }

    public Rectangle getBounds(){
        return new Rectangle(body.getPosition().x, body.getPosition().y, this.width, this.height);
    }

}
