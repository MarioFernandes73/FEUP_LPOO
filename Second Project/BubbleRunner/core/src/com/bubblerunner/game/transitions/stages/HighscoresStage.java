package com.bubblerunner.game.transitions.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.transitions.actors.GenericButton;
import com.bubblerunner.game.transitions.actors.HighscoresTableActor;
import com.bubblerunner.game.transitions.screens.GenericScreen;
import com.bubblerunner.game.utils.AssetsManager;

/**
 * Created by Mario on 22/05/2017.
 */

public class HighscoresStage extends Stage {

    static final int VIEWPORT_WIDTH = 400;
    private Table backTable;
    private GenericButton backButton;
    private BubbleRunner game;
    private AssetsManager assetsManager;
    private HighscoresTableActor highscoresTableActor;

    public HighscoresStage(BubbleRunner game){
        this.game = game;
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_WIDTH * ratio));

        this.assetsManager = assetsManager.getInstance();

        this.highscoresTableActor = new HighscoresTableActor(game.getScoreManager().displayHighscores());
        this.addActor(highscoresTableActor);

        this.backButton = new GenericButton(assetsManager.leftButton);
        this.backTable = new Table();
        this.backTable.left().bottom();
        this.backTable.add(this.backButton).size(80, 80);
        this.addActor(backTable);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        if(backButton.getPressed()){
            game.setScreen(new GenericScreen(game, new MenuStage(game)));
        }
    }
}
