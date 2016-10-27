package com.game.weaponsoftime.entities;

import java.util.ArrayList;

public class EntityManager {
	public static ArrayList<GameObject> entities = new ArrayList<GameObject>();

	public static void renderEntities() {

		for (GameObject e : entities) {
			e.render();
		}

	}

}
