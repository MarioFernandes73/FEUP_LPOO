package com.bubblerunner.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.stages.MenuStage;

import java.util.ArrayList;

/**
 * Created by Mario on 03/06/2017.
 */

public class GenericScreen implements Screen {

    private Stage currentScreenStage;

    public GenericScreen(BubbleRunner game, Stage currentScreenStage) {
        this.currentScreenStage = currentScreenStage;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        currentScreenStage.act(delta);
        currentScreenStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        currentScreenStage.getViewport().update(width, height, true);
        currentScreenStage.getViewport().update(width, height, true);
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
