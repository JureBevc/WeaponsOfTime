package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.graphics.Animation;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.util.Collision;
import com.game.weaponsoftime.util.Vec2f;

public class Rat extends Mob {

	public Rat(float x, float y, float width, float height, TextureRegion sprite) {
		super(x, y, width, height, sprite);
		runningAnimation = new Animation(Textures.ratRunning, 7, 1, 3, this);
		idleAnimation = new Animation(Textures.ratRunning, 7, 1, 0, this);
		maxHP = hp = 5;

		speed = 2.2f;
	}

	public void update() {
		moveToPlayer();
		updateAnimation();
	}

	public void render() {
		runningAnimation.renderAnimation(spriteDirection);
	}

	Vec2f toPlayer = null; // Vector that points to player

	public void moveToPlayer() {
		toPlayer = Game.level.player.pos.sub(this.pos);
		if (toPlayer.length() > 25 && toPlayer.length() < 200) {
			vel = toPlayer.norm().mul(speed);

			if (!Collision.checkWalls(this, vel.x, 0) && !Collision.checkMobs(this, vel.x, 0))
				move(vel.x, 0);
			if (!Collision.checkWalls(this, 0, vel.y) && !Collision.checkMobs(this, 0, vel.y))
				move(0, vel.y);
		}

	}
}
