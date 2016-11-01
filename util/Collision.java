package com.game.weaponsoftime.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.entities.Mob;
import com.game.weaponsoftime.entities.Tile;

public class Collision {

	public static boolean checkWalls(Mob mob, float x, float y) {
		Tile tiles[][] = Game.level.tiles;
		Rectangle rect = new Rectangle(mob.bounds);
		Vector2 center = new Vector2();
		rect.getCenter(center);
		center.add(new Vector2(x, y));
		rect.setCenter(center);
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].solid) {
					if (tiles[i][j].bounds.overlaps(rect))
						return true;
				}
			}
		}
		return false;
	}
}
