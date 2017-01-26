package com.game.weaponsoftime.entities;

import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;

public class Rat extends Mob {

	public Rat(float x, float y, float width, float height) {
		super(x, y, width, height);
		setTexture(Textures.rat);
	}

	public void update() {

	}

	public void render() {
		if (texture != null)
			Renderer.renderTextureRegion(texture, getBounds(), false);
	}

}
