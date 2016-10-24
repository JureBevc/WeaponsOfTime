package com.game.weaponsoftime.util;

public class Vec2f {

	float x, y;

	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2f add(Vec2f v) {
		return new Vec2f(x + v.x, y + v.y);
	}

	public Vec2f sub(Vec2f v) {
		return new Vec2f(x - v.x, y - v.y);
	}

	public Vec2f mul(float m) {
		return new Vec2f(m * x, m * y);
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float dot(Vec2f v) {
		return (x * v.x + y * v.y);
	}

	public double angle(Vec2f v) {
		return Math.acos(dot(v) / (length() * v.length()));
	}
}
