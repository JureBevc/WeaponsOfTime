package com.game.weaponsoftime.entities;

import com.game.weaponsoftime.util.Vec2f;

public abstract class Entity {

	public Vec2f pos, size;

	public Entity(Vec2f pos, Vec2f size) {
		this.pos = pos;
		this.size = size;
	}


	public abstract void render();
}
