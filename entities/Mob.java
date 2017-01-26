package com.game.weaponsoftime.entities;

import com.game.weaponsoftime.util.Collision;
import com.game.weaponsoftime.util.Vec2f;

public class Mob extends GameObject {

	boolean spriteDirection = false; // true - left, false - right

	// Attributes of every mob
	public int hp = 1;
	public float speed = 1;

	public Mob(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public Mob(Vec2f pos, Vec2f size) {
		super(pos, size);
	}

	public void update() {

	}

	@Override
	public void render() {

	}

	public void move(Vec2f vel) {
		pos = pos.add(vel);
		if (Collision.checkWalls(this) || Collision.checkMobs(this))
			pos = pos.sub(vel);
	}

}
