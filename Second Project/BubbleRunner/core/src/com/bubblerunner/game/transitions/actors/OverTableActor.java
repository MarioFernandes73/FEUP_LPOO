package com.bubblerunner.game.transitions.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.bubblerunner.game.utils.gui.GraphicsManager;

/**
 * Created by Mario on 03/06/2017.
 */

public class OverTableActor extends Table {

    private GenericButton confirmButton;
    private TextField textField;

    public OverTableActor(GraphicsManager graphicsManager){

        this.confirmButton = new GenericButton(graphicsManager.hud.leftButton);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.YELLOW);

        TextField.TextFieldStyle temp = new TextField.TextFieldStyle();
        temp.font = font;
        temp.fontColor = Color.YELLOW;


        this.textField = new TextField("player" ,temp);
        this.textField.setText("Insert your name");

        this.add(textField);
        this.row();
        this.add(confirmButton).size(80, 80);;

        this.setFillParent(true);
    }

    public boolean getConfirmPressed(){
        return this.confirmButton.getPressed();
    }

}