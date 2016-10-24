package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.util.Vec2f;

public abstract class Entity {

	public Vec2f pos, size;
	Rectangle bounds;

	public Entity(float x, float y, float width, float height) {
		pos = new Vec2f(x, y);
		bounds = new Rectangle(x - width / 2, y - height / 2, width, height);

	}

	public abstract void render(SpriteBatch batch);
}
