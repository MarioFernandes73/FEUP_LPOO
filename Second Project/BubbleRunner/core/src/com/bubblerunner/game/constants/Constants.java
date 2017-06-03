package com.bubblerunner.game.constants;

import com.badlogic.gdx.Gdx;

import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.LETHAL;
import static com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY.NONLETHAL;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.MEDIUM;
import static com.bubblerunner.game.constants.Constants.LEDGE_WIDTH.SMALL;

/**
 * Created by Mario on 08/05/2017.
 */

public class Constants {

    //GAME
    public enum GAME_STATE {RUNNING, OVER}

    //MEASURES
    public static final float PIXEL_TO_METER = 0.22f / 200;
    public static final float METER_TO_PIXEL = 1/PIXEL_TO_METER;

    //SCREEN
    public static final int VIEWPORT_WIDTH = 4;
    public static final float RATIO = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
    public static final float SCREEN_WIDTH = VIEWPORT_WIDTH / PIXEL_TO_METER;
    public static final float SCREEN_HEIGHT = VIEWPORT_WIDTH / PIXEL_TO_METER * RATIO;

    //WORLD
    public static final int WORLD_GRAVITY_X = 0;
    public static final int WORLD_GRAVITY_Y = -20;//-80;
    public static final int WORLD_VELOCITY_ITERATIONS = 6;
    public static final int WORLD_POSITION_ITERATIONS = 2;

    //LEDGES
    //enums

    public enum LEDGE_LETHALITY {
        NONLETHAL(0), LETHAL(1);

        private final int value;
        private LEDGE_LETHALITY(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum LEDGE_WIDTH {
        SMALL(0), MEDIUM(1), LARGE(2);

        private final int value;
        private LEDGE_WIDTH(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    //enum arrays
    public static final LEDGE_LETHALITY [] LEDGE_TYPES = new LEDGE_LETHALITY [] {LETHAL, NONLETHAL};
    public static final LEDGE_WIDTH [] LEDGE_SIZES = new LEDGE_WIDTH [] {SMALL, MEDIUM};
    //generator possibilities
    public static final int LEDGE_WIDTH_POSSIBILITIES = LEDGE_SIZES.length;
    public static final int LEDGE_TYPE_POSSIBILITIES = LEDGE_TYPES.length;
    //initial position options
    public static final float[] LEDGE_INITIAL_POS_X_SMALL = new float[]{0,VIEWPORT_WIDTH/4,VIEWPORT_WIDTH/2,3*VIEWPORT_WIDTH/4};
    public static final float[] LEDGE_INITIAL_POS_X_MEDIUM = new float[]{0,VIEWPORT_WIDTH/4,VIEWPORT_WIDTH/2};
    public static final float LEDGE_INITIAL_POS_X_LARGE = 0;
    public static final float[] LEDGE_INITIAL_POS_Y = new float[] {0,VIEWPORT_WIDTH * RATIO/4, VIEWPORT_WIDTH * RATIO/2, 15 * VIEWPORT_WIDTH * RATIO/16};
    public static final float LEDGE_GENERATOR_POS_Y = 3 * VIEWPORT_WIDTH * RATIO/4;
    public static final float LEDGE_INITIAL_POS_Z = 0;  //no z variation
    //initial position possibilities (always bottom of screen, so y constant)
    public static final int LEDGE_INITIAL_POS_X_SMALL_POSSIBILITIES = LEDGE_INITIAL_POS_X_SMALL.length;
    public static final int LEDGE_INITIAL_POS_X_MEDIUM_POSSIBILITIES = LEDGE_INITIAL_POS_X_MEDIUM.length;
    //initial position indexes
    //X coordinate
    public static final int LEDGE_INITIAL_POS_X_INDEX_LEFT = 0;
    public static final int LEDGE_INITIAL_POS_X_INDEX_MLEFT = 1;
    public static final int LEDGE_INITIAL_POS_X_INDEX_MRIGHT = 2;
    public static final int LEDGE_INITIAL_POS_X_INDEX_RIGHT = 3;
    //Y coordinate
    public static final int LEDGE_INITIAL_POS_Y_INDEX_BOTTOM = 0;
    public static final int LEDGE_INITIAL_POS_Y_INDEX_MBOTTOM = 1;
    public static final int LEDGE_INITIAL_POS_Y_INDEX_MUP = 2;
    public static final int LEDGE_INITIAL_POS_Y_INDEX_UP = 3;

    public static final int LEDGE_HEIGHT_GENERATOR_INDEX = 0;



    public static final float[] LEDGE_WIDTH = new float[]{VIEWPORT_WIDTH/2, VIEWPORT_WIDTH/4, VIEWPORT_WIDTH};
    public static final float LEDGE_HEIGHT = VIEWPORT_WIDTH * RATIO / 16;
    public static final float LEDGE_INITIAL_VELOCITY = 0.2f;
    public static final float LEDGE_INCREMENT_VELOCITY = 0.1f;
    public static final float LEDGE_DENSITY = .5f;
    public static final float LEDGE_FRICTION = 0f;
    public static final float LEDGE_RESTITUTION = 0f;


    //BALL
    public static final int STARTING_HP = 1;
    public static final float BALL_RADIUS = 0.11f;
    public static final float BALL_MAX_HEIGHT = (15* VIEWPORT_WIDTH * RATIO / 16) - BALL_RADIUS;
    public static final float BALL_MIN_HEIGHT = LEDGE_HEIGHT + BALL_RADIUS;
    public static final float BALL_INITIAL_POS_X = VIEWPORT_WIDTH / 2 / PIXEL_TO_METER;
    public static final float BALL_INITIAL_POS_Y = VIEWPORT_WIDTH * RATIO / 2 / Constants.PIXEL_TO_METER;
    public static final int BALL_NUMBER_FRAMES = 8;
    public static final float BALL_FRAMES_DURATION = .5f;
    public static final int BALL_INITIAL_FRAME = 0;
    public static final float BALL_DENSITY = 0.8f;
    public static final float BALL_FRICTION = 0f;
    public static final float BALL_RESTITUTION = 0f;
    public static final float BALL_VELOCITY = 1;


    //CONTROLLER
    public static final float BUTTON_SIZE_WIDTH = 300;
    public static final float BUTTON_SIZE_HEIGHT = 300;

    //MENU BUTTONS
    public static final float MENU_BUTTON_SIZE_WIDTH = 500;
    public static final float MENU_BUTTON_SIZE_HEIGHT = 300;


    public static final int HIGHSCORES_MAX_PLAYERS = 5;
    public static final int HIGHSCORES_INITIAL_POS = 1;
}