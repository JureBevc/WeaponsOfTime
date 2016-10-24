package com.game.weaponsoftime;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.level.LevelGenerator;
import com.game.weaponsoftime.util.Input;

public class Game extends ApplicationAdapter {

	int WIDTH = 1280, HEIGHT = 720;

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
		levelGenerator.createMap(3, 3);

		Renderer.init();

	}

	public void update() {
		if (input.keys[Keys.A])
			camOffX--;
		if (input.keys[Keys.D])
			camOffX++;
		if (input.keys[Keys.W])
			camOffY++;
		if (input.keys[Keys.S])
			camOffY--;
	}

	int camOffX = 0, camOffY = 0;

	@Override
	public void render() {
		update();

		Gdx.gl.glClearColor(1f, 0f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Renderer.translate(camOffX, camOffY);

		Renderer.renderTexture(Textures.emptyTile, 50, 50, 50, 50);
		Renderer.renderRect(new Rectangle(50, 100, 50, 50), new Color(0, 0, 1, 1));

	}

	@Override
	public void dispose() {
		batch.dispose();

	}
}
