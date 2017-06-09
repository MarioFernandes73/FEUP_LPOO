package com.bubblerunner.game.utils.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Mario on 08/05/2017.
 */

//Singleton implementation
public class GraphicsManager {

    private AssetManager assetManager;
    public Texture ball;
    public Texture flyingBall;
    public Texture normalLedge;
    public Texture spikedLedge;
    public Texture leftButton;
    public Texture rightButton;
    public Texture playButton;
    public Texture highscoresButton;
    public Texture exitButton;
    public Texture confirmButton;
    public Texture spikedLedge2;

    private static GraphicsManager instance = new GraphicsManager();

    private GraphicsManager(){
        assetManager = new AssetManager();
        assetManager.update();
        loadAssets();
        assignAssets();
    }

    private void loadAssets(){
        assetManager.load("ballAnimation.png", Texture.class);
        assetManager.load("flyingAnimation.png", Texture.class);
        assetManager.load("normal.png", Texture.class);
        assetManager.load("spiked.png", Texture.class);
        assetManager.load("left.png", Texture.class);
        assetManager.load("right.png", Texture.class);
        assetManager.load("playButton.png", Texture.class);
        assetManager.load("highscoresButton.png", Texture.class);
        assetManager.load("exitButton.png", Texture.class);
        assetManager.load("confirmButton.png", Texture.class);
        assetManager.load("spiked2.png", Texture.class);
        assetManager.finishLoading();
    }

    private void assignAssets(){
        this.ball = assetManager.get("ballAnimation.png");
        this.flyingBall = assetManager.get("flyingAnimation.png");
        this.normalLedge = assetManager.get("normal.png");
        this.spikedLedge = assetManager.get("spiked.png");
        this.leftButton = assetManager.get("left.png");
        this.rightButton = assetManager.get("right.png");
        this.playButton = assetManager.get("playButton.png");
        this.highscoresButton = assetManager.get("highscoresButton.png");
        this.exitButton = assetManager.get("exitButton.png");
        this.confirmButton = assetManager.get("confirmButton.png");
        this.spikedLedge2 = assetManager.get("spiked2.png");
    }

    public void dispose(){
        assetManager.dispose();
    }

    public static GraphicsManager getInstance(){
        return instance;
    }
}
