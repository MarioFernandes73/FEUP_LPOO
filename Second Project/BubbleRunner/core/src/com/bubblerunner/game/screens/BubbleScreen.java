package com.bubblerunner.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.constants.Constants;
import com.bubblerunner.game.stages.GameStage;
import com.bubblerunner.game.stages.ScoreStage;

/**
 * Created by Mario on 12/04/2017.
 */

public class BubbleScreen implements Screen {

    private final GameStage gameStage;
    private final ScoreStage scoreStage;

    public BubbleScreen(BubbleRunner game) {
        this.gameStage = new GameStage(game);
        this.scoreStage = new ScoreStage(game);

        // Sets the stage as its input processor
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        // Steps the stage
        gameStage.act(delta);

        scoreStage.addScore(gameStage.getScoreUpdate());
        gameStage.setScoreUpdate(0);

        // Draws the stage
        gameStage.draw();
        scoreStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
        scoreStage.getViewport().update(width, height, true);
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
