package com.game.weaponsoftime.entities;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.util.Vec2f;

public abstract class GameObject {

	public Vec2f pos, size;
	public Rectangle bounds;

	public GameObject(float x, float y, float width, float height) {
		pos = new Vec2f(x, y);
		bounds = new Rectangle(x - width / 2, y - height / 2, width, height);

	}

	public abstract void render();
}
