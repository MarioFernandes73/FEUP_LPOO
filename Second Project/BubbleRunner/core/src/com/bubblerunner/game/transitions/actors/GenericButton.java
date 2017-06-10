package com.bubblerunner.game.transitions.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.bubblerunner.game.utils.Constants.BUTTON_SIZE_HEIGHT;
import static com.bubblerunner.game.utils.Constants.BUTTON_SIZE_WIDTH;

public class GenericButton extends Image {

    private Texture texture;
    private boolean pressed;

    public GenericButton(Texture texture) {

        this.texture = texture;
        this.pressed = false;
        this.setDrawable((new TextureRegionDrawable(new TextureRegion(this.texture))));
        this.setSize(BUTTON_SIZE_WIDTH, BUTTON_SIZE_HEIGHT);
        this.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pressed = false;
            }

        });

    }

    public boolean getPressed() {
        return this.pressed;
    }
}
