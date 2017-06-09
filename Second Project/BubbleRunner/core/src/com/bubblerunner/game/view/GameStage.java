package com.bubblerunner.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.controller.GameControllerState;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.GameModelState;
import com.bubblerunner.game.utils.Constants;
import com.bubblerunner.game.view.entities.BallActor;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.view.entities.ControllerTableActor;
import com.bubblerunner.game.view.entities.LedgeActor;
import com.bubblerunner.game.controller.GameController;
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import java.util.ArrayList;

import static com.bubblerunner.game.utils.Constants.BALL_VELOCITY;
import static com.bubblerunner.game.utils.Constants.GAME_CREATION.RELEASE;
import static com.bubblerunner.game.utils.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.utils.Constants.LEDGE_INITIAL_POS_Y_INDEX_UP;
import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY.LETHAL;
import static com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.utils.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.utils.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.utils.Constants.SCREEN_WIDTH;

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
        this.gameController = new GameController(new GameModel(new GameModelState(RELEASE)), new GameControllerState(RELEASE));

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

        //create ball
        this.ballActor = new BallActor(graphicsManager, gameController.getBallBody());
        this.addActor(ballActor);

        //create initial ledges
        for(int i = 0; i < this.gameController.getLedgeBodies().size(); i++){
            LedgeActor newLedgeActor = new LedgeActor(graphicsManager.normalLedge,this.gameController.getLedgeBodies().get(i));
            this.ledgeActors.add(newLedgeActor);
            this.addActor(newLedgeActor);
            newLedgeActor.setHasActor(true);
        }

        //create ceiling and floor spikes
        Image floor = new Image(graphicsManager.spikedLedge);
        floor.setBounds(0,0,SCREEN_WIDTH,LEDGE_HEIGHT/PIXEL_TO_METER);
        this.addActor(floor);
        Image ceiling = new Image(graphicsManager.spikedLedge2);
        ceiling.setBounds(0,LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_UP]/PIXEL_TO_METER,SCREEN_WIDTH,LEDGE_HEIGHT/PIXEL_TO_METER);
        this.addActor(ceiling);


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
                if(currentBody.getLethality() == NONLETHAL){
                    this.addActor(new LedgeActor(graphicsManager.normalLedge, currentBody));
                } else if(currentBody.getLethality() == LETHAL){
                    this.addActor(new LedgeActor(graphicsManager.spikedLedge, currentBody));
                }

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

    public GameController getGameController(){return this.gameController;}

}


