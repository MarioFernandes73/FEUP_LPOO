package com.bubblerunner.game.model;

import com.bubblerunner.game.model.entities.BallModel;
import com.bubblerunner.game.model.entities.LedgeModel;

import java.util.ArrayList;

import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY.NONLETHAL;

/**
 * Created by Mario on 04/06/2017.
 */

public class GameModel {

    private final BallModel ballModel;
    private ArrayList<LedgeModel> ledgeModels;
    private GameModelState state;

    public GameModel (GameModelState state){
        this.state = state;
            this.ballModel = new BallModel(state.ballStartingCoord);
            this.ledgeModels = new ArrayList<LedgeModel>();
            for(int i = 0; i < state.ledgeQuantity; i++){
                this.ledgeModels.add(new LedgeModel(state.ledgeStartingCoord[i],state.ledgeSizes[i],state.ledgeWidths[i],state.ledgeHeight,NONLETHAL));
            }
 }

    public BallModel getBallModel(){
        return this.ballModel;
    }

    public ArrayList<LedgeModel> getLedgeModels(){
        return this.ledgeModels;
    }

    public float getLedgeHeight(){return this.state.ledgeHeight;}


}
