package com.gunbon.game;

import GameState.MenuState;
import GameState.StateManager;
import Utility.Constrain;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;

public class FlappyGame extends ApplicationAdapter {

	private StateManager stateManager;
	private SpriteBatch batch;
	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager = new StateManager();
		stateManager.push(new MenuState(stateManager));
		music = Gdx.audio.newMusic(Gdx.files.internal("bgpiano.mp3"));
		music.setLooping(true);
		music.setVolume(0.3f);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,1,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
	}
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
