package com.bubblerunner.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubblerunner.game.controller.entities.LedgeBody;

import static com.bubblerunner.game.utils.Constants.METER_TO_PIXEL;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;

/**
 * Created by Mario on 12/04/2017.
 */

public class LedgeActor extends Actor {

    private LedgeBody body;
    private final Texture texture;

    public LedgeActor(Texture texture, LedgeBody body) {
        this.texture = texture;
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        this.body = body;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, (body.getX()- body.getWidth()/2)*METER_TO_PIXEL, (body.getY()-body.getHeight()/2)*METER_TO_PIXEL, 0, 0, (int)(body.getWidth() / PIXEL_TO_METER), (int)(body.getHeight() / PIXEL_TO_METER));
    }

    public void setHasActor(boolean value){this.body.setHasActor(value); }

    public boolean needsDelete(){return this.body.getNeedsDelete();}

    public void setCanDelete(boolean value){this.body.setCanDelete(value);}
}
