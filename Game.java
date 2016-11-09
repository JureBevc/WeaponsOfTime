package com.game.weaponsoftime;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.graphics.AnimationManager;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.level.Level;
import com.game.weaponsoftime.level.LevelGenerator;
import com.game.weaponsoftime.util.Input;

public class Game extends ApplicationAdapter {

	public static Input input;

	public static LevelGenerator levelGenerator;
	public static Level level;

	public static AnimationManager animationManager;

	@Override
	public void create() {
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

		input = new Input();
		Gdx.input.setInputProcessor((InputProcessor) input);

		Textures.init();
		animationManager = new AnimationManager();

		level = new Level();
		levelGenerator = new LevelGenerator(level);
		levelGenerator.createMap(50, 50);

		Renderer.init();
		Renderer.zoomCam(Renderer.SCALE);

	}

	int scrollSpeed = 5;

	boolean temp = false;

	public void update() {

		level.updateLevel();
		animationManager.update();
	}

	@Override
	public void render() {
		update();

		Gdx.gl.glClearColor(0.1f, 0f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Renderer.moveCam(level.player.pos.x, level.player.pos.y);
		Renderer.sb.begin();
		level.renderLevel();
		animationManager.render();

		Renderer.renderTextureRegion(Textures.emptyTile, new Rectangle(input.x, input.y, 10, 10), false);
		Renderer.sb.end();

	}

	@Override
	public void dispose() {
		Renderer.sb.dispose();

	}
}
