package com.game.weaponsoftime.util;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.game.weaponsoftime.graphics.Renderer;

public class Input implements InputProcessor {

	public int x = 0, y = 0;
	public boolean LMB = false, RMB = false, LMB_Click = false;

	public boolean keys[] = new boolean[7000];

	Vector3 vec3 = new Vector3();

	@Override
	public boolean keyDown(int keycode) {
		keys[keycode] = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		keys[keycode] = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Renderer.camera.unproject(vec3.set(screenX, screenY, 0)); // I have no idea how this works, but ok
		x = (int) vec3.x;
		y = (int) vec3.y;
		if (button == Buttons.LEFT) {
			LMB_Click = true;
			LMB = true;
		}
		if (button == Buttons.RIGHT)
			RMB = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Renderer.camera.unproject(vec3.set(screenX, screenY, 0));
		x = (int) vec3.x;
		y = (int) vec3.y;
		if (button == Buttons.LEFT)
			LMB = false;
		if (button == Buttons.RIGHT)
			RMB = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Renderer.camera.unproject(vec3.set(screenX, screenY, 0));
		x = (int) vec3.x;
		y = (int) vec3.y;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Renderer.camera.unproject(vec3.set(screenX, screenY, 0));
		x = (int) vec3.x;
		y = (int) vec3.y;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
