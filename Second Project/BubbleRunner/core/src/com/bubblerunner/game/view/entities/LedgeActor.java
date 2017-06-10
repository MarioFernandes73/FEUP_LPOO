package com.bubblerunner.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.controller.entities.LedgeBody;

import static com.bubblerunner.game.utils.Constants.METER_TO_PIXEL;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;

/**
 * Ledge view extending LIBGdx Actor class.
 */
public class LedgeActor extends Actor {

    /**
     * Ledge's body which textures are mapped to.
     */
    private LedgeBody body;

    /**
     * Ledge's texture
     */
    private final Texture texture;

    /**
     * Constructs a new ledge actor.
     *
     * @param texture the fixed texture the ledge will have (spiekd lege or normal ledge)
     * @param body the ledge's body.
     */
    public LedgeActor(Texture texture, LedgeBody body) {
        this.texture = texture;
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        this.body = body;
    }

    /**
     * Override of draw method.
     *
     * @param batch the batch to draw in.
     * @param parentAlpha the paren'ts alpha value
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, (body.getX()- body.getWidth()/2)*METER_TO_PIXEL, (body.getY()-body.getHeight()/2)*METER_TO_PIXEL, 0, 0, (int)(body.getWidth() / PIXEL_TO_METER), (int)(body.getHeight() / PIXEL_TO_METER));
    }

    /**
     * Wrapper that sets the hasActor flag for this ledge.
     *
     * @param value hasActor value
     */
    public void setHasActor(boolean value){this.body.setHasActor(value); }

    /**
     * Wrapper that gets the needsDelete flag for this ledge.
     *
     * @return  the needsDelete value.
     */
    public boolean needsDelete(){return this.body.getNeedsDelete();}

    /**
     * Wrapper that sets the canDeleter flag for this ledge.
     *
     * @param value canDelete value
     */
    public void setCanDelete(boolean value){this.body.setCanDelete(value);}
}
