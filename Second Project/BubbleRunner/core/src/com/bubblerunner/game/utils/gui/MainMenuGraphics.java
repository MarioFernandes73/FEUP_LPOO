package com.bubblerunner.game.utils.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Mario on 15/05/2017.
 */

public class MainMenuGraphics {

    private static MainMenuGraphics instance = new MainMenuGraphics();
    public TextureAtlas atlas;

    private MainMenuGraphics() {}

    public void loadAssets(AssetManager manager){

    }

    public void assignAssets(AssetManager manager){

    }

    public static MainMenuGraphics getInstance() {
        return instance;
    }
}
