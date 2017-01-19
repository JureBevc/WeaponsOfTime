package com.game.weaponsoftime.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.graphics.Animation;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.util.Collision;
import com.game.weaponsoftime.util.Vec2f;

public class Player extends Mob {

	public Player(float x, float y, float width, float height, TextureRegion sprite) {
		super(x, y, width, height, sprite);

		runningAnimation = new Animation(Textures.knightRunning, 14, 1,
				(int) ((60 / 1000.0) * (60 - 20 * vel.length() / speed)), this);

		idleAnimation = new Animation(Textures.knightIdle, 12, 1, (int) (60 / 1000.0 * 150), this);

		weaponRunningAnimation = new Animation(Textures.swordRunning, 14, 1, runningAnimation.interval, this);
		weaponRunningAnimation.isWeapon();
		weaponIdleAnimation = new Animation(Textures.swordRunning, 14, 1, 0, this);
		weaponIdleAnimation.isWeapon();
		maxHP = hp = 50;

		speed = 2;
	}

	public void update() {
		control();
		runningAnimation.interval = (int) ((60 / 1000.0) * (60 - 20 * vel.length() / speed));
		weaponRunningAnimation.interval = runningAnimation.interval;
		updateAnimation();

	}

	@Override
	public void render() {
		renderAnimation();
	}

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

		if (vel.length() > speed) {

			vel = vel.norm().mul(speed);
		}
		if (vel.x != 0)
			spriteDirection = (vel.x < 0);

		if (!Collision.checkWalls(this, vel.x, 0))
			move(vel.x, 0);
		if (!Collision.checkWalls(this, 0, vel.y))
			move(0, vel.y);

	}

}
