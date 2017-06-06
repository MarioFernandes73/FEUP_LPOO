package com.bubblerunner.game.transitions.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.MENU_BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.MENU_BUTTON_SIZE_WIDTH;

/**
 * Created by Mario on 15/05/2017.
 */

public class MenuTableActor extends Table {

    private GenericButton playButton;
    private GenericButton highscoresButton;
    private GenericButton exitButton;

    public MenuTableActor(GraphicsManager graphicsManager){

        this.playButton = new GenericButton(graphicsManager.playButton);
        this.highscoresButton = new GenericButton(graphicsManager.highscoresButton);
        this.exitButton = new GenericButton(graphicsManager.exitButton);

        this.add(playButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT/12);
        this.row();
        this.add(highscoresButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT/12);
        this.row();
        this.add(exitButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.setFillParent(true);
    }

    public boolean getPlayPressed(){
        return this.playButton.getPressed();
    }
    public boolean getHighscoresPressed(){
        return this.highscoresButton.getPressed();
    }
    public boolean getExitPressed(){
        return this.exitButton.getPressed();
    }

}
