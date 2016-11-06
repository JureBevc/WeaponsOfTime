package com.game.weaponsoftime.entities;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.graphics.Animation;
import com.game.weaponsoftime.util.Vec2f;

public abstract class GameObject {
	public Vec2f pos, size;
	public Rectangle bounds;
	public Rectangle spriteBounds;
	public Animation animation;
	public Animation idleAnimation;

	public GameObject(float x, float y, float width, float height) {
		pos = new Vec2f(x, y);
		bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
		spriteBounds = bounds;
		animation = null;
		idleAnimation = null;
	}

	public abstract void render();
}
