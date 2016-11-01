package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {
	// Tiles
	public static Texture emptyTile, spriteTest, cobbleSheet, cobbleWallSheet, border;

	public static void init() {
		emptyTile = new Texture(Gdx.files.internal("emptyTile.png"));
		spriteTest = new Texture(Gdx.files.internal("character.png"));
		cobbleSheet = new Texture(Gdx.files.internal("cobbleSheet.png"));
		cobbleWallSheet = new Texture(Gdx.files.internal("cobbleWallSheet.png"));
		border = new Texture(Gdx.files.internal("border.png"));

	}
}
