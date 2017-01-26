package com.game.weaponsoftime.entities;

import com.badlogic.gdx.Input.Keys;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.util.Vec2f;

public class Player extends Mob {

	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void update() {
		control();
	}

	@Override
	public void render() {
		if (texture != null)
			Renderer.renderTextureRegion(texture, getBounds(), spriteDirection);
	}

	Vec2f vel = new Vec2f(0, 0);

	public void control() {
		vel = vel.mul(0.9f);

		if (vel.length() < 0.1)
			vel = new Vec2f(0, 0);
		if (Game.input.keys[Keys.A])
			vel = vel.add(new Vec2f(-speed, 0));
		if (Game.input.keys[Keys.D])
			vel = vel.add(new Vec2f(speed, 0));
		if (Game.input.keys[Keys.W])
			vel = vel.add(new Vec2f(0, speed));
		if (Game.input.keys[Keys.S])
			vel = vel.add(new Vec2f(0, -speed));

		if (vel.length() > speed)
			vel = vel.norm().mul(speed);

		if (vel.x != 0)
			spriteDirection = (vel.x < 0);

		move(vel);
	}

}
