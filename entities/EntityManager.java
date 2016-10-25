package com.game.weaponsoftime.entities;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.graphics.Renderer;

public class EntityManager {
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static Tile tiles[][];

	public static void renderEntities() {

		for (Entity e : entities) {
			e.render();
		}

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (onScreen(tiles[i][j].bounds))
					tiles[i][j].render();
			}
		}

	}

	public static boolean onScreen(Rectangle r) {
		if (r.x + r.width > Renderer.camOffX - Renderer.WIDTH / 2 && r.x < Renderer.camOffX + Renderer.WIDTH / 2
				&& r.y + r.height > Renderer.camOffY - Renderer.HEIGHT / 2
				&& r.y < Renderer.camOffY + Renderer.HEIGHT / 2)
			return true;
		return false;
	}
}
