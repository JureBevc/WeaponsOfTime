package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {
	// Tiles
	public static Texture emptyTile;

	public static void init() {
		emptyTile = new Texture(Gdx.files.internal("emptyTile.png"));
	}
}
