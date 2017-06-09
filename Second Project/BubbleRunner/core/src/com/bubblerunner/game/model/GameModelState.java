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
 * Created by Mario on 09/06/2017.
 */

public class GameModelState {

    public final Point<Float> ballStartingCoord;
    public final int ledgeQuantity;
    public final Point<Float>[] ledgeStartingCoord;
    public final LedgeModel.LedgeSize[] ledgeSizes;
    public final float[] ledgeWidths;
    public final float ledgeHeight;

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
