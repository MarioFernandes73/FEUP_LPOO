package com.bubblerunner.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.actors.MenuTableActor;
import com.bubblerunner.game.screens.BubbleScreen;
import com.bubblerunner.game.screens.HighscoresScreen;
import com.bubblerunner.game.utils.gui.GraphicsManager;

import static com.bubblerunner.game.constants.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.constants.Constants.SCREEN_WIDTH;

/**
 * Created by Mario on 08/05/2017.
 */

public class MenuStage extends Stage {

    private BubbleRunner game;
    private GraphicsManager graphicsManager;
    private MenuTableActor menuTableActor;

    public MenuStage (BubbleRunner game){

        //screen setup
        this.game = game;
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.graphicsManager = graphicsManager.getInstance();

        this.menuTableActor = new MenuTableActor(graphicsManager);
        this.addActor(menuTableActor);

    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        if (menuTableActor.getPlayPressed()) {
            game.setScreen(new BubbleScreen(game));
        } else if(menuTableActor.getHighscoresPressed()) {
            game.setScreen(new HighscoresScreen(game));
        } else if(menuTableActor.getExitPressed()) {
            Gdx.app.exit();
        }

    }


}
