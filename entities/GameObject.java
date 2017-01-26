package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.util.Vec2f;

public abstract class GameObject {

	public Vec2f pos, size; // position is the center

	public boolean solid = false; // Does it collide

	TextureRegion texture = null; // Static texture, can be null or set later

	public GameObject(Vec2f pos, Vec2f size) {
		this.pos = pos;
		this.size = size;
	}

	// Second constructor, mainly for tiles
	public GameObject(float x, float y, float width, float height) {
		pos = new Vec2f(x + width / 2, y + height / 2);
		size = new Vec2f(width, height);
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public Rectangle getBounds() {
		/* Get bounds of object, useful for collision */
		return new Rectangle(this.pos.x - this.size.x / 2, this.pos.y - this.size.y / 2, this.size.x, this.size.y);
	}

	public abstract void render();

}
