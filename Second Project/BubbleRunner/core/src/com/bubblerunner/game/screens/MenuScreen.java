package com.bubblerunner.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.stages.MenuStage;

/**
 * Created by Mario on 08/05/2017.
 */


public class MenuScreen implements Screen {

    private final MenuStage stage;

    public MenuScreen(BubbleRunner game) {
        this.stage = new MenuStage(game);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if(stage.pressed == true){
            stage.pressed = false;
            Gdx.app.log("ola","ola");
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
