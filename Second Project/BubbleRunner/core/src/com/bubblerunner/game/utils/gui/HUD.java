package com.bubblerunner.game.utils.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Mario on 08/05/2017.
 */

//Singleton implementation
public class HUD {

    private static HUD instance = new HUD();
    public Texture leftButton;
    public Texture rightButton;

    private HUD() {
        AssetManager manager = new AssetManager();
        loadAssets(manager);
        assignAssets(manager);
    }

    public void loadAssets(AssetManager manager){
        manager.load("left.png", Texture.class);
        manager.load("right.png", Texture.class);
        manager.finishLoading();
    }

    public void assignAssets(AssetManager manager){
        this.leftButton = manager.get("left.png");
        this.rightButton = manager.get("right.png");
    }

    public static HUD getInstance() {
        return instance;
    }
}