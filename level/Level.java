package com.game.weaponsoftime.level;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.graphics.Renderer;

public class Level {
	public Tile tiles[][];

	public void renderLevel() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (onScreen(tiles[i][j].bounds))
					tiles[i][j].render();
			}
		}
	}

	public boolean onScreen(Rectangle r) {
		if (r.x + r.width > Renderer.camOffX - Renderer.WIDTH / 2 && r.x < Renderer.camOffX + Renderer.WIDTH / 2
				&& r.y + r.height > Renderer.camOffY - Renderer.HEIGHT / 2
				&& r.y < Renderer.camOffY + Renderer.HEIGHT / 2)
			return true;
		return false;
	}

	public void renderFront() { // TODO Render all decorations that are shown in front of mobs

	}

}
