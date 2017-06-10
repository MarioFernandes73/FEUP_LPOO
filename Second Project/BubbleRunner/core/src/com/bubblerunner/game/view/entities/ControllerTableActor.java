package com.bubblerunner.game.view.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bubblerunner.game.transitions.actors.GenericButton;
import com.bubblerunner.game.utils.AssetsManager;


/**
 * Controller table which holds the arrows for the
 * player to click and move the ball.
 */
public class ControllerTableActor extends Table {

    /**
     * Right arrow
     */
    private GenericButton rightButton;

    /**
     * Left arrow
     */
    private GenericButton leftButton;

    /**
     * Constructs a new table
     *
     * @param assetsManager the manager that holds the textures.
     */
    public ControllerTableActor(AssetsManager assetsManager){

        this.left().bottom();
        this.leftButton = new GenericButton(assetsManager.leftButton);
        this.rightButton = new GenericButton(assetsManager.rightButton);
        this.add(leftButton).size(leftButton.getWidth()*1.5f, leftButton.getHeight()*1.5f);
        this.add();
        this.add(rightButton).size(rightButton.getWidth()*1.5f, rightButton.getHeight()*1.5f);
    }

    /**
     * Returns a boolean value determining if the user has clicked the right button.
     *
     * @return right button's pressed value.
     */
    public boolean getRightPressed(){
        return this.rightButton.getPressed();
    }

    /**
     * Returns a boolean value determining if the user has clicked the left button.
     *
     * @return left button's pressed value.
     */
    public boolean getLeftPressed(){
        return this.leftButton.getPressed();
    }
}
