package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.weaponsoftime.util.Textures;

public class Tile extends Entity {

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Textures.emptyTile, bounds.x, bounds.y, bounds.width, bounds.height);
	}

}
