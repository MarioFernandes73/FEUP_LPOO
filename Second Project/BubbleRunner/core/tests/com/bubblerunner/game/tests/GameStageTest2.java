package com.bubblerunner.game.tests;

import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.stages.GameStage;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Mario on 04/06/2017.
 */
public class GameStageTest2 {
    @Before
    public void setUp() throws Exception {
        GameStage stage = new GameStage(new BubbleRunner());
    }

    @After
    public void tearDown() throws Exception {

    }

}