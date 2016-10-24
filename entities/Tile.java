package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;

public class Tile extends Entity {

	public boolean solid = false;

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);

	}

	ShapeRenderer sr = new ShapeRenderer(); // TODO: Remove after debug

	@Override
	public void render(SpriteBatch batch) {
		if (solid)
			Renderer.renderRect(bounds, new Color(0.3f, 0.3f, 0.3f, 1));
		else
			Renderer.renderTexture(Textures.emptyTile, bounds.x, bounds.y, bounds.width, bounds.height);
	}

}
