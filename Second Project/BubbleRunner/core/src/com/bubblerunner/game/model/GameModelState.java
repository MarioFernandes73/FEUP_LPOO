package com.bubblerunner.game.model;

import com.bubblerunner.game.model.entities.LedgeModel;
import com.bubblerunner.game.utils.Constants.GAME_CREATION;
import com.bubblerunner.game.utils.Point;

import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_X;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_X_TEST;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.BALL_INITIAL_POS_Y_TEST;
import static com.bubblerunner.game.utils.Constants.GAME_CREATION.DEBUG;
import static com.bubblerunner.game.utils.Constants.GAME_CREATION.RELEASE;
import static com.bubblerunner.game.utils.Constants.LEDGE_1_INITIAL_POS_X_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_1_INITIAL_POS_Y_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_1_INITIAL_WIDTH_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_2_INITIAL_POS_X_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_2_INITIAL_POS_Y_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_2_INITIAL_WIDTH_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_INDEX_LEFT;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_INDEX_MLEFT;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_INDEX_MRIGHT;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_MEDIUM;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_X_SMALL;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y_INDEX_BOTTOM;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y_INDEX_MUP;
import static com.bubblerunner.game.utils.Constants.LEDGE_STARTING_QUANTITY;
import static com.bubblerunner.game.utils.Constants.LEDGE_STARTING_QUANTITY_TEST;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH.MEDIUM;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH.SMALL;

/**
 * The state of the game's model.
 * This class defines all the game's starting coordinates, quantities and sizes.
 * These variables act as either triggers for events or are directly used
 * to create new objects.
 */
public class GameModelState {

    /**
     * The ball's starting coordinates
     */
    final Point<Float> ballStartingCoord;

    /**
     * The initial ledge's quantity
     */
    final int ledgeQuantity;

    /**
     * The ledge's starting coordinates.
     */
    final Point<Float>[] ledgeStartingCoord;

    /**
     * The ledge's starting sizes.
     */
    final LedgeModel.LedgeSize[] ledgeSizes;

    /**
     * The ledge's starting widths.
     */
    final float[] ledgeWidths;

    /**
     * The ledge's starting height.
     */
    final float ledgeHeight;

    /**
     * Creates the game model state.
     * Tests use fixed value instead of screen related values.
     *
     * @param creationMode to indicate the mode which the game will be initialized upon.
     */
    public GameModelState(GAME_CREATION creationMode){
        if(creationMode == RELEASE || creationMode == DEBUG){
            ballStartingCoord = new Point<Float>(BALL_INITIAL_POS_X, BALL_INITIAL_POS_Y);
            ledgeQuantity = LEDGE_STARTING_QUANTITY;
            ledgeStartingCoord = new Point[]{
                    new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[LEDGE_INITIAL_POS_X_INDEX_MRIGHT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_MUP]),
                    new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[LEDGE_INITIAL_POS_X_INDEX_MLEFT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM]),
                    new Point<Float>(LEDGE_INITIAL_POS_X_MEDIUM[LEDGE_INITIAL_POS_X_INDEX_LEFT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_BOTTOM])};
            ledgeSizes = new LedgeModel.LedgeSize[]{LedgeModel.LedgeSize.MEDIUM, LedgeModel.LedgeSize.MEDIUM, LedgeModel.LedgeSize.SMALL};
            ledgeWidths = new float[]{LEDGE_WIDTH[MEDIUM.getValue()],LEDGE_WIDTH[MEDIUM.getValue()],LEDGE_WIDTH[SMALL.getValue()]};
            ledgeHeight = LEDGE_HEIGHT;
        }
        else{
            ballStartingCoord = new Point<Float>(BALL_INITIAL_POS_X_TEST,BALL_INITIAL_POS_Y_TEST);
            ledgeQuantity = LEDGE_STARTING_QUANTITY_TEST;
            ledgeStartingCoord = new Point[]{
                    new Point<Float>(LEDGE_1_INITIAL_POS_X_TEST,LEDGE_1_INITIAL_POS_Y_TEST),
                    new Point<Float>(LEDGE_2_INITIAL_POS_X_TEST,LEDGE_2_INITIAL_POS_Y_TEST)};
            ledgeSizes = new LedgeModel.LedgeSize[]{LedgeModel.LedgeSize.MEDIUM, LedgeModel.LedgeSize.SMALL};
            ledgeWidths = new float[]{LEDGE_1_INITIAL_WIDTH_TEST,LEDGE_2_INITIAL_WIDTH_TEST};
            ledgeHeight = LEDGE_HEIGHT_TEST;
        }
    }
}
