package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	// Tiles
	public static TextureRegion emptyTile, cobbleSheet, cobbleWallSheet, border, knightRunning, knightIdle, levelPortal;

	public static void init() {
		emptyTile = new TextureRegion(new Texture(Gdx.files.internal("emptyTile.png")));
		cobbleSheet = new TextureRegion(new Texture(Gdx.files.internal("cobbleSheet.png")));
		cobbleWallSheet = new TextureRegion(new Texture(Gdx.files.internal("cobbleWallSheet.png")));
		border = new TextureRegion(new Texture(Gdx.files.internal("border.png")));
		levelPortal = new TextureRegion(new Texture(Gdx.files.internal("levelPortal.png")));
		knightRunning = new TextureRegion(new Texture(Gdx.files.internal("knightRunning.png")));
		knightIdle = new TextureRegion(new Texture(Gdx.files.internal("knightIdle.png")));

	}
}
