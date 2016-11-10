package com.lipkill.game;

import state.Play;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainActivity extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Play(this));
	}

	@Override
	public void render () {
	super.render();
	}
}
