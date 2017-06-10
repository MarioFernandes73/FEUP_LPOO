package com.bubblerunner.game.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.utils.Constants;
import com.bubblerunner.game.utils.Constants.GAME_STATE;
import com.bubblerunner.game.controller.entities.BallBody;
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.entities.LedgeModel;
import com.bubblerunner.game.utils.Point;

import java.util.ArrayList;
import java.util.Random;

import static com.bubblerunner.game.utils.Constants.GAME_STATE.OVER;
import static com.bubblerunner.game.utils.Constants.GAME_STATE.RUNNING;
import static com.bubblerunner.game.utils.Constants.LEDGE_INCREMENT_VELOCITY;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.utils.Constants.LEDGE_SIZES;
import static com.bubblerunner.game.utils.Constants.LEDGE_TYPES;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH.MEDIUM;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH.SMALL;
import static com.bubblerunner.game.utils.Constants.WORLD_GRAVITY_X;
import static com.bubblerunner.game.utils.Constants.WORLD_GRAVITY_Y;
import static com.bubblerunner.game.utils.Constants.WORLD_POSITION_ITERATIONS;
import static com.bubblerunner.game.utils.Constants.WORLD_VELOCITY_ITERATIONS;

/**
 * Controls the entire logic of the game, separated from the view component.
 */
public class GameController {

    /**
     * Value to update the game's current score.
     */
    private int scoreUpdate;

    /**
     * State of the game
     */
    private GAME_STATE gameState;

    /**
     * All ledge's current velocity.
     */
    private float ledgesCurrentVelocity;

    /**
     * Model of the current game.
     */
    private GameModel gameModel;

    /**
     * World of the current game.
     */
    private World world;

    /**
     * Body of the ball.
     */
    private BallBody ballBody;

    /**
     * All ledges currently in the game.
     */
    private ArrayList<LedgeBody> ledgeBodies;

    /**
     * Flag indicating that some new actors need to be created (used by view).
     */
    private boolean refreshActors;

    /**
     * Flag indicating that some bodies need to be deleted.
     */
    private boolean pendingDeletion;

    /**
     * Flag indicating that some actors need to be deleted (used by view).
     */
    private boolean deleteLedgeActors;

    /**
     * The state used for this controller.
     */
    private final GameControllerState state;


    /**
     * Creates and initializes the physics of this game.
     *
     * @param gameModel the starting model which this game will use.
     * @param state     all the variables representing the state of this controller.
     */
    public GameController(GameModel gameModel, GameControllerState state) {
        this.state = state;
        this.deleteLedgeActors = false;
        this.pendingDeletion = false;
        this.gameModel = gameModel;
        this.gameState = RUNNING;
        this.ledgesCurrentVelocity = LEDGE_INITIAL_VELOCITY;
        this.scoreUpdate = 0;
        this.world = new World(new Vector2(WORLD_GRAVITY_X, WORLD_GRAVITY_Y), true);
        this.ballBody = new BallBody(world, gameModel.getBallModel());
        this.ledgeBodies = new ArrayList<LedgeBody>();
        for (int i = 0; i < this.gameModel.getLedgeModels().size(); i++) {
            ledgeBodies.add(new LedgeBody(world, this.gameModel.getLedgeModels().get(i)));
        }

    }

    /**
     * The update cycle of the game.
     *
     * @param delta the world variation which the world will have (0 to a "time frozen" world).
     */
    public void update(float delta) {
        // Step the world
        world.step(delta, WORLD_VELOCITY_ITERATIONS, WORLD_POSITION_ITERATIONS);

        checkWallCollision();
        checkBallHeight();
        updateLedgePosition();
        checkLedgeCollision();

        if (pendingDeletion) {
            deleteLedge();
        }

        if (gameModel.getBallModel().getHp() == 0) {
            gameState = OVER;
        }
    }

    /**
     * Checks the ball's collision with the side walls
     * and if the ball would cross it, moves it back.
     */
    public void checkWallCollision() {
        if (this.ballBody.getBounds().x - this.ballBody.getBounds().radius <= 0) {
            this.ballBody.setTransform(this.ballBody.getBounds().radius, this.ballBody.getY(), 0);
        } else if (this.ballBody.getBounds().x + this.ballBody.getBounds().radius >= Constants.VIEWPORT_WIDTH) {
            this.ballBody.setTransform(Constants.VIEWPORT_WIDTH - this.ballBody.getBounds().radius, this.ballBody.getY(), 0);
        }
    }

    /**
     * Checks the ball's height: if it's either too high or too low, changes the game state to over.
     */
    public void checkBallHeight() {
        if (ballBody.getY() >= state.ballMaxHeight || ballBody.getY() <= state.ballMinHeight) {
            this.gameState = OVER;
        }
    }


    /**
     * Updates the game based on a generic ledge's position.
     * If a ledge reaches the end of the screen, the actor representing can be removed
     * and the body can be deleted.
     * If a ledge reaches 3/4's of the screen, a new ledge will spawn.
     */
    public void updateLedgePosition() {
        for (int i = 0; i < this.ledgeBodies.size(); i++) {
            LedgeBody currentLedge = this.ledgeBodies.get(i);
            if (currentLedge.getY() > state.ledgeMaxHeight) {        // ledge has reached end of screen
                currentLedge.setNeedsDelete(true);
                this.pendingDeletion = true;
                this.deleteLedgeActors = true;
            } else if (currentLedge.getY() > state.ledgeGenHeight && !currentLedge.hasSpawnedAnother()) {     // ledge has reached 3/4 of the screen (create a new one)
                LedgeBody newLedge = createRandomLedge();
                this.ledgeBodies.add(newLedge);
                currentLedge.setSpawnedAnother(true);
                this.refreshActors = true;
                this.ledgesCurrentVelocity += LEDGE_INCREMENT_VELOCITY;
                updateLedgesVelocity();
            }
        }
    }

