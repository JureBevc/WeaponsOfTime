package com.game.weaponsoftime.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.util.Vec2f;

public class Mob extends GameObject {

	TextureRegion sprite;

	public Vec2f vel = new Vec2f(0, 0);

	public float maxHP = 10;
	public float hp = maxHP;
	public float speed = 1;
	public boolean spriteDirection = false;

	public Mob(float x, float y, float width, float height, TextureRegion sprite) {
		super(x, y, width, height);
		this.sprite = sprite;
		spriteBounds = new Rectangle(pos.x - sprite.getRegionWidth() / 2, pos.y - sprite.getRegionHeight() / 2,
				sprite.getRegionWidth(), sprite.getRegionHeight());
		bounds = new Rectangle(pos.x - width / 2, spriteBounds.y, width, height);
	}

	public void update() {

	}

	@Override
	public void render() {

	}

	public void updateAnimation() {
		if (vel.x != 0)
			spriteDirection = (vel.x < 0);
		if (vel.length() > 0) {
			if (runningAnimation != null)
				runningAnimation.updateAnimation();
			if (weaponRunningAnimation != null)
				weaponRunningAnimation.updateAnimation();
		} else {
			if (idleAnimation != null)
				idleAnimation.updateAnimation();
			if (weaponIdleAnimation != null)
				weaponIdleAnimation.updateAnimation();
		}
	}

	public void renderAnimation() {
		if (vel.length() > 0) {
			if (runningAnimation != null)
				runningAnimation.renderAnimation(spriteDirection);
			if (weaponRunningAnimation != null)
				weaponRunningAnimation.renderAnimation(spriteDirection);
		} else {
			if (idleAnimation != null)
				idleAnimation.renderAnimation(spriteDirection);
			else
				runningAnimation.renderAnimation(spriteDirection);
			if (weaponIdleAnimation != null)
				weaponIdleAnimation.renderAnimation(spriteDirection);
		}
	}

	public void move(float x, float y) {
		pos.x += x;
		pos.y += y;

		spriteBounds = new Rectangle(pos.x - spriteBounds.width / 2, pos.y - spriteBounds.height / 2,
				spriteBounds.width, spriteBounds.height);
		bounds = new Rectangle(pos.x - bounds.width / 2, spriteBounds.y, bounds.width, bounds.height);

	}

}
