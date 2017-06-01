package com.bubblerunner.game.actors;

import com.badlogic.gdx.Gdx;
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

    private GenericButton playButton;
    private GenericButton highscoresButton;
    private GenericButton exitButton;

    public MenuTableActor(GraphicsManager graphicsManager){

        this.playButton = new GenericButton(graphicsManager.hud.leftButton);
        this.highscoresButton = new GenericButton(graphicsManager.gameGraphics.ledge);
        this.exitButton = new GenericButton(graphicsManager.gameGraphics.ledge);

        this.add(playButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT/4);
        this.row();
        this.add(highscoresButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT/4);
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
