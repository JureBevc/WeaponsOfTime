package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	// Tiles
	public static TextureRegion knightRunning, knightIdle, entrance, exit, floor_wood, floor_wood_birch, floor_grass,
			wall_panel, wall_panel2, ratRunning, swordRunning, black_tile;
	public static TextureRegion DEBUG;

	public static void init() {

		floor_wood = new TextureRegion(new Texture(Gdx.files.internal("floor_wood.png")));
		floor_wood_birch = new TextureRegion(new Texture(Gdx.files.internal("floor_grass.png")));
		floor_grass = new TextureRegion(new Texture(Gdx.files.internal("floor_grass.png")));
		wall_panel = new TextureRegion(new Texture(Gdx.files.internal("wall_panel.png")));
		wall_panel2 = new TextureRegion(new Texture(Gdx.files.internal("wall_panel2.png")));
		entrance = new TextureRegion(new Texture(Gdx.files.internal("entrance.png")));
		exit = new TextureRegion(new Texture(Gdx.files.internal("exit.png")));
		knightRunning = new TextureRegion(new Texture(Gdx.files.internal("knightRunning.png")));
		knightIdle = new TextureRegion(new Texture(Gdx.files.internal("knightIdle.png")));
		ratRunning = new TextureRegion(new Texture(Gdx.files.internal("ratRunning.png")));
		swordRunning = new TextureRegion(new Texture(Gdx.files.internal("swordRunning.png")));
		black_tile = new TextureRegion(new Texture(Gdx.files.internal("black_tile.png")));
		//
		DEBUG = new TextureRegion(new Texture(Gdx.files.internal("DEBUG.png")));

	}
}
