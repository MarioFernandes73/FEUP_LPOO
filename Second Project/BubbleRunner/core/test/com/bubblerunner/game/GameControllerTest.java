package com.bubblerunner.game;

import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.utils.Point;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 06/06/2017.
 */
public class GameControllerTest extends GameTest {

    @Test
    public void testGameControllerCreation() {
        assertNotNull(gameController);
        assertNotNull(gameController.getBallBody());
        assertNotNull(gameController.getLedgeBodies());
        assertTrue(gameController.getScoreUpdate() >= 0);
    }

    @Test
    public void testGameControllerCreation2() {
        assertEquals(gameController.getLedgeBodies().size(), gameModel.getLedgeModels().size());
        assertEquals(gameController.getBallBody().getHp(), gameController.getBallBody().getHp());
        Point<Float> ballBodyCoord = new Point<Float>(gameController.getBallBody().getX(), gameController.getBallBody().getY());
        Point<Float> ballModelCoord = new Point<Float>(gameModel.getBallModel().getX(), gameModel.getBallModel().getY());
        assertTrue(ballBodyCoord.equals(ballModelCoord));
    }

    @Test
    public void testGameControllerDeleteLedge() {
        GameController newController = new GameController(new GameModel(true), true);
        assertEquals(newController.getLedgeBodies().size(), 2);
        newController.update(0f);
        assertEquals(newController.getLedgeBodies().size(), 2);
    }

}