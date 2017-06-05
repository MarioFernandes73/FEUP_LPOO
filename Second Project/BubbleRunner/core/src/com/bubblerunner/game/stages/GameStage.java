package com.bubblerunner.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.actors.BallActor;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.actors.ControllerTableActor;
import com.bubblerunner.game.actors.LedgeActor;
import com.bubblerunner.game.actors.NormalLedgeActor;
import com.bubblerunner.game.actors.SpikedLedgeActor;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.screens.GenericScreen;
import com.bubblerunner.game.utils.Point;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import java.util.ArrayList;
import java.util.Random;

import static com.bubblerunner.game.constants.Constants.BALL_MAX_HEIGHT;
import static com.bubblerunner.game.constants.Constants.BALL_MIN_HEIGHT;
import static com.bubblerunner.game.constants.Constants.BALL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.GAME_STATE.OVER;
import static com.bubblerunner.game.constants.Constants.GAME_STATE.RUNNING;
import static com.bubblerunner.game.constants.Constants.LEDGE_GENERATOR_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_HEIGHT_GENERATOR_INDEX;
import static com.bubblerunner.game.constants.Constants.LEDGE_INCREMENT_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_INDEX_LEFT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_INDEX_MLEFT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_INDEX_MRIGHT;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_LARGE;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_SMALL;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_BOTTOM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_MUP;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y_INDEX_UP;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.LETHAL;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.constants.Constants.LEDGE_SIZES;
import static com.bubblerunner.game.constants.Constants.LEDGE_TYPES;
import static com.bubblerunner.game.constants.Constants.LEDGE_TYPE_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.LARGE;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.SMALL;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH_POSSIBILITIES;
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
    private float ledgesCurrentVelocity;
    private ControllerTableActor controllerTableActor;

    private final World world;

    public GameStage(BubbleRunner game) {

        this.game = game;
        this.gameState = RUNNING;
        this.ledgesCurrentVelocity = LEDGE_INITIAL_VELOCITY;
        this.scoreUpdate = 0;

        //set viewport
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));

        //load textures
        this.graphicsManager = graphicsManager.getInstance();

        //load textures
      //  game.getAssetManager().load("kick.wav", Sound.class);
       // game.getAssetManager().load("music.mp3", Music.class);
       // game.getAssetManager().finishLoading();
        //  kickSound = game.getAssetManager().get("kick.wav");
        // Music music = game.getAssetManager().get("music.mp3");
        //  music.setLooping(true);
        // music.play();

        //create world
        this.world = new World(new Vector2(WORLD_GRAVITY_X, WORLD_GRAVITY_Y), true);

        //create ball
        this.ballActor = new BallActor(graphicsManager, world);
        this.addActor(ballActor);

        //create ceiling and floor spikes
        LedgeActor ceiling = new LedgeActor(graphicsManager.gameGraphics.ball, world, new Point<Float>(LEDGE_INITIAL_POS_X_LARGE, LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_UP]),LEDGE_WIDTH[LARGE.getValue()]);
        this.addActor(ceiling);
        LedgeActor floor = new LedgeActor(graphicsManager.gameGraphics.ball, world, new Point<Float>(LEDGE_INITIAL_POS_X_LARGE, LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_BOTTOM]),LEDGE_WIDTH[LARGE.getValue()]);
        this.addActor(floor);

        //create initial ledges
        this.ledgeActors.add(new NormalLedgeActor(graphicsManager, world, new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[LEDGE_INITIAL_POS_X_INDEX_MRIGHT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_MUP]),LEDGE_WIDTH[MEDIUM.getValue()]));
        this.ledgeActors.add(new NormalLedgeActor(graphicsManager, world , new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[LEDGE_INITIAL_POS_X_INDEX_MLEFT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM]),LEDGE_WIDTH[MEDIUM.getValue()]));
        this.ledgeActors.add(new NormalLedgeActor(graphicsManager, world , new Point<Float>(LEDGE_INITIAL_POS_X_MEDIUM[LEDGE_INITIAL_POS_X_INDEX_LEFT], LEDGE_INITIAL_POS_Y[LEDGE_INITIAL_POS_Y_INDEX_BOTTOM]),LEDGE_WIDTH[SMALL.getValue()]));
        for(int i = 0; i < this.ledgeActors.size(); i++){
            this.addActor(this.ledgeActors.get(i));
        }

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

        if(gameState == OVER){
            game.setScreen(new GenericScreen(game, new GameOverStage(game)));
        }

        // Step the world
        world.step(delta, WORLD_VELOCITY_ITERATIONS, WORLD_POSITION_ITERATIONS);

        // Update the ball actor position
        checkWallCollision();
        handleControllerInput();
        updateBallPosition();
        checkBallHeight();

        // Update the ledges positions
        updateLedgePosition();

        checkLedgeCollision();

        if(ballActor.getHp() == 0){
            gameState = OVER;
        }

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
           // game.setScreen(new MenuScreen(game));
        }
        else {
            this.ballActor.setBodyLinearVelocity(0,0);
        }
    }

    private void checkWallCollision(){
        if (this.ballActor.getBounds().x - this.ballActor.getBounds().radius  <= 0){
            this.ballActor.setBodyTransform(this.ballActor.getBounds().radius, this.ballActor.getBodyPosition().y, 0);
        }
        else if(this.ballActor.getBounds().x + this.ballActor.getBounds().radius >= Constants.VIEWPORT_WIDTH){
            this.ballActor.setBodyTransform(Constants.VIEWPORT_WIDTH - this.ballActor.getBounds().radius, this.ballActor.getBodyPosition().y, 0);
        }
    }

    private void updateBallPosition(){
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
            else if(currentLedge.getBodyPosition().y > (LEDGE_GENERATOR_POS_Y) && !currentLedge.hasSpawnedAnother() ){     // ledge has reached 3/4 of the screen (create a new one)

                LedgeActor newLedge = createRandomLedge();
                this.ledgeActors.add(newLedge);
                this.addActor(newLedge);
                this.controllerTableActor.remove();     // controller always on top of other actors
                this.addActor(controllerTableActor);
                currentLedge.setSpawnedAnother(true);
                this.ledgesCurrentVelocity += LEDGE_INCREMENT_VELOCITY;
                updateLedgesVelocity();
            }
        }
    }

    private void updateLedgesVelocity(){
        for(int i = 0; i < this.ledgeActors.size(); i++){
            this.ledgeActors.get(i).setVelocity(this.ledgesCurrentVelocity);
        }
    }

    private LedgeActor createRandomLedge(){
        Random generator = new Random();
        int type = generator.nextInt(LEDGE_TYPE_POSSIBILITIES);
        int width = generator.nextInt(LEDGE_WIDTH_POSSIBILITIES);

        Point<Float> startingCoordinates = null;

        if(LEDGE_SIZES[width] == SMALL){
            startingCoordinates = new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[generator.nextInt(LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES)], LEDGE_INITIAL_POS_Y[LEDGE_HEIGHT_GENERATOR_INDEX]);
        } else if (LEDGE_SIZES[width] == MEDIUM) {
            startingCoordinates = new Point<Float>(LEDGE_INITIAL_POS_X_MEDIUM[generator.nextInt(LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES)], LEDGE_INITIAL_POS_Y[LEDGE_HEIGHT_GENERATOR_INDEX]);
        }

        if(LEDGE_TYPES[type] == LETHAL){
            return new SpikedLedgeActor(graphicsManager, world, startingCoordinates, LEDGE_WIDTH[width]);
        } else if (LEDGE_TYPES[type] == NONLETHAL){
            return new NormalLedgeActor(graphicsManager, world, startingCoordinates, LEDGE_WIDTH[width]);
        }

        return null;
    }

    private void checkBallHeight(){
        if(ballActor.getYPosition() >= BALL_MAX_HEIGHT || ballActor.getYPosition() <= BALL_MIN_HEIGHT){
            this.gameState = OVER;
        }
    }

    public int getScoreUpdate(){
        return this.scoreUpdate;
    }

    public void setScoreUpdate(int score){
        this.scoreUpdate = score;
    }

    public void checkLedgeCollision(){
        for(int i = 0; i < this.ledgeActors.size(); i++){
            Rectangle currentLedgeBody = this.ledgeActors.get(i).getBounds();
            float ledgeContactMaxX = currentLedgeBody.x+currentLedgeBody.width/2;
            float ledgeContactMinX = currentLedgeBody.x-currentLedgeBody.width/2;
            float ballContactMaxX = ballActor.getBounds().x+ballActor.getBounds().radius;
            float ballContactMinX = ballActor.getBounds().x-ballActor.getBounds().radius;
            float ledgeContactY = currentLedgeBody.y+currentLedgeBody.height/2;
            float ballContactY = ballActor.getBounds().y-ballActor.getBounds().radius;
            if( ballContactY >= ledgeContactY && ballContactY <= ledgeContactY + 0.01 && ballContactMaxX >= ledgeContactMinX && ballContactMinX <= ledgeContactMaxX  ){
                ballActor.setHp(ballActor.getHp() - this.ledgeActors.get(i).getLethality());
            }
        }
    }

    public GraphicsManager getGraphicsManager(){
        return this.graphicsManager;
    }

}


