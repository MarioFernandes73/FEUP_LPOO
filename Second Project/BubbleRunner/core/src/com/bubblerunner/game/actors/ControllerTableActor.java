package com.bubblerunner.game.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.BUTTON_SIZE_WIDTH;


/**
 * Created by Mario on 13/05/2017.
 */

public class ControllerTableActor extends Table {

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public ControllerTableActor(GraphicsManager graphicsManager){

        this.left().bottom();

        Image leftImg = new Image(graphicsManager.hud.leftButton);
        leftImg.setSize(BUTTON_SIZE_WIDTH,BUTTON_SIZE_HEIGHT);
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
        rightImg.setSize(BUTTON_SIZE_WIDTH,BUTTON_SIZE_HEIGHT);
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
        this.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        this.add();
        this.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
    }

    public boolean getRightPressed(){
        return this.rightPressed;
    }

    public boolean getLeftPressed(){
        return this.leftPressed;
    }
}
