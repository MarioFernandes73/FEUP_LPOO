package com.bubblerunner.game;

import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.model.GameModel;

import org.junit.BeforeClass;

import static org.junit.Assert.*;

/**
 * Created by Mario on 06/06/2017.
 */
public class GameTest {

    protected static GameModel gameModel;
    protected static GameController gameController;

    @BeforeClass
    public static void init() {

        gameModel = new GameModel(true);
        gameController = new GameController(gameModel, true);

    }


    /* TESTS MOCKUP

        10---------     //Maximum height

        8----------     //Height a ledge will reach to generate another

     */

}