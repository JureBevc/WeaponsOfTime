package com.game.weaponsoftime.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static Tile tiles[][];

	public static void renderEntities(SpriteBatch batch) {
		
		for (Entity e : entities) {
			e.render(batch);
		}

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j].render(batch);
			}
		}

	}
}
