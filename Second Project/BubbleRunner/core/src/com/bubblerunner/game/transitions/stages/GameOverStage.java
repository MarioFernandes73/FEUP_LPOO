package com.bubblerunner.game.transitions.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.transitions.actors.OverTableActor;
import com.bubblerunner.game.transitions.screens.GenericScreen;
import com.bubblerunner.game.utils.AssetsManager;
import com.bubblerunner.game.utils.Highscores;
import com.bubblerunner.game.utils.ScoreManager;

import java.io.IOException;
import java.util.Map;

import static com.bubblerunner.game.utils.Constants.HIGHSCORES_MAX_PLAYERS;
import static com.bubblerunner.game.utils.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.utils.Constants.RATIO;
import static com.bubblerunner.game.utils.Constants.VIEWPORT_WIDTH;

/**
 * Created by Mario on 03/06/2017.
 */

public class GameOverStage extends Stage {

    private BubbleRunner game;
    private AssetsManager assetsManager;
    private OverTableActor overTableActor;
    private ScoreManager scoreManager;
    private Highscores highscores;
    private Map<Integer, String > allScores;
    private int finalScore;
    private boolean register;

    public GameOverStage(BubbleRunner game, int finalScore) {

        //screen setup
        this.game = game;
        this.finalScore = finalScore;
        this.scoreManager = this.game.getScoreManager();
        this.highscores = scoreManager.getHighscores();
        this.allScores = highscores.getMap();
        this.assetsManager = assetsManager.getInstance();
        this.register = checkMinimum();

        setViewport(new FitViewport(VIEWPORT_WIDTH/(20*PIXEL_TO_METER), VIEWPORT_WIDTH/(20*PIXEL_TO_METER) * RATIO));


        this.overTableActor = new OverTableActor(assetsManager,register);
        this.addActor(overTableActor);


        Gdx.input.setInputProcessor(this);
    }

    public boolean checkMinimum(){
        if(allScores.size() < HIGHSCORES_MAX_PLAYERS)
            return true;

        for (Map.Entry<Integer, String> entry : allScores.entrySet()) {
            if(entry.getKey() > finalScore){
                return true;
            }
        }

        return false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (overTableActor.getConfirmPressed()) {
            if(register){
                highscores.updateHighscores(overTableActor.getName(),finalScore);
                try {
                    scoreManager.saveHighscores();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            game.setScreen(new GenericScreen(game, new MenuStage(game)));
        }
    }
}
