package com.bubblerunner.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.actors.GenericButton;
import com.bubblerunner.game.actors.HighscoresTableActor;
import com.bubblerunner.game.screens.MenuScreen;
import com.bubblerunner.game.utils.gui.GameGraphics;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.constants.Constants.SCREEN_WIDTH;

/**
 * Created by Mario on 22/05/2017.
 */

public class HighscoresStage extends Stage {

    static final int VIEWPORT_WIDTH = 400;
    private Table backTable;
    private GenericButton backButton;
    private BubbleRunner game;
    private GraphicsManager graphicsManager;
    private HighscoresTableActor highscoresTableActor;

    public HighscoresStage(BubbleRunner game){
        this.game = game;
        //this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
        //float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_WIDTH * ratio));

        this.graphicsManager = graphicsManager.getInstance();

        this.highscoresTableActor = new HighscoresTableActor(graphicsManager);
        this.addActor(highscoresTableActor);

        this.backButton = new GenericButton(graphicsManager.hud.rightButton);
        this.backTable = new Table();
        this.backTable.left().bottom();
        this.backTable.add(this.backButton).size(80, 80);
        this.addActor(backTable);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        if(backButton.getPressed()){
            game.setScreen(new MenuScreen(game));
        }
    }
}
