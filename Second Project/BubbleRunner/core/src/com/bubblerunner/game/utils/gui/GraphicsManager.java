package com.bubblerunner.game.utils.gui;

/**
 * Created by Mario on 08/05/2017.
 */

//Singleton implementation
public class GraphicsManager {

    public GameGraphics gameGraphics;
    public HUD hud;

    private static GraphicsManager instance = new GraphicsManager();

    private GraphicsManager(){
        this.gameGraphics = GameGraphics.getInstance();
        this.hud = HUD.getInstance();
    }

    public static GraphicsManager getInstance(){
        return instance;
    }
}