    /**
     * Creates a random ledge which can either:
     * Be LETHAL or NONLETHAL
     * Be of SMALL or MEDIUM size
     * Have a certain initial position depending on its size.
     */
    public LedgeBody createRandomLedge() {
        Random generator = new Random();

        int type = generator.nextInt(state.ledgeCreationTypePossibilities);
        int width = generator.nextInt(state.ledgeCreationWidthPossibilities);
        LedgeModel.LedgeSize size = null;
        Point<Float> startingCoordinates = null;


        if (LEDGE_SIZES[width] == SMALL) {
            float xSmallCoord = state.ledgeSmallInitialXPos[generator.nextInt(state.ledgeSmallPositionPossibilities)];
            float ySmallCoord = state.ledgeInitialYPos[state.ledgeHeightGenIndex];
            startingCoordinates = new Point<Float>(xSmallCoord, ySmallCoord);
            size = LedgeModel.LedgeSize.SMALL;
        } else if (LEDGE_SIZES[width] == MEDIUM) {
            float xMediumCoord = state.ledgeMediumInitialXPos[generator.nextInt(state.ledgeMediumPositionPossibilities)];
            float yMediumCoord = state.ledgeInitialYPos[state.ledgeHeightGenIndex];
            startingCoordinates = new Point<Float>(xMediumCoord, yMediumCoord);
            size = LedgeModel.LedgeSize.MEDIUM;
        }

        return new LedgeBody(world, new LedgeModel(startingCoordinates, size, LEDGE_WIDTH[width], gameModel.getLedgeHeight(), LEDGE_TYPES[type]));
    }

    /**
     * Updates all of the ledge's velocity based on ledgesCurrentVelocity.
     */
    public void updateLedgesVelocity() {
        for (int i = 0; i < this.ledgeBodies.size(); i++) {
            this.ledgeBodies.get(i).setVelocity(this.ledgesCurrentVelocity);
        }
    }

    /**
     * Deletes all ledges which can be deleted.
     */
    public void deleteLedge() {
        for (int i = 0; i < this.ledgeBodies.size(); i++) {
            LedgeBody currentLedge = this.ledgeBodies.get(i);
            if (currentLedge.getCanDelete()) {
                world.destroyBody(currentLedge.getBody());
                currentLedge.delete();
                this.ledgeBodies.remove(i);
                this.scoreUpdate = 1;
                this.pendingDeletion = false;
                break;
            }
        }
    }

    /**
     * Checks ledge's collisions with the ball based on each body's bounds.
     * If the ball touches a LETHAL ledge, it has its HP decreased by 1.
     */
    public void checkLedgeCollision() {
        for (int i = 0; i < this.ledgeBodies.size(); i++) {
            Rectangle currentLedgeBody = this.ledgeBodies.get(i).getBounds();
            float ledgeContactMaxX = currentLedgeBody.x + currentLedgeBody.width / 2;
            float ledgeContactMinX = currentLedgeBody.x - currentLedgeBody.width / 2;
            float ballContactMaxX = ballBody.getBounds().x + ballBody.getBounds().radius;
            float ballContactMinX = ballBody.getBounds().x - ballBody.getBounds().radius;
            float ledgeContactY = currentLedgeBody.y + currentLedgeBody.height / 2;
            float ballContactY = ballBody.getBounds().y - ballBody.getBounds().radius;
            if (ballContactY >= ledgeContactY && ballContactY <= ledgeContactY + 0.01 && ballContactMaxX >= ledgeContactMinX && ballContactMinX <= ledgeContactMaxX) {
                ballBody.setHp(ballBody.getHp() - this.ledgeBodies.get(i).getLethality().getValue());
            }
        }
    }

    /**
     * Returns the deleteLedgeActors flag.
     *
     * @return the deleteLedgeActors flag.
     */
    public boolean getDeleteLedgeActors() {
        return this.deleteLedgeActors;
    }

    /**
     * Sets the deleteLedgeActors flag.
     *
     * @param value of the deleteLedgeActors flag.
     */
    public void setDeleteLedgeActors(boolean value) {
        this.deleteLedgeActors = value;
    }

    /**
     * Returns the state of the game.
     *
     * @return the gameState.
     */
    public GAME_STATE getGameState() {
        return this.gameState;
    }

    /**
     * Returns the scoreUpdate value.
     *
     * @return the scoreUpdate.
     */
    public int getScoreUpdate() {
        return this.scoreUpdate;
    }


    /**
     * Sets the scoreUpdate value
     *
     * @param score the value of the scoreUpdate.
     */
    public void setScoreUpdate(int score) {
        this.scoreUpdate = score;
    }

    /**
     * Returns ball's body.
     *
     * @return the ball's body.
     */
    public BallBody getBallBody() {
        return this.ballBody;
    }

    /**
     * Returns all the ledges bodies.
     *
     * @return the list with the ledges bodies.
     */
    public ArrayList<LedgeBody> getLedgeBodies() {
        return this.ledgeBodies;
    }

    /**
     * Returns the refreshLedgeActors flag.
     *
     * @return the refreshLedgeActors flag
     */
    public boolean getRefreshLedgeActors() {
        return this.refreshActors;
    }

    /**
     * Sets the refreshLedgeActors flag.
     *
     * @param value the value of the refreshLedgeActors flag.
     */
    public void setRefreshLedgeActors(boolean value) {
        this.refreshActors = value;
    }
}
