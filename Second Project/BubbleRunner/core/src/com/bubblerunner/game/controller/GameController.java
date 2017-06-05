package com.bubblerunner.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.constants.Constants.GAME_STATE;
import com.bubblerunner.game.controller.entities.BallBody;
import com.bubblerunner.game.controller.entities.LedgeBody;
import com.bubblerunner.game.model.GameModel;
import com.bubblerunner.game.model.entities.LedgeModel;
import com.bubblerunner.game.utils.Point;

import java.util.ArrayList;
import java.util.Random;

import static com.bubblerunner.game.constants.Constants.BALL_MAX_HEIGHT;
import static com.bubblerunner.game.constants.Constants.BALL_MIN_HEIGHT;
import static com.bubblerunner.game.constants.Constants.GAME_STATE.OVER;
import static com.bubblerunner.game.constants.Constants.GAME_STATE.RUNNING;
import static com.bubblerunner.game.constants.Constants.LEDGE_GENERATOR_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.LEDGE_HEIGHT_GENERATOR_INDEX;
import static com.bubblerunner.game.constants.Constants.LEDGE_INCREMENT_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_SMALL;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_POS_Y;
import static com.bubblerunner.game.constants.Constants.LEDGE_INITIAL_VELOCITY;
import static com.bubblerunner.game.constants.Constants.LEDGE_SIZES;
import static com.bubblerunner.game.constants.Constants.LEDGE_TYPES;
import static com.bubblerunner.game.constants.Constants.LEDGE_TYPE_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.SMALL;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH_POSSIBILITIES;
import static com.bubblerunner.game.constants.Constants.RATIO;
import static com.bubblerunner.game.constants.Constants.VIEWPORT_WIDTH;
import static com.bubblerunner.game.constants.Constants.WORLD_GRAVITY_X;
import static com.bubblerunner.game.constants.Constants.WORLD_GRAVITY_Y;
import static com.bubblerunner.game.constants.Constants.WORLD_POSITION_ITERATIONS;
import static com.bubblerunner.game.constants.Constants.WORLD_VELOCITY_ITERATIONS;

/**
 * Created by Mario on 04/06/2017.
 */

public class GameController {

    private int scoreUpdate;
    private GAME_STATE gameState;
    private float ledgesCurrentVelocity;
    private GameModel gameModel;
    private World world;
    private BallBody ballBody;
    private ArrayList<LedgeBody> ledgeBodies;
    private boolean refreshActors;
    private boolean pendingDeletion;
    private boolean deleteLedgeActors;

    public GameController(){
        this.deleteLedgeActors = false;
        this.pendingDeletion = false;
        this.gameModel = new GameModel();
        this.gameState = RUNNING;
        this.ledgesCurrentVelocity = LEDGE_INITIAL_VELOCITY;
        this.scoreUpdate = 0;
        this.world = new World(new Vector2(WORLD_GRAVITY_X, WORLD_GRAVITY_Y), true);
        this.ballBody = new BallBody(world, gameModel.getBallModel());
        this.ledgeBodies = new ArrayList<LedgeBody>();
        for(int i = 0; i < this.gameModel.getLedgeModels().size(); i++){
            applyLedgeBody(this.gameModel.getLedgeModels().get(i));
        }

    }

    private void applyLedgeBody(LedgeModel model){
        ledgeBodies.add(new LedgeBody(world, model));
    }

    public void update(float delta){
        // Step the world
        world.step(delta, WORLD_VELOCITY_ITERATIONS, WORLD_POSITION_ITERATIONS);

        checkWallCollision();
        //handleControllerInput();
        updateBallPosition();
        checkBallHeight();
        updateLedgePosition();
        checkLedgeCollision();

        if(pendingDeletion){
            deleteLedge();
        }

        if(gameModel.getBallModel().getHp() == 0){
            gameState = OVER;
        }
    }

    private void deleteLedge(){
        for(int i = 0; i < this.ledgeBodies.size(); i++){
            LedgeBody currentLedge = this.ledgeBodies.get(i);
            if(currentLedge.getCanDelete()){
                world.destroyBody(currentLedge.getBody());
                currentLedge.delete();
                this.ledgeBodies.remove(i);
                this.scoreUpdate = 1;
                this.pendingDeletion = false;
                break;
            }
        }
    }

    private void checkWallCollision(){
        if (this.ballBody.getBounds().x - this.ballBody.getBounds().radius  <= 0){
            this.ballBody.setTransform(this.ballBody.getBounds().radius, this.ballBody.getY(), 0);
        }
        else if(this.ballBody.getBounds().x + this.ballBody.getBounds().radius >= Constants.VIEWPORT_WIDTH){
            this.ballBody.setTransform(Constants.VIEWPORT_WIDTH - this.ballBody.getBounds().radius, this.ballBody.getY(), 0);
        }
    }

