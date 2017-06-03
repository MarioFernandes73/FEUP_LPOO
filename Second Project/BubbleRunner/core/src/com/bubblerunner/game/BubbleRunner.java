package com.bubblerunner.game;

import com.badlogic.gdx.Game;
import com.bubblerunner.game.screens.GenericScreen;
import com.bubblerunner.game.stages.MenuStage;
import com.bubblerunner.game.utils.ScoreManager;

import java.io.IOException;

public class BubbleRunner extends Game {

	private ScoreManager scoreManager;

	@Override
	public void create () {
		//this.setScreen(new MenuScreen(this));
		//this.setScreen(new BubbleScreen(this));

		this.setScreen(new GenericScreen(this, new MenuStage(this)));
		scoreManager = new ScoreManager();

	}

	@Override
	public void pause(){
		super.pause();
		saveHighscores();
	}

	@Override
	public void dispose(){
		super.dispose();
		saveHighscores();
	}

	private void saveHighscores(){
		try {
			scoreManager.saveHighscores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ScoreManager getScoreManager(){
		return this.scoreManager;
	}

}
