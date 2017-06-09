package com.bubblerunner.game.controller;

import com.bubblerunner.game.utils.Constants;

import static com.bubblerunner.game.utils.Constants.BALL_MAX_HEIGHT;
import static com.bubblerunner.game.utils.Constants.BALL_MAX_HEIGHT_TEST;
import static com.bubblerunner.game.utils.Constants.BALL_MIN_HEIGHT;
import static com.bubblerunner.game.utils.Constants.BALL_MIN_HEIGHT_TEST;
import static com.bubblerunner.game.utils.Constants.GAME_CREATION.DEBUG;
import static com.bubblerunner.game.utils.Constants.GAME_CREATION.RELEASE;
import static com.bubblerunner.game.utils.Constants.LEDGE_GEN_HEIGHT;
import static com.bubblerunner.game.utils.Constants.LEDGE_GEN_HEIGHT_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT_GENERATOR_INDEX;
import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT_GENERATOR_INDEX_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_MEDIUM;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_MEDIUM_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_MAX_HEIGHT_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_TYPE_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_TYPE_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.RATIO;
import static com.bubblerunner.game.utils.Constants.VIEWPORT_WIDTH;

/**
 * Created by Mario on 09/06/2017.
 */

public class GameControllerState {

    public final float ballMaxHeight;
    public final float ballMinHeight;
    public final float ledgeMaxHeight;
    public final float ledgeGenHeight;
    public final int ledgeCreationTypePossibilities;
    public final int ledgeCreationWidthPossibilities;
    public final int ledgeSmallPositionPossibilities;
    public final int ledgeMediumPositionPossibilities;
    public final int ledgeHeightGenIndex;
    public final float[] ledgeSmallInitialXPos;
    public final float[] ledgeMediumInitialXPos;
    public final float[] ledgeInitialYPos;


    public GameControllerState(Constants.GAME_CREATION creationMode){
        if(creationMode == RELEASE || creationMode == DEBUG){
            ballMaxHeight = BALL_MAX_HEIGHT;
            ballMinHeight = BALL_MIN_HEIGHT;
            ledgeMaxHeight = VIEWPORT_WIDTH * RATIO;
            ledgeGenHeight = LEDGE_GEN_HEIGHT;
            ledgeCreationTypePossibilities = LEDGE_TYPE_POSSIBILITIES;
            ledgeCreationWidthPossibilities = LEDGE_WIDTH_POSSIBILITIES;
            ledgeSmallPositionPossibilities = LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES;
            ledgeMediumPositionPossibilities = LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES;
            ledgeSmallInitialXPos = LEDGE_INITIAL_POS_X_SMALL;
            ledgeMediumInitialXPos = LEDGE_INITIAL_POS_X_MEDIUM;
            ledgeHeightGenIndex = LEDGE_HEIGHT_GENERATOR_INDEX;
            ledgeInitialYPos = LEDGE_INITIAL_POS_Y;
        } else {
            ballMaxHeight = BALL_MAX_HEIGHT_TEST;
            ballMinHeight = BALL_MIN_HEIGHT_TEST;
            ledgeMaxHeight = LEDGE_MAX_HEIGHT_TEST;
            ledgeGenHeight = LEDGE_GEN_HEIGHT_TEST;
            ledgeCreationTypePossibilities = LEDGE_TYPE_POSSIBILITIES_TEST;
            ledgeCreationWidthPossibilities = LEDGE_WIDTH_POSSIBILITIES_TEST;
            ledgeSmallPositionPossibilities = LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES_TEST;
            ledgeMediumPositionPossibilities = LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES_TEST;
            ledgeSmallInitialXPos = LEDGE_INITIAL_POS_X_SMALL_TEST;
            ledgeMediumInitialXPos = LEDGE_INITIAL_POS_X_MEDIUM_TEST;
            ledgeHeightGenIndex = LEDGE_HEIGHT_GENERATOR_INDEX_TEST;
            ledgeInitialYPos = LEDGE_INITIAL_POS_Y_TEST;
        }
    }
}
