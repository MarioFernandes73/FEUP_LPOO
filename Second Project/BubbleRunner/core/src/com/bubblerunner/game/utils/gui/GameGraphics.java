package com.bubblerunner.game.utils.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Mario on 08/05/2017.
 */

//Singleton implementation
public class GameGraphics {

    private static GameGraphics instance = new GameGraphics();
    public Texture ball;
    public Texture ledge;

    private GameGraphics() {
        AssetManager manager = new AssetManager();
        loadAssets(manager);
        assignAssets(manager);
    }

    public void loadAssets(AssetManager manager){
        manager.load("ball.png", Texture.class);
        manager.load("ground.png", Texture.class);
        manager.finishLoading();
    }

    public void assignAssets(AssetManager manager){
        this.ball = manager.get("ball.png");
        this.ledge = manager.get("ground.png");
    }

    public static GameGraphics getInstance() {
        return instance;
    }
}
