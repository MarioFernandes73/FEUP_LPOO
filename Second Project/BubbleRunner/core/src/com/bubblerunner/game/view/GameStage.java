package com.bubblerunner.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.view.entities.BallActor;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.view.entities.ControllerTableActor;
import com.bubblerunner.game.view.entities.LedgeActor;
import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.transitions.screens.GenericScreen;
import com.bubblerunner.game.transitions.stages.GameOverStage;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import java.util.ArrayList;

import static com.bubblerunner.game.constants.Constants.BALL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.GAME_STATE.OVER;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.constants.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.constants.Constants.SCREEN_WIDTH;

/**
 * Created by Mario on 12/04/2017.
 */

public class GameStage extends Stage {

    private final BubbleRunner game;
    private final GraphicsManager graphicsManager;
    private GameController gameController;
    private ControllerTableActor controllerTableActor;
    private final BallActor ballActor;
    private ArrayList<LedgeActor> ledgeActors = new ArrayList<LedgeActor>();


    public GameStage(BubbleRunner game) {

        this.game = game;
        this.gameController = new GameController();

        //set viewport
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));

        //load textures
        this.graphicsManager = GraphicsManager.getInstance();

        //load textures
      //  game.getAssetManager().load("kick.wav", Sound.class);
       // game.getAssetManager().load("music.mp3", Music.class);
       // game.getAssetManager().finishLoading();
        //  kickSound = game.getAssetManager().get("kick.wav");
        // Music music = game.getAssetManager().get("music.mp3");
        //  music.setLooping(true);
        // music.play();

        //create world

        //create ball
        this.ballActor = new BallActor(graphicsManager, gameController.getBallBody());
        this.addActor(ballActor);

        //create initial ledges
        for(int i = 0; i < this.gameController.getLedgeBodies().size(); i++){
            LedgeActor newLedgeActor = new LedgeActor(graphicsManager.gameGraphics.ledge,this.gameController.getLedgeBodies().get(i));
            this.ledgeActors.add(newLedgeActor);
            this.addActor(newLedgeActor);
            newLedgeActor.setHasActor(true);
        }

        //create ceiling and floor spikes
       // LedgeActor ceiling = new LedgeActor(graphicsManager.gameGraphics.ball, world, new Point<Float>(LEDGE_INITIAL_POS_X_LARGE, LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_UP]),LEDGE_WIDTH[LARGE.getValue()]);
      //  this.addActor(ceiling);
      //  LedgeActor floor = new LedgeActor(graphicsManager.gameGraphics.ball, world, new Point<Float>(LEDGE_INITIAL_POS_X_LARGE, LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_BOTTOM]),LEDGE_WIDTH[LARGE.getValue()]);
       // this.addActor(floor);


        //create controller
        this.controllerTableActor = new ControllerTableActor(graphicsManager);
        this.addActor(this.controllerTableActor);

        Gdx.input.setInputProcessor(this);
}

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        gameController.update(delta);
        handleControllerInput();

        if(gameController.getRefreshLedgeActors()){
            updateLedgeActors();
            gameController.setRefreshLedgeActors(false);
        }
        if(gameController.getDeleteLedgeActors()) {
            deleteLedgeActors();
            gameController.setDeleteLedgeActors(false);
        }

        if(gameController.getGameState() == OVER){
            game.setScreen(new GenericScreen(game, new GameOverStage(game)));
        }
    }

    public void deleteLedgeActors(){
        for(Actor actor : this.getActors()){
            if(actor instanceof LedgeActor && ((LedgeActor) actor).needsDelete()){
                ((LedgeActor) actor).setCanDelete(true);
                actor.remove();
            }
        }
    }

    public void updateLedgeActors(){
        for(int i = 0; i<this.gameController.getLedgeBodies().size(); i++){
            LedgeBody currentBody = this.gameController.getLedgeBodies().get(i);
            if(!currentBody.getHasActor()){
                this.addActor(new LedgeActor(graphicsManager.gameGraphics.ledge, currentBody));
                controllerTableActor.remove();
                this.addActor(controllerTableActor);
                currentBody.setHasActor(true);
            }
        }
    }


    private void handleControllerInput() {
        if(controllerTableActor.getRightPressed()) {
            this.ballActor.setBodyLinearVelocity(BALL_VELOCITY,0);
        }
        else if (controllerTableActor.getLeftPressed()) {
            this.ballActor.setBodyLinearVelocity(-BALL_VELOCITY,0);
        }
        else {
            this.ballActor.setBodyLinearVelocity(0,0);
        }
    }

    public int getScoreUpdate(){return this.gameController.getScoreUpdate();}

    public void setScoreUpdate(int scoreUpdate){this.gameController.setScoreUpdate(scoreUpdate);}

}


