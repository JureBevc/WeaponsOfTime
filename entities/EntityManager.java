package com.game.weaponsoftime.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public static void renderEntities(SpriteBatch batch) {
		for (Entity e : entities) {
			e.render(batch);
		}
	}
}
