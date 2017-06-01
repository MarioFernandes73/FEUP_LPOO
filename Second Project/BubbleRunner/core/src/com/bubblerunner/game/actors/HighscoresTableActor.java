package com.bubblerunner.game.actors;

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

    private GenericButton playButton;

    public HighscoresTableActor(GraphicsManager graphicsManager){



        this.playButton = new GenericButton(graphicsManager.hud.leftButton);
        //this.add(playButton).size(MENU_BUTTON_SIZE_WIDTH, MENU_BUTTON_SIZE_HEIGHT);

        Label label = new Label("olaaaaaaaaaa", new Label.LabelStyle(new BitmapFont(), null));
        //label.setPosition(10, VIEWPORT_WIDTH * ratio - 30);
        label.setColor(Color.WHITE);
        this.add(label).size(80, 80);

        this.setFillParent(true);

    }
}
