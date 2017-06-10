package com.bubblerunner.game.model;

import com.bubblerunner.game.model.entities.BallModel;
import com.bubblerunner.game.model.entities.LedgeModel;

import java.util.ArrayList;

import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY.NONLETHAL;

/**
 * A model representing a game.
 */
public class GameModel {

    /**
     * The ball model.
     */
    private final BallModel ballModel;

    /**
     * The ledge models.
     */
    private ArrayList<LedgeModel> ledgeModels;

    /**
     * The state of the game.
     */
    private GameModelState state;

    /**
     * Constructs a game model with a ball in the middle of the screen and
     * the initial ledges.
     *
     * @param state the state of this model which holds all final variables.
     */
    public GameModel(GameModelState state) {
        this.state = state;
        this.ballModel = new BallModel(state.ballStartingCoord);
        this.ledgeModels = new ArrayList<LedgeModel>();
        for (int i = 0; i < state.ledgeQuantity; i++) {
            this.ledgeModels.add(new LedgeModel(state.ledgeStartingCoord[i], state.ledgeSizes[i], state.ledgeWidths[i], state.ledgeHeight, NONLETHAL));
        }
    }

    /**
     * Returns the model of the ball.
     *
     * @return The model of the ball.
     */
    public BallModel getBallModel() {
        return this.ballModel;
    }

    /**
     * Returns the model of the ledges.
     *
     * @return The model of the ledges.
     */
    public ArrayList<LedgeModel> getLedgeModels() {
        return this.ledgeModels;
    }

    /**
     * Returns the fixed height of a ledge.
     *
     * @return The fixed height of a ledge.
     */
    public float getLedgeHeight() {
        return this.state.ledgeHeight;
    }


}
