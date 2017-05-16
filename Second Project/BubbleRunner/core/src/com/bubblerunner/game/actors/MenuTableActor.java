package com.bubblerunner.game.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.BUTTON_SIZE_WIDTH;
import static com.bubblerunner.game.constants.Constants.MENU_BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.MENU_BUTTON_SIZE_WIDTH;
import static com.bubblerunner.game.constants.Constants.METER_TO_PIXEL;
import static com.bubblerunner.game.constants.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.constants.Constants.RATIO;
import static com.bubblerunner.game.constants.Constants.VIEWPORT_WIDTH;

/**
 * Created by Mario on 15/05/2017.
 */

public class MenuTableActor extends Table {

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public MenuTableActor(GraphicsManager graphicsManager){

        //this.left().bottom();

        Image leftImg = new Image(graphicsManager.gameGraphics.ledge);
        leftImg.setSize(MENU_BUTTON_SIZE_WIDTH,MENU_BUTTON_SIZE_HEIGHT);
        leftImg.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }

        });

        Image leftImg2 = new Image(graphicsManager.gameGraphics.ledge);
        leftImg.setSize(MENU_BUTTON_SIZE_WIDTH,MENU_BUTTON_SIZE_HEIGHT);
        leftImg.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }

        });

        Image rightImg = new Image(graphicsManager.hud.rightButton);
        rightImg.setSize(MENU_BUTTON_SIZE_WIDTH,MENU_BUTTON_SIZE_HEIGHT);
        rightImg.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }

        });
        this.add(leftImg).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT/4);
        this.row();
        this.add(leftImg2).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT/4);
        this.row();
        this.add(rightImg).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.setDebug(true);
        this.setFillParent(true);
    }
}
