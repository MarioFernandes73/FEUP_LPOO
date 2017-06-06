package com.bubblerunner.game.transitions.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Mario on 22/05/2017.
 */

public class HighscoresTableActor extends Table {

    public HighscoresTableActor(String highscoresText){

        Label label = new Label(highscoresText, new Label.LabelStyle(new BitmapFont(), null));
        label.setColor(Color.WHITE);
        this.add(label);

        this.setFillParent(true);

    }
}
