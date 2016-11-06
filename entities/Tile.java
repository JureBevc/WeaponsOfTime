package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;

public class Tile extends GameObject {

	public boolean solid;
	public TextureRegion texture;

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);
		solid = false;
		texture = new TextureRegion(Textures.emptyTile);

	}

	@Override
	public void render() {
		Renderer.renderTextureRegion(texture, bounds.x, bounds.y, bounds.width, bounds.height, false);
		if (Game.level.entrance.equals(this) && animation != null) {
			animation.renderAnimation(false);
		}
	}

}
