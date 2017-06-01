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

    private GenericButton rightButton;
    private GenericButton leftButton;

    public ControllerTableActor(GraphicsManager graphicsManager){

        this.left().bottom();
        this.leftButton = new GenericButton(graphicsManager.hud.leftButton);
        this.rightButton = new GenericButton(graphicsManager.hud.rightButton);
        this.add(leftButton).size(leftButton.getWidth()*1.5f, leftButton.getHeight()*1.5f);
        this.add();
        this.add(rightButton).size(rightButton.getWidth()*1.5f, rightButton.getHeight()*1.5f);
    }

    public boolean getRightPressed(){
        return this.rightButton.getPressed();
    }

    public boolean getLeftPressed(){
        return this.leftButton.getPressed();
    }
}
