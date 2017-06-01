package com.bubblerunner.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.bubblerunner.game.screens.BubbleScreen;
import com.bubblerunner.game.screens.MenuScreen;
import com.bubblerunner.game.utils.gui.GraphicsManager;

public class BubbleRunner extends Game {

	@Override
	public void create () {
		this.setScreen(new MenuScreen(this));
		//this.setScreen(new BubbleScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}


}