    private void updateBallPosition(){
        this.ballBody.setPos(this.ballBody.getX() / Constants.PIXEL_TO_METER, this.ballBody.getY() / Constants.PIXEL_TO_METER);
    }

    private void updateLedgePosition(){
        for(int i = 0; i < this.ledgeBodies.size(); i++){
            LedgeBody currentLedge = this.ledgeBodies.get(i);
            if(currentLedge.getY() > (VIEWPORT_WIDTH * RATIO)){        // ledge has reached end of screen
                currentLedge.setNeedsDelete(true);
                this.pendingDeletion = true;
                this.deleteLedgeActors = true;
            }
            else if(currentLedge.getY() > LEDGE_GENERATOR_POS_Y && !currentLedge.hasSpawnedAnother() ){     // ledge has reached 3/4 of the screen (create a new one)
                LedgeBody newLedge = createRandomLedge();
                this.ledgeBodies.add(newLedge);
                currentLedge.setSpawnedAnother(true);
                this.refreshActors = true;
                this.ledgesCurrentVelocity += LEDGE_INCREMENT_VELOCITY;
                updateLedgesVelocity();
            }
        }
    }

    private void updateLedgesVelocity(){
        for(int i = 0; i < this.ledgeBodies.size(); i++){
            this.ledgeBodies.get(i).setVelocity(this.ledgesCurrentVelocity);
        }
    }

    private LedgeBody createRandomLedge(){
        Random generator = new Random();
        int type = generator.nextInt(LEDGE_TYPE_POSSIBILITIES);
        int width = generator.nextInt(LEDGE_WIDTH_POSSIBILITIES);
        LedgeModel.LedgeSize size = null;
        Point<Float> startingCoordinates = null;

        if(LEDGE_SIZES[width] == SMALL){
            startingCoordinates = new Point<Float>(LEDGE_INITIAL_POS_X_SMALL[generator.nextInt(LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES)], LEDGE_INITIAL_POS_Y[LEDGE_HEIGHT_GENERATOR_INDEX]);
            size = LedgeModel.LedgeSize.SMALL;
        } else if (LEDGE_SIZES[width] == MEDIUM) {
            startingCoordinates = new Point<Float>(LEDGE_INITIAL_POS_X_MEDIUM[generator.nextInt(LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES)], LEDGE_INITIAL_POS_Y[LEDGE_HEIGHT_GENERATOR_INDEX]);
            size = LedgeModel.LedgeSize.MEDIUM;
        }
        return new LedgeBody(world, new LedgeModel(startingCoordinates,size,LEDGE_WIDTH[width],LEDGE_HEIGHT,LEDGE_TYPES[type]));
    }

    private void checkBallHeight(){
        if(ballBody.getY() >= BALL_MAX_HEIGHT || ballBody.getY() <= BALL_MIN_HEIGHT){
            this.gameState = OVER;
        }
    }

    private void checkLedgeCollision(){
        for(int i = 0; i < this.ledgeBodies.size(); i++){
            Rectangle currentLedgeBody = this.ledgeBodies.get(i).getBounds();
            float ledgeContactMaxX = currentLedgeBody.x+currentLedgeBody.width/2;
            float ledgeContactMinX = currentLedgeBody.x-currentLedgeBody.width/2;
            float ballContactMaxX = ballBody.getBounds().x+ballBody.getBounds().radius;
            float ballContactMinX = ballBody.getBounds().x-ballBody.getBounds().radius;
            float ledgeContactY = currentLedgeBody.y+currentLedgeBody.height/2;
            float ballContactY = ballBody.getBounds().y-ballBody.getBounds().radius;
            if( ballContactY >= ledgeContactY && ballContactY <= ledgeContactY + 0.01 && ballContactMaxX >= ledgeContactMinX && ballContactMinX <= ledgeContactMaxX  ){
                ballBody.setHp(ballBody.getHp() - this.ledgeBodies.get(i).getLethality().getValue());
            }
        }
    }

    public boolean getDeleteLedgeActors(){return this.deleteLedgeActors;}

    public void setDeleteLedgeActors(boolean value){this.deleteLedgeActors = value;}

    public GAME_STATE getGameState(){return this.gameState; }

    public int getScoreUpdate(){
        return this.scoreUpdate;
    }

    public void setScoreUpdate(int score){
        this.scoreUpdate = score;
    }

    public BallBody getBallBody(){return this.ballBody;}

    public ArrayList<LedgeBody> getLedgeBodies(){return this.ledgeBodies;}

    public boolean getRefreshLedgeActors(){return this.refreshActors;}

    public void setRefreshLedgeActors(boolean value){this.refreshActors = value;}


}
