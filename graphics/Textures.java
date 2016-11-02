package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	// Tiles
	public static TextureRegion emptyTile, spriteTest, cobbleSheet, cobbleWallSheet, border, levelEntrance,
			animationTest;

	public static void init() {
		emptyTile = new TextureRegion(new Texture(Gdx.files.internal("emptyTile.png")));
		spriteTest = new TextureRegion(new Texture(Gdx.files.internal("character.png")));
		cobbleSheet = new TextureRegion(new Texture(Gdx.files.internal("cobbleSheet.png")));
		cobbleWallSheet = new TextureRegion(new Texture(Gdx.files.internal("cobbleWallSheet.png")));
		border = new TextureRegion(new Texture(Gdx.files.internal("border.png")));
		levelEntrance = new TextureRegion(new Texture(Gdx.files.internal("levelEntrance.png")));
		animationTest = new TextureRegion(new Texture(Gdx.files.internal("animationTest.png")));

	}
}
