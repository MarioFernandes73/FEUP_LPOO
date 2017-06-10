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
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.LEDGE_MAX_HEIGHT_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_TYPE_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_TYPE_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH_POSSIBILITIES;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH_POSSIBILITIES_TEST;
import static com.bubblerunner.game.utils.Constants.RATIO;
import static com.bubblerunner.game.utils.Constants.VIEWPORT_WIDTH;

/**
 * The state of the game's controller.
 * This class defines all the game's heights, positions and other variables
 * which are only going to be defined once.
 * These variables act as either triggers for events or are directly used
 * to create new objects.
 */
public class GameControllerState {

    /**
     * The maximum height the ball can reach.
     */
    final float ballMaxHeight;

    /**
     * The minimum height the ball can reach.
     */
    final float ballMinHeight;

    /**
     * The maximum height a ledge can reach.
     */
    final float ledgeMaxHeight;

    /**
     * The height at which the game will be signaled to create a new random ledge.
     */
    final float ledgeGenHeight;

    /**
     * The type possibilities a random ledge can have.
     */
    final int ledgeCreationTypePossibilities;

    /**
     * The width possibilities a random ledge can have.
     */
    final int ledgeCreationWidthPossibilities;

    /**
     * The initial position possibilities a small ledge can have
     */
    final int ledgeSmallPositionPossibilities;

    /**
     * The initial position possibilities a medium ledge can have
     */
    final int ledgeMediumPositionPossibilities;

    /**
     * The fixed index used to select the starting position of a newly
     * generated ledge.
     */
    final int ledgeHeightGenIndex;

    /**
     * The initial horizontal positions a small ledge can have
     */
    final float[] ledgeSmallInitialXPos;

    /**
     * The initial horizontal positions a medium ledge can have
     */
    final float[] ledgeMediumInitialXPos;

    /**
     * The initial vertical positions a ledge can have.
     */
    final float[] ledgeInitialYPos;

    /**
     * Creates the game controller state.
     * Tests use fixed value instead of screen related values.
     *
     * @param creationMode to indicate the mode which the game will be initialized upon.
     */
    public GameControllerState(Constants.GAME_CREATION creationMode) {
        if (creationMode == RELEASE || creationMode == DEBUG) {
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
            ledgeSmallInitialXPos = new float[]{0f};
            ledgeMediumInitialXPos = new float[]{0f};
            ledgeHeightGenIndex = LEDGE_HEIGHT_GENERATOR_INDEX_TEST;
            ledgeInitialYPos = new float[]{0f};
        }
    }
}
