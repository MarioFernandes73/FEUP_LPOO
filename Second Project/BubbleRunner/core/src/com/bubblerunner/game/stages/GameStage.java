package com.bubblerunner.game.stages;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.actors.BallActor;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.actors.ControllerTableActor;
import com.bubblerunner.game.actors.LedgeActor;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import java.util.ArrayList;
import java.util.Random;

import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_POS_X;
import static com.bubblerunner.game.constants.Constants.BALL_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.BALL_MAX_HEIGHT;
import static com.bubblerunner.game.constants.Constants.BALL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.RATIO;
import static com.bubblerunner.game.constants.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.constants.Constants.SCREEN_WIDTH;
import static com.bubblerunner.game.constants.Constants.VIEWPORT_WIDTH;
import static com.bubblerunner.game.constants.Constants.WORLD_GRAVITY_X;
import static com.bubblerunner.game.constants.Constants.WORLD_GRAVITY_Y;
import static com.bubblerunner.game.constants.Constants.WORLD_POSITION_ITERATIONS;
import static com.bubblerunner.game.constants.Constants.WORLD_VELOCITY_ITERATIONS;

/**
 * Created by Mario on 12/04/2017.
 */

public class GameStage extends Stage {

    private BubbleRunner game;
    private GraphicsManager graphicsManager;
    private Constants.GAME_STATE gameState;
    private int scoreUpdate;

    private final BallActor ballActor;
    private ArrayList<LedgeActor> ledgeActors = new ArrayList<LedgeActor>();
    private ControllerTableActor controllerTableActor;

    private final World world;

    public GameStage(BubbleRunner game) {

        this.game = game;
        this.gameState = Constants.GAME_STATE.RUNNING;
        this.scoreUpdate = 0;

        //set viewport
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));


        //load textures
        this.graphicsManager = graphicsManager.getInstance();

        //load textures
      //  game.getAssetManager().load("kick.wav", Sound.class);
       // game.getAssetManager().load("music.mp3", Music.class);
       // game.getAssetManager().finishLoading();





        this.world = new World(new Vector2(WORLD_GRAVITY_X, WORLD_GRAVITY_Y), true);

        this.ballActor = new BallActor(game.getGraphicsManager(), world);
        this.addActor(ballActor);

        this.ledgeActors.add(new LedgeActor(world, game.getGraphicsManager(),2,2,1));
        this.ledgeActors.add(new LedgeActor(world, game.getGraphicsManager(),1,1,1));
        this.ledgeActors.add(new LedgeActor(world, game.getGraphicsManager(),0,0,0));
        for(int i = 0; i < this.ledgeActors.size(); i++){
            this.addActor(this.ledgeActors.get(i));
        }


        this.controllerTableActor = new ControllerTableActor(game.getGraphicsManager());
        this.addActor(this.controllerTableActor);



      //  kickSound = game.getAssetManager().get("kick.wav");
       // Music music = game.getAssetManager().get("music.mp3");
      //  music.setLooping(true);
       // music.play();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Step the world
        world.step(delta, WORLD_VELOCITY_ITERATIONS, WORLD_POSITION_ITERATIONS);

        // Update the ball actor position
        checkWallCollision();
        handleControllerInput();
        updateBallPosition();
        updateGameState();

        // Update the ledges positions
        updateLedgePosition();
    }


    private void handleControllerInput() {
        if(controllerTableActor.getRightPressed()) {
            this.ballActor.setBodyLinearVelocity(BALL_VELOCITY,0);
         //   Vector2 vector = new Vector2((float).2, 0);
         //   vector.rotateRad(ballBody.getAngle());
        //    ballBody.applyForceToCenter(vector, false);
           // if(ballBody.getLinearVelocity() > maxSpeed)
            //    ballBody.setLinearVelocity(maxSpeed);
        }
        else if (controllerTableActor.getLeftPressed()) {
            this.ballActor.setBodyLinearVelocity(-BALL_VELOCITY,0);
         //   Vector2 vector = new Vector2((float)-0.02, 0);
        //    vector.rotateRad(ballBody.getAngle());
         //   ballBody.applyForceToCenter(vector, false);
            //if(ballBody.getLinearVelocity() < (-1*maxSpeed))
              //  ballBody.setLinearVelocity(-1*maxSpeed);
        }
        else {
            this.ballActor.setBodyLinearVelocity(0,0);
        }
    }

    private void checkWallCollision(){
        if (this.ballActor.getBodyPosition().x < 0){
            this.ballActor.setBodyTransform(0, this.ballActor.getBodyPosition().y, 0);
        }
        else if(this.ballActor.getBodyPosition().x > Constants.VIEWPORT_WIDTH){
            this.ballActor.setBodyTransform(Constants.VIEWPORT_WIDTH, this.ballActor.getBodyPosition().y, 0);
        }
    }

    private void updateBallPosition(){
        this.ballActor.setRotation((float) Math.toDegrees(this.ballActor.getBodyAngle()));
        this.ballActor.setPosition(this.ballActor.getBodyPosition().x / Constants.PIXEL_TO_METER, this.ballActor.getBodyPosition().y / Constants.PIXEL_TO_METER);
    }

    private void updateLedgePosition(){
        for(int i = 0; i < this.ledgeActors.size(); i++){
            LedgeActor currentLedge = this.ledgeActors.get(i);
            if(currentLedge.getBodyPosition().y > (VIEWPORT_WIDTH * RATIO)){        // ledge has reached end of screen
                this.ledgeActors.get(i).delete();
                this.ledgeActors.remove(i);
                this.scoreUpdate = 1;
            }
            else if(currentLedge.getBodyPosition().y > (LEDGE_INITIAL_POS_Y[3]) && !currentLedge.hasSpawnedAnother() ){     // ledge has reached 3/4 of the screen (create a new one)

                LedgeActor newLedge = createRandomLedge();
                this.ledgeActors.add(newLedge);
                this.addActor(newLedge);
                this.controllerTableActor.remove();     // controller always on top of other actors
                this.addActor(controllerTableActor);
                currentLedge.setSpawnedAnother(true);
            }
        }
    }

    private LedgeActor createRandomLedge(){
        Random generator = new Random();
        int width = generator.nextInt(2);
        int posX = 0;
        if(width == 0){
            posX = generator.nextInt(3);        // random between 0 and 2
        } else if (width == 1) {
            posX = generator.nextInt(4);        // random between 0 and 3
        }
        return new LedgeActor(world, game.getGraphicsManager(),posX,0,width);
    }

    private void updateGameState(){
        if(ballActor.getYPosition() >= BALL_MAX_HEIGHT){
            this.gameState = Constants.GAME_STATE.OVER;
            //TEST OLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAAOLAAA
            this.scoreUpdate = -1;
        }
    }

    public int getScoreUpdate(){
        return this.scoreUpdate;
    }

    public void setScoreUpdate(int score){
        this.scoreUpdate = score;
    }

}


