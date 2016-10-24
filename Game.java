package com.game.weaponsoftime;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;

	Texture knight;

	@Override
	public void create() {

		knight = new Texture(Gdx.files.internal("knight.png"));
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1f, 0f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(knight, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();

	}
}
