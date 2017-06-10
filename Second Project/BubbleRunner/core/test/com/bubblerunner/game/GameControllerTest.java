package com.bubblerunner.game;

import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.controller.GameControllerState;
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.GameModelState;
import com.bubblerunner.game.utils.Constants;
import com.bubblerunner.game.utils.Point;

import org.junit.Test;

import static com.bubblerunner.game.utils.Constants.GAME_CREATION.TESTS;
import static com.bubblerunner.game.utils.Constants.VIEWPORT_WIDTH;
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

    @Test
    public void testGameControllerBallMaxY(){
        GameController newController = new GameController(new GameModel(new GameModelState(TESTS)), new GameControllerState(TESTS));
        //max height on this model is 8.
        newController.getBallBody().setTransform(5,5,0);    // ball Y = 5;
        assertEquals(newController.getGameState(), Constants.GAME_STATE.RUNNING);   //Game is running
        newController.checkBallHeight();
        assertEquals(newController.getGameState(), Constants.GAME_STATE.RUNNING);   //Game is still running
        newController.getBallBody().setTransform(5,10,0);    // ball Y = 10;
        newController.checkBallHeight();
        assertEquals(newController.getGameState(), Constants.GAME_STATE.OVER);   //Game is over.
    }

    @Test
    public void testGameControllerBallMinY(){
        GameController newController = new GameController(new GameModel(new GameModelState(TESTS)), new GameControllerState(TESTS));
        //min height on this model is 0.
        newController.getBallBody().setTransform(5,5,0);    // ball Y = 5;
        assertEquals(newController.getGameState(), Constants.GAME_STATE.RUNNING);   //Game is running
        newController.checkBallHeight();
        assertEquals(newController.getGameState(), Constants.GAME_STATE.RUNNING);   //Game is still running
        newController.getBallBody().setTransform(5,0,0);    // ball Y = 0;
        newController.checkBallHeight();
        assertEquals(newController.getGameState(), Constants.GAME_STATE.OVER);   //Game is over.
    }

    @Test
    public void testGameControllerBallX(){
        GameController newController = new GameController(new GameModel(new GameModelState(TESTS)), new GameControllerState(TESTS));
        //min x is 0 on this model and maximum is 4
        assertEquals(newController.getGameState(), Constants.GAME_STATE.RUNNING);   //Game is running
        newController.getBallBody().setTransform(2,5,0);    // ball X = 2;
        newController.checkWallCollision();
        assertEquals(newController.getBallBody().getX(),2,0);
        newController.getBallBody().setTransform(-1,5,0);    // ball X = -1;
        newController.checkWallCollision();
        assertEquals(newController.getBallBody().getX(),newController.getBallBody().getBounds().radius,0); //X is now 0 + ball radius
        newController.getBallBody().setTransform(10,5,0);    // ball X = 10;
        newController.checkWallCollision();
        assertEquals(newController.getBallBody().getX(),VIEWPORT_WIDTH - newController.getBallBody().getBounds().radius,0); //X is now VIEWPORT_WIDTH - ball radius
        assertEquals(newController.getGameState(), Constants.GAME_STATE.RUNNING);   //Game is still running
    }

    @Test
    public void testGameControllerCheckLedgeCollision(){
        GameController newController = new GameController(new GameModel(new GameModelState(TESTS)), new GameControllerState(TESTS));
        assertEquals(newController.getLedgeBodies().size(), 2);
        newController.getBallBody().setTransform(2,5+newController.getBallBody().getBounds().radius,0); //sets center of the ball's body
        newController.getLedgeBodies().get(1).getBody().setTransform(2,4,0);        //sets center of a ledge's body
        newController.getLedgeBodies().get(1).setLethality(Constants.LEDGE_LETHALITY.LETHAL);
        newController.checkLedgeCollision();
        assertEquals(newController.getBallBody().getHp(), 0);   //Ball hp is now 0
    }

    @Test
    public void testGameControllerUpdateLedgePosition(){
        GameController newController = new GameController(new GameModel(new GameModelState(TESTS)), new GameControllerState(TESTS));
        assertEquals(newController.getLedgeBodies().size(), 2);
        newController.updateLedgePosition();
        assertEquals(newController.getLedgeBodies().size(), 2); //ledges are unchanged
        newController.getLedgeBodies().get(1).getBody().setTransform(2,9,0);    //one ledge has reached the threshold position to generate a new ledge
        newController.updateLedgePosition();
        assertEquals(newController.getLedgeBodies().size(), 3); //new ledge has been created!
        newController.getLedgeBodies().get(1).getBody().setTransform(2,11,0);
        newController.updateLedgePosition();
        assertTrue(newController.getLedgeBodies().get(1).getNeedsDelete());     //ledge has passed the deletion threshold, needs delete
    }



}