package com.bubblerunner.game.transitions.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.transitions.stages.GameOverStage;
import com.bubblerunner.game.transitions.stages.ScoreStage;
import com.bubblerunner.game.utils.AssetsManager;
import com.bubblerunner.game.view.GameStage;

import static com.bubblerunner.game.utils.Constants.GAME_STATE.OVER;

public class BubbleScreen implements Screen {

    private final BubbleRunner game;
    private final GameStage gameStage;
    private final ScoreStage scoreStage;
    private final AssetsManager assetsManager;

    public BubbleScreen(BubbleRunner game) {
        this.game = game;
        this.gameStage = new GameStage();
        this.scoreStage = new ScoreStage(game);
        this.assetsManager = AssetsManager.getInstance();
        assetsManager.music.setLooping(true);
        assetsManager.music.play();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Steps the stage
        gameStage.act(delta);

        scoreStage.addScore(gameStage.getScoreUpdate());
        gameStage.setScoreUpdate(0);

        if (gameStage.getGameController().getGameState() == OVER) {
            assetsManager.music.stop();
            game.setScreen(new GenericScreen(game, new GameOverStage(game, scoreStage.getCurrentScore())));
        }

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
