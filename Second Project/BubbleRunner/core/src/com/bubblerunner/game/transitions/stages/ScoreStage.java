package com.bubblerunner.game.transitions.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;

public class ScoreStage extends Stage {

    static final int VIEWPORT_WIDTH = 400;
    private final Label scoreLabel;
    private int currentScore;

    public ScoreStage(BubbleRunner game) {

        this.currentScore = 0;
        // Set the viewport
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_WIDTH * ratio));

        scoreLabel = new Label("0", new Label.LabelStyle(new BitmapFont(), null));
        scoreLabel.setPosition(10, VIEWPORT_WIDTH * ratio - 30);
        scoreLabel.setColor(Color.WHITE);
        addActor(scoreLabel);
    }

    public void addScore(int score) {
        currentScore += score;
        scoreLabel.setText("" + currentScore);
    }

    public int getCurrentScore() {
        return this.currentScore;
    }
}
