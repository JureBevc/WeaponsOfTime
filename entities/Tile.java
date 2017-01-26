package com.game.weaponsoftime.entities;

import com.game.weaponsoftime.graphics.Renderer;

public class Tile extends GameObject {

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void render() {
		if (texture != null)
			Renderer.renderTextureRegion(texture, getBounds(), false);
	}

}
