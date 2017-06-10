package com.bubblerunner.game;

import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.controller.GameControllerState;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.GameModelState;
import com.bubblerunner.game.utils.Point;

import org.junit.Test;

import static com.bubblerunner.game.utils.Constants.GAME_CREATION.TESTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        GameController newController = new GameController(new GameModel(new GameModelState(TESTS)), new GameControllerState(TESTS));
        assertEquals(newController.getLedgeBodies().size(), 2);         //size 2
        newController.deleteLedge();                                    //asks to delete (without marks)
        assertEquals(newController.getLedgeBodies().size(), 2);         //still size 2
        newController.getLedgeBodies().get(0).setNeedsDelete(true);     //marks for view to remove actor associated
        newController.getLedgeBodies().get(0).setCanDelete(true);       //marks has actor being deleted
        newController.deleteLedge();                                    //tries to delete
        assertEquals(newController.getLedgeBodies().size(), 1);         //size now 1.
    }

}