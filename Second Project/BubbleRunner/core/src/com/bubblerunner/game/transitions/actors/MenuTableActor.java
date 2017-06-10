package com.bubblerunner.game.transitions.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bubblerunner.game.utils.AssetsManager;

import static com.bubblerunner.game.utils.Constants.MENU_BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.utils.Constants.MENU_BUTTON_SIZE_WIDTH;
import static com.bubblerunner.game.utils.Constants.SCREEN_WIDTH;

public class MenuTableActor extends Table {

    private GenericButton playButton;
    private GenericButton highscoresButton;
    private GenericButton exitButton;

    public MenuTableActor(AssetsManager assetsManager) {

        this.playButton = new GenericButton(assetsManager.playButton);
        this.highscoresButton = new GenericButton(assetsManager.highscoresButton);
        this.exitButton = new GenericButton(assetsManager.exitButton);

        this.add(new Image(assetsManager.menuBanner)).size(SCREEN_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT / 12);
        this.row();
        this.add(playButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT / 12);
        this.row();
        this.add(highscoresButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.row();
        this.add().size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT / 12);
        this.row();
        this.add(exitButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);
        this.setFillParent(true);
    }

    public boolean getPlayPressed() {
        return this.playButton.getPressed();
    }

    public boolean getHighscoresPressed() {
        return this.highscoresButton.getPressed();
    }

    public boolean getExitPressed() {
        return this.exitButton.getPressed();
    }

}
