package com.bubblerunner.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.bubblerunner.game.screens.BubbleScreen;
import com.bubblerunner.game.screens.MenuScreen;
import com.bubblerunner.game.utils.gui.GraphicsManager;

public class BubbleRunner extends Game {

	private GraphicsManager graphicsManager;
	public GraphicsManager getGraphicsManager() {return graphicsManager;}

	@Override
	public void create () {
		graphicsManager = graphicsManager.getInstance();
		this.setScreen(new MenuScreen(this));
		//this.setScreen(new BubbleScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}


}
