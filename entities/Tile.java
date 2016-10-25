package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.Color;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;

public class Tile extends Entity {

	public boolean solid = false;

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);

	}

	@Override
	public void render() {
		if (solid)
			Renderer.renderRect(bounds, new Color(0.3f, 0.3f, 0.3f, 1));
		else
			Renderer.renderTexture(Textures.emptyTile, bounds.x, bounds.y, bounds.width, bounds.height);
	}

}
