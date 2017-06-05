package com.bubblerunner.game.transitions.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.transitions.actors.OverTableActor;
import com.bubblerunner.game.transitions.screens.GenericScreen;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.PIXEL_TO_METER;
import static com.bubblerunner.game.constants.Constants.RATIO;
import static com.bubblerunner.game.constants.Constants.VIEWPORT_WIDTH;

/**
 * Created by Mario on 03/06/2017.
 */

public class GameOverStage extends Stage {

    private BubbleRunner game;
    private GraphicsManager graphicsManager;
    private OverTableActor overTableActor;

    public GameOverStage(BubbleRunner game) {

        //screen setup
        this.game = game;
        setViewport(new FitViewport(VIEWPORT_WIDTH/(20*PIXEL_TO_METER), VIEWPORT_WIDTH/(20*PIXEL_TO_METER) * RATIO));
        this.graphicsManager = graphicsManager.getInstance();

        this.overTableActor = new OverTableActor(graphicsManager);
        this.addActor(overTableActor);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (overTableActor.getConfirmPressed()) {
            game.setScreen(new GenericScreen(game, new MenuStage(game)));
        }
    }
}
