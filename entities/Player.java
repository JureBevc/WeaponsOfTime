package com.game.weaponsoftime.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.graphics.Animation;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.util.Collision;
import com.game.weaponsoftime.util.Vec2f;

public class Player extends Mob {

	public Player(float x, float y, float width, float height, TextureRegion sprite) {
		super(x, y, width, height, sprite);
		Game.animationManager.animations.add(new Animation(Textures.animationTest, 8, 3, 2, this));

	}

	float speed = 2;
	Vec2f vel = new Vec2f(0, 0);

	public void update() {
		control();

	}

	boolean spriteDirection = false; // False = Right, True = Left

	@Override
	public void render() {

		Renderer.renderTextureRegion(Textures.spriteTest, spriteBounds.x, spriteBounds.y, spriteBounds.width,
				spriteBounds.height, spriteDirection);
		Renderer.renderRect(new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height), new Color(1, 0, 1, 1));
	}

	public void control() {
		vel = new Vec2f(0, 0);

		if (Game.input.keys[Keys.A])
			vel = vel.add(new Vec2f(-speed, 0));
		if (Game.input.keys[Keys.D])
			vel = vel.add(new Vec2f(speed, 0));
		if (Game.input.keys[Keys.W])
			vel = vel.add(new Vec2f(0, speed));
		if (Game.input.keys[Keys.S])
			vel = vel.add(new Vec2f(0, -speed));

		if (vel.length() > 0) {
			vel = vel.norm().mul(speed);
			if (vel.x != 0)
				spriteDirection = (vel.x < 0);
		}

		if (!Collision.checkWalls(this, vel.x, 0))
			move(vel.x, 0);
		if (!Collision.checkWalls(this, 0, vel.y))
			move(0, vel.y);

	}

}
