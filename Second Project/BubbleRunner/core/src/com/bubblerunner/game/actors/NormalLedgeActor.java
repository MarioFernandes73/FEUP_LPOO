package com.bubblerunner.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.utils.Point;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.constants.Constants.METER_TO_PIXEL;
import static com.bubblerunner.game.constants.Constants.PIXEL_TO_METER;

/**
 * Created by Mario on 31/05/2017.
 */

public class NormalLedgeActor extends LedgeActor {

    public NormalLedgeActor(GraphicsManager graphicsManager, World world, Point<Float> startingCoordinates, float width){
        super(graphicsManager.gameGraphics.ledge, world, startingCoordinates, width);
        this.setLethality(NONLETHAL);
        this.createBody();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, (body.getX()- ledgeModel.getWidth()/2)*METER_TO_PIXEL, (body.getY()-ledgeModel.getHeight()/2)*METER_TO_PIXEL, 0, 0, (int)(ledgeModel.getWidth() / PIXEL_TO_METER), (int)(ledgeModel.getHeight() / PIXEL_TO_METER));
    }

}
