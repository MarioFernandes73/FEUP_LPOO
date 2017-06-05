package com.bubblerunner.game.model;

import com.bubblerunner.game.model.entities.BallModel;
import com.bubblerunner.game.model.entities.LedgeModel;
import com.bubblerunner.game.utils.Point;

import java.util.ArrayList;

import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_POS_X;
import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_INDEX_LEFT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_INDEX_MLEFT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_INDEX_MRIGHT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_SMALL;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_BOTTOM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_MUP;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.SMALL;

/**
 * Created by Mario on 04/06/2017.
 */

public class GameModel {

    private final BallModel ballModel;
    private ArrayList<LedgeModel> ledgeModels;

    public GameModel (){
        this.ballModel = new BallModel(new Point<Float>(BALL_INITIAL_POS_X, BALL_INITIAL_POS_Y));
        this.ledgeModels = new ArrayList<LedgeModel>();
        this.ledgeModels.add(new LedgeModel(new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[LEDGE_INITIAL_POS_X_INDEX_MRIGHT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_MUP]), LedgeModel.LedgeSize.MEDIUM,LEDGE_WIDTH[MEDIUM.getValue()], LEDGE_HEIGHT, NONLETHAL));
        this.ledgeModels.add(new LedgeModel(new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[LEDGE_INITIAL_POS_X_INDEX_MLEFT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM]), LedgeModel.LedgeSize.MEDIUM,LEDGE_WIDTH[MEDIUM.getValue()], LEDGE_HEIGHT, NONLETHAL));
        this.ledgeModels.add(new LedgeModel(new Point<Float>(LEDGE_INITIAL_POS_X_MEDIUM[LEDGE_INITIAL_POS_X_INDEX_LEFT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_BOTTOM]), LedgeModel.LedgeSize.SMALL,LEDGE_WIDTH[SMALL.getValue()], LEDGE_HEIGHT, NONLETHAL));
    }

    public BallModel getBallModel(){
        return this.ballModel;
    }

    public ArrayList<LedgeModel> getLedgeModels(){
        return this.ledgeModels;
    }


}
