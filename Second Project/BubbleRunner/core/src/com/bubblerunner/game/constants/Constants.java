package com.bubblerunner.game.constants;

import com.badlogic.gdx.Gdx;

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

    //BALL
    public static final float BALL_MAX_HEIGHT = 15* VIEWPORT_WIDTH * RATIO / 16;
    public static final float BALL_INITIAL_POS_X = VIEWPORT_WIDTH / 2 / PIXEL_TO_METER;
    public static final float BALL_INITIAL_POS_Y = VIEWPORT_WIDTH * RATIO / 2 / Constants.PIXEL_TO_METER;
    public static final float BALL_RADIUS = 0.11f;
    public static final int BALL_NUMBER_FRAMES = 8;
    public static final float BALL_FRAMES_DURATION = .5f;
    public static final int BALL_INITIAL_FRAME = 0;
    public static final float BALL_DENSITY = 0.8f;
    public static final float BALL_FRICTION = 0f;
    public static final float BALL_RESTITUTION = 0f;
    public static final float BALL_VELOCITY = 1;

    //LEDGES
    public static final float[] LEDGE_INITIAL_POS_X = new float[]{0,VIEWPORT_WIDTH/4,VIEWPORT_WIDTH/2,3*VIEWPORT_WIDTH/4};
    public static final float[] LEDGE_INITIAL_POS_Y = new float[] {0,VIEWPORT_WIDTH * RATIO/4, VIEWPORT_WIDTH * RATIO/2, 3 * VIEWPORT_WIDTH * RATIO/4};
    public static final float LEDGE_INITIAL_POS_Z = 0;
    public static final float[] LEDGE_WIDTH = new float[]{VIEWPORT_WIDTH/2, VIEWPORT_WIDTH/4};
    public static final float LEDGE_HEIGHT = VIEWPORT_WIDTH * RATIO / 16;
    public static final float LEDGE_INITIAL_VELOCITY = 0.2f;
    public static final float LEDGE_INCREMENT_VELOCITY = 0.1f;
    public static final float LEDGE_DENSITY = .5f;
    public static final float LEDGE_FRICTION = 0f;
    public static final float LEDGE_RESTITUTION = 0f;

    //CONTROLLER
    public static final float BUTTON_SIZE_WIDTH = 300;
    public static final float BUTTON_SIZE_HEIGHT = 300;

    //MENU BUTTONS
    public static final float MENU_BUTTON_SIZE_WIDTH = 500;
    public static final float MENU_BUTTON_SIZE_HEIGHT = 300;

}