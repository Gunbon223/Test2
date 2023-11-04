package com.gunbon.game;

import GameState.MenuState;
import GameState.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FlappyGame extends ApplicationAdapter {

	public static final String TITLE = "FlappyBird";
	private StateManager stateManager;
	Texture img;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager = new StateManager();
		img = new Texture("badlogic.jpg");
		stateManager.push(new MenuState(stateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //wipe screen and draw again
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
