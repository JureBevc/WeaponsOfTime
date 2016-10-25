package com.game.weaponsoftime;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.weaponsoftime.entities.EntityManager;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.level.LevelGenerator;
import com.game.weaponsoftime.util.Input;

public class Game extends ApplicationAdapter {

	SpriteBatch batch;
	Input input;

	LevelGenerator levelGenerator;

	@Override
	public void create() {

		batch = new SpriteBatch();

		input = new Input();
		Gdx.input.setInputProcessor((InputProcessor) input);

		Textures.init();

		levelGenerator = new LevelGenerator();
		levelGenerator.createMap(50, 50);

		Renderer.init();

	}

	int scrollSpeed = 5;

	boolean temp = false;

	public void update() {
		if (input.keys[Keys.A])
			Renderer.camOffX -= scrollSpeed;
		if (input.keys[Keys.D])
			Renderer.camOffX += scrollSpeed;
		if (input.keys[Keys.W])
			Renderer.camOffY += scrollSpeed;
		if (input.keys[Keys.S])
			Renderer.camOffY -= scrollSpeed;

		if (input.keys[Keys.G]) {
			if (!temp) {
				levelGenerator.createMap(100, 100);
				temp = true;
			}
		} else {
			temp = false;
		}
	}

	@Override
	public void render() {
		update();

		Gdx.gl.glClearColor(1f, 0f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Renderer.translate(Renderer.camOffX, Renderer.camOffY);

		EntityManager.renderEntities();

	}

	@Override
	public void dispose() {
		batch.dispose();

	}
}
