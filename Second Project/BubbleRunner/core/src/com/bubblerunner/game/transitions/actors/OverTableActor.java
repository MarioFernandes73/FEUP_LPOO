package com.bubblerunner.game.transitions.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.bubblerunner.game.utils.AssetsManager;

import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.utils.Constants.RATIO;
import static com.bubblerunner.game.utils.Constants.VIEWPORT_WIDTH;

/**
 * Created by Mario on 03/06/2017.
 */

public class OverTableActor extends Table {

    private GenericButton confirmButton;
    private TextField textField;

    public OverTableActor(AssetsManager assetsManager, boolean register){

        this.confirmButton = new GenericButton(assetsManager.confirmButton);

        if(register){
            BitmapFont font = new BitmapFont();
            font.setColor(Color.YELLOW);

            TextField.TextFieldStyle temp = new TextField.TextFieldStyle();
            temp.font = font;
            temp.fontColor = Color.YELLOW;


            this.textField = new TextField("player" ,temp);
            this.textField.setText("Insert your name");

            this.add(new Image(assetsManager.highscoresBanner)).size(VIEWPORT_WIDTH/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/4/(20*PIXEL_TO_METER));
            this.row();
            this.add().size(VIEWPORT_WIDTH/1.5f/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/32/(20*PIXEL_TO_METER));
            this.row();
            this.add(textField).size(VIEWPORT_WIDTH/1.5f/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/8/(20*PIXEL_TO_METER));
        } else {
            this.add(new Image(assetsManager.gameOverBanner)).size(VIEWPORT_WIDTH/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/4/(20*PIXEL_TO_METER));
            this.row();
            this.add().size(VIEWPORT_WIDTH/1.5f/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/32/(20*PIXEL_TO_METER));
        }

        this.row();
        this.add().size(VIEWPORT_WIDTH/1.5f/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/32/(20*PIXEL_TO_METER));
        this.row();
        this.add(confirmButton).size(VIEWPORT_WIDTH/1.5f/(20*PIXEL_TO_METER), VIEWPORT_WIDTH*RATIO/8/(20*PIXEL_TO_METER));

        this.setFillParent(true);
    }

    public boolean getConfirmPressed(){
        return this.confirmButton.getPressed();
    }

    public String getName(){return this.textField.getText();}
}