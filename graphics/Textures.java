package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	// Tiles
	public static TextureRegion black_tile, floor_wood, wall_wood;

	// Mobs
	public static TextureRegion knight, rat;

	// Debug
	public static TextureRegion DEBUG;

	public static void init() {

		// Tiles
		black_tile = new TextureRegion(new Texture(Gdx.files.internal("black_tile.png")));

		floor_wood = new TextureRegion(new Texture(Gdx.files.internal("floor_wood.png")));
		wall_wood = new TextureRegion(new Texture(Gdx.files.internal("wall_wood.png")));

		// Mobs
		rat = new TextureRegion(new Texture(Gdx.files.internal("rat.png")));
		knight = new TextureRegion(new Texture(Gdx.files.internal("knight.png")));

		// Debug
		DEBUG = new TextureRegion(new Texture(Gdx.files.internal("DEBUG.png")));

	}
}
