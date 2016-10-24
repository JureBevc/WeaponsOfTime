package com.game.weaponsoftime;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.weaponsoftime.entities.EntityManager;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.util.Input;
import com.game.weaponsoftime.util.Textures;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;

	Texture knight;

	Input input;

	@Override
	public void create() {

		knight = new Texture(Gdx.files.internal("knight.png"));
		batch = new SpriteBatch();
		input = new Input();
		Textures.init();
		Gdx.input.setInputProcessor((InputProcessor) input);
		EntityManager.entities.add(new Tile(50, 50, 50, 50));
	}

	public void update() {

	}

	@Override
	public void render() {
		update();
		System.out.println(input.keys[Keys.A]);
		Gdx.gl.glClearColor(1f, 0f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		EntityManager.renderEntities(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();

	}
}
