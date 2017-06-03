package com.bubblerunner.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bubblerunner.game.utils.gui.GameGraphics;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.MENU_BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.constants.Constants.MENU_BUTTON_SIZE_WIDTH;

/**
 * Created by Mario on 22/05/2017.
 */

public class HighscoresTableActor extends Table {

    public HighscoresTableActor(GraphicsManager graphicsManager, String highscoresText){

        Label label = new Label(highscoresText, new Label.LabelStyle(new BitmapFont(), null));
        label.setColor(Color.WHITE);
        this.add(label).size(80, 80);

        this.setFillParent(true);

    }
}
