package com.game.weaponsoftime.graphics;

import java.util.ArrayList;

public class AnimationManager {
	public ArrayList<Animation> animations = new ArrayList<Animation>();

	public void update() {
		for (Animation a : animations)
			a.updateAnimation();
	}

	public void render() {
		for (Animation a : animations)
			a.renderAnimation();
	}
}
