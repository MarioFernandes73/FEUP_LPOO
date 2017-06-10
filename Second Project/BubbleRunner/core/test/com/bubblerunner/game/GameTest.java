package com.bubblerunner.game;

import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.controller.GameControllerState;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.GameModelState;

import org.junit.BeforeClass;

import static com.bubblerunner.game.utils.Constants.GAME_CREATION.TESTS;

/**
 * Created by Mario on 06/06/2017.
 */
public class GameTest {

    protected static GameModel gameModel;
    protected static GameController gameController;

    @BeforeClass
    public static void init() {
        gameModel = new GameModel(new GameModelState(TESTS));
        gameController = new GameController(gameModel, new GameControllerState(TESTS));
    }

}