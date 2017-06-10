package com.bubblerunner.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.controller.GameControllerState;
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.GameModelState;
import com.bubblerunner.game.utils.AssetsManager;
import com.bubblerunner.game.view.entities.BallActor;
import com.bubblerunner.game.view.entities.ControllerTableActor;
import com.bubblerunner.game.view.entities.LedgeActor;
import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;

import java.util.ArrayList;

import static com.bubblerunner.game.utils.Constants.BALL_VELOCITY;
import static com.bubblerunner.game.utils.Constants.GAME_CREATION.RELEASE;
import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y_INDEX_UP;
import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY.LETHAL;
import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.utils.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.utils.Constants.SCREEN_WIDTH;


/**
 * Game overall view extending Stage LIBGdx class.
 */
public class GameStage extends Stage {

    /**
     * The AssetsManager containing the textures and music
     */
    private final AssetsManager assetsManager;

    /**
     * The game's controller module
     */
    private GameController gameController;

    /**
     * The user's controls
     */
    private ControllerTableActor controllerTableActor;

    /**
     * The ball's actor.
     */
    private final BallActor ballActor;

    /**
     * The ledge's actors.
     */
    private ArrayList<LedgeActor> ledgeActors = new ArrayList<LedgeActor>();

    /**
     * Constructs a new GameStage using a new GameController and a new GameModel.
     * Sets all the initial configurations such as viewport, actors and scenario.
     */
    public GameStage() {

        this.gameController = new GameController(new GameModel(new GameModelState(RELEASE)), new GameControllerState(RELEASE));

        //set viewport
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));

        //load textures
        this.assetsManager = AssetsManager.getInstance();

        //create ball
        this.ballActor = new BallActor(assetsManager, gameController.getBallBody());
        this.addActor(ballActor);

        //create initial ledges
        for (int i = 0; i < this.gameController.getLedgeBodies().size(); i++) {
            LedgeActor newLedgeActor = new LedgeActor(assetsManager.normalLedge, this.gameController.getLedgeBodies().get(i));
            this.ledgeActors.add(newLedgeActor);
            this.addActor(newLedgeActor);
            newLedgeActor.setHasActor(true);
        }

        //create ceiling and floor spikes
        Image floor = new Image(assetsManager.spikedLedge);
        floor.setBounds(0, 0, SCREEN_WIDTH, LEDGE_HEIGHT / PIXEL_TO_METER);
        this.addActor(floor);
        Image ceiling = new Image(assetsManager.spikedLedge2);
        ceiling.setBounds(0, LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_UP] / PIXEL_TO_METER, SCREEN_WIDTH, LEDGE_HEIGHT / PIXEL_TO_METER);
        this.addActor(ceiling);


        //create controller
        this.controllerTableActor = new ControllerTableActor(assetsManager);
        this.addActor(this.controllerTableActor);

        Gdx.input.setInputProcessor(this);
    }

    /**
     * Overriden draw method.
     */
    @Override
    public void draw() {
        super.draw();
    }

    /**
     * Act method which simply calls the update cycle of the game controller,
     * checks for any refresh/deletion flag,
     * and handles the user's controller's input.
     *
     * @param delta the world's float's variation.
     */
    @Override
    public void act(float delta) {
        super.act(delta);

        gameController.update(delta);
        handleControllerInput();

        if (gameController.getRefreshLedgeActors()) {
            updateLedgeActors();
            gameController.setRefreshLedgeActors(false);
        }
        if (gameController.getDeleteLedgeActors()) {
            deleteLedgeActors();
            gameController.setDeleteLedgeActors(false);
        }
    }

    /**
     * Deletes a ledge's actor depending on the flag.
     */
    public void deleteLedgeActors() {
        for (Actor actor : this.getActors()) {
            if (actor instanceof LedgeActor && ((LedgeActor) actor).needsDelete()) {
                ((LedgeActor) actor).setCanDelete(true);
                actor.remove();
            }
        }
    }

    /**
     * Updates the ledge's actors.
     */
    public void updateLedgeActors() {
        for (int i = 0; i < this.gameController.getLedgeBodies().size(); i++) {
            LedgeBody currentBody = this.gameController.getLedgeBodies().get(i);
            if (!currentBody.getHasActor()) {
                if (currentBody.getLethality() == NONLETHAL) {
                    this.addActor(new LedgeActor(assetsManager.normalLedge, currentBody));
                } else if (currentBody.getLethality() == LETHAL) {
                    this.addActor(new LedgeActor(assetsManager.spikedLedge, currentBody));
                }

                controllerTableActor.remove();
                this.addActor(controllerTableActor);
                currentBody.setHasActor(true);
            }
        }
    }

    /**
     * Handles the user's input on the controller.
     */
    private void handleControllerInput() {
        if (controllerTableActor.getRightPressed()) {
            this.ballActor.setBodyLinearVelocity(BALL_VELOCITY, 0);
        } else if (controllerTableActor.getLeftPressed()) {
            this.ballActor.setBodyLinearVelocity(-BALL_VELOCITY, 0);
        } else {
            this.ballActor.setBodyLinearVelocity(0, 0);
        }
    }

    /**
     * Wrapper for the scoreUpdate get method.
     *
     * @return The scoreUpdate value.
     */
    public int getScoreUpdate() {
        return this.gameController.getScoreUpdate();
    }

    /**
     * Wrapper for the scoreUpdate set method.
     *
     * @param scoreUpdate the scoreUpdate value.
     */
    public void setScoreUpdate(int scoreUpdate) {
        this.gameController.setScoreUpdate(scoreUpdate);
    }

    /**
     * Wrapper for the gameController get method.
     *
     * @return The gameController.
     */
    public GameController getGameController() {
        return this.gameController;
    }

}


