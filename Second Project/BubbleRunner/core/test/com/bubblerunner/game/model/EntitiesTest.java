package com.bubblerunner.game.model;

import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.model.entities.BallModel;
import com.bubblerunner.game.model.entities.LedgeModel;
import com.bubblerunner.game.utils.Point;

import org.junit.Test;

import static com.bubblerunner.game.constants.Constants.STARTING_HP;
import static org.junit.Assert.*;

/**
 * Created by Mario on 05/06/2017.
 */
public class EntitiesTest {

    @Test
    public void testBallModelCreation() {
        BallModel ballModel = new BallModel(new Point<Float>(0f,0f));
        assertEquals(ballModel.getX(),0,0);
        assertEquals(ballModel.getY(),0,0);
        assertEquals(ballModel.getHp(),STARTING_HP);
        assertEquals(ballModel.isFalling(),true);
    }

    @Test
    public void testGenericLedgeModelCreation() {
        LedgeModel genericLedgeModel = new LedgeModel(new Point<Float>(0f,0f), LedgeModel.LedgeSize.MEDIUM,5,5, Constants.LEDGE_LETHALITY.NONLETHAL);
        assertEquals(genericLedgeModel.getX(),0,0);
        assertEquals(genericLedgeModel.getY(),0,0);
    }

}