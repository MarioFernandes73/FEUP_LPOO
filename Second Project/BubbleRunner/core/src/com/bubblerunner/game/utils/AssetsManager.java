package com.bubblerunner.game.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Mario on 08/05/2017.
 */

//Singleton implementation
public class AssetsManager {

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
    public Music music;
    public Texture menuBanner;
    public Texture highscoresBanner;
    public Texture gameOverBanner;

    private static AssetsManager instance = new AssetsManager();

    private AssetsManager(){
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
        assetManager.load("music.mp3",Music.class);
        assetManager.load("bubbleRunner.png", Texture.class);
        assetManager.load("congratulations.png", Texture.class);
        assetManager.load("gameOver.png", Texture.class);
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
        this.music = assetManager.get("music.mp3");
        this.menuBanner = assetManager.get("bubbleRunner.png", Texture.class);
        this.highscoresBanner = assetManager.get("congratulations.png", Texture.class);
        this.gameOverBanner = assetManager.get("gameOver.png", Texture.class);
    }

    public void dispose(){
        assetManager.dispose();
    }

    public static AssetsManager getInstance(){
        return instance;
    }
}
