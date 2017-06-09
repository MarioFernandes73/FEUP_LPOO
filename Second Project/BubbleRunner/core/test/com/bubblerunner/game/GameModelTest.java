package com.bubblerunner.game;

import com.bubblerunner.game.model.entities.EntityModel;
import com.bubblerunner.game.model.entities.LedgeModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 06/06/2017.
 */
public class GameModelTest extends GameTest {

    @Test
    public void testGameModelCreation() {
        assertNotNull(gameModel.getBallModel());
        assertEquals(gameModel.getLedgeModels().size(), 2);
    }

    @Test
    public void testGameModelBall() {
        assertTrue(gameModel.getBallModel().getHp() > 0);
        assertTrue(gameModel.getBallModel().isFalling());
        assertTrue(gameModel.getBallModel().getX() == gameModel.getBallModel().getY() || gameModel.getBallModel().getX() != gameModel.getBallModel().getY());
    }

    @Test
    public void testGameModelLedges() {
        LedgeModel ledge1 = gameModel.getLedgeModels().get(0);
        LedgeModel ledge2 = gameModel.getLedgeModels().get(1);
        assertTrue(ledge1.getHeight() == ledge2.getHeight());
        assertTrue(!ledge1.getSpawnedAnother() && !ledge2.getSpawnedAnother());
        assertTrue(ledge1.getType() == EntityModel.ModelType.MEDIUMLEDGE && ledge2.getType() == EntityModel.ModelType.SMALLLEDGE);
    }


}