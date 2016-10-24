package com.game.weaponsoftime.util;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {

	public int x = 0, y = 0;
	public boolean LMB = false, RMB = false;

	public boolean keys[] = new boolean[7000];

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		x = screenX;
		y = screenY;
		if (button == Buttons.LEFT)
			LMB = true;
		if (button == Buttons.RIGHT)
			RMB = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		x = screenX;
		y = screenY;
		if (button == Buttons.LEFT)
			LMB = false;
		if (button == Buttons.RIGHT)
			RMB = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		x = screenX;
		y = screenY;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
