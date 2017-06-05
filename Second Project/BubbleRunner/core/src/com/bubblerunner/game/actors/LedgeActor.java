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
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.model.entities.LedgeModel;
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
import static com.bubblerunner.game.constants.Constants.VIEWPORT_WIDTH;

/**
 * Created by Mario on 12/04/2017.
 */

public class LedgeActor extends Actor {

    protected LedgeBody body;
    protected final Texture texture;
    protected LedgeModel ledgeModel;
    private final Point<Float> startingCoordinates;
    private World world;


    public LedgeActor(Texture texture, World world, Point<Float> startingCoordinates, float width) {
        this.world = world;
        this.startingCoordinates = startingCoordinates;
        this.texture = texture;
        this.texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        if(width == VIEWPORT_WIDTH/2){
            this.ledgeModel = new LedgeModel(startingCoordinates.getX(), startingCoordinates.getY(), LedgeModel.LedgeSize.MEDIUM, width, LEDGE_HEIGHT );
        } else {
            this.ledgeModel = new LedgeModel(startingCoordinates.getX(), startingCoordinates.getY(), LedgeModel.LedgeSize.SMALL, width, LEDGE_HEIGHT);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, startingCoordinates.getX()*METER_TO_PIXEL, startingCoordinates.getY()*METER_TO_PIXEL, 0, 0, (int)(ledgeModel.getWidth() / PIXEL_TO_METER), (int)(ledgeModel.getHeight() / PIXEL_TO_METER));
    }

    protected void createBody() {
        this.body = new LedgeBody(world, ledgeModel);
    }

    public void setVelocity(float yVelocity){
        this.body.setLinearVelocity(0, yVelocity);
    }

    public Vector2 getBodyPosition(){
        return this.body.getPos();
    }

    public void delete(){
        world.destroyBody(this.body.getBody());
        this.remove();
        this.body.setUserData(null);
        this.body = null;
    }

    public boolean hasSpawnedAnother(){
        return this.ledgeModel.getSpawnedAnother();
    }

    public void setSpawnedAnother(boolean state){
        this.ledgeModel.setSpawnedAnother(state);
    }

    public int getLethality(){
        return this.ledgeModel.getLethality().getValue();
    }

    public void setLethality(Constants.LEDGE_LETHALITY lethality){
        this.ledgeModel.setLethality(lethality);
    }

    public Rectangle getBounds(){
        return new Rectangle(body.getX(), body.getY(), this.ledgeModel.getWidth(), this.ledgeModel.getHeight());
    }

}
