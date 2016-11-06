package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.weaponsoftime.entities.GameObject;

public class Animation {

	TextureRegion sheet; // Sprite sheet
	GameObject object; // The game object with the animation
	int nx, ny; // number of frames in x, number of frames in y, interval of animation
	public int interval;
	int w, h; // width and height of one frame

	public Animation(TextureRegion sheet, int nx, int ny, int interval, GameObject object) {
		this.sheet = sheet;
		this.object = object;
		this.nx = nx;
		this.ny = ny;
		this.interval = interval;
		w = sheet.getTexture().getWidth() / nx;
		h = sheet.getTexture().getHeight() / ny;
		sheet.setRegion(0, 0, w, h);
	}

	int tick = 0;
	int tx = 0;
	int ty = 0;

	public void updateAnimation() {
		tick++;
		if (tick >= interval) {
			tx++;
			if (tx >= nx) {
				tx = 0;
				ty++;
			}
			if (ty >= ny)
				ty = 0;
			sheet.setRegion(tx * w, ty * h, w, h);
			tick = 0;
		}

	}

	public void renderAnimation(boolean flipped) {

		Renderer.renderTextureRegion(sheet, object.pos.x - w / 2, object.pos.y - h / 2, w, h, flipped);

	}

}
