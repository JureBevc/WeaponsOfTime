package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Mob extends GameObject {

	TextureRegion sprite;

	public Mob(float x, float y, float width, float height, TextureRegion sprite) {
		super(x, y, width, height);
		this.sprite = sprite;
		spriteBounds = new Rectangle(pos.x - sprite.getRegionWidth(), pos.y - sprite.getRegionHeight(),
				sprite.getRegionWidth() * 2, sprite.getRegionHeight() * 2);
		bounds = new Rectangle(pos.x - width / 2, spriteBounds.y, width, height);
	}

	public void update() {
	}

	@Override
	public void render() {

	}

	public void move(float x, float y) {
		pos.x += x;
		pos.y += y;

		spriteBounds = new Rectangle(pos.x - spriteBounds.width / 2, pos.y - spriteBounds.height / 2,
				spriteBounds.width, spriteBounds.height);
		bounds = new Rectangle(pos.x - bounds.width / 2, spriteBounds.y, bounds.width, bounds.height);

	}

}
