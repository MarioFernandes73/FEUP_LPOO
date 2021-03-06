package com.bubblerunner.game.transitions.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.transitions.actors.MenuTableActor;
import com.bubblerunner.game.transitions.screens.BubbleScreen;
import com.bubblerunner.game.transitions.screens.GenericScreen;
import com.bubblerunner.game.utils.AssetsManager;

import static com.bubblerunner.game.utils.Constants.SCREEN_HEIGHT;
import static com.bubblerunner.game.utils.Constants.SCREEN_WIDTH;

public class MenuStage extends Stage {

    private BubbleRunner game;
    private AssetsManager assetsManager;
    private MenuTableActor menuTableActor;

    public MenuStage(BubbleRunner game) {

        //screen setup
        this.game = game;
        this.setViewport(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.assetsManager = assetsManager.getInstance();

        this.menuTableActor = new MenuTableActor(assetsManager);
        this.addActor(menuTableActor);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (menuTableActor.getPlayPressed()) {
            game.setScreen(new BubbleScreen(game));
        } else if (menuTableActor.getHighscoresPressed()) {
            game.setScreen(new GenericScreen(game, new HighscoresStage(game)));
        } else if (menuTableActor.getExitPressed()) {
            Gdx.app.exit();
        }

    }


}
