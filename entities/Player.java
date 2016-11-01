package com.game.weaponsoftime.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.util.Collision;
import com.game.weaponsoftime.util.Vec2f;

public class Player extends Mob {

	public Player(float x, float y, float width, float height, Texture sprite) {
		super(x, y, width, height, sprite);

	}

	float speed = 2;
	Vec2f vel = new Vec2f(0, 0);

	public void update() {
		control();

	}

	@Override
	public void render() {
		Renderer.renderTexture(Textures.spriteTest, spriteBounds.x, spriteBounds.y, spriteBounds.width, spriteBounds.height);
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
		
		
		if (vel.length() > 0)
			vel = vel.norm().mul(speed);

		if (!Collision.checkWalls(this, vel.x, 0))
			move(vel.x, 0);
		if (!Collision.checkWalls(this, 0, vel.y))
			move(0, vel.y);

	}

}
