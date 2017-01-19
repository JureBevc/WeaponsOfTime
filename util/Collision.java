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
				if (tiles[i][j].solid && mob.pos.sub(tiles[i][j].pos).length() < 100) {
					if (tiles[i][j].bounds.overlaps(rect))
						return true;
				}
			}
		}
		return false;
	}

	public static boolean checkMobs(Mob mob, float x, float y) {
		Rectangle rect = new Rectangle(mob.bounds);
		Vector2 center = new Vector2();
		rect.getCenter(center);
		center.add(new Vector2(x, y));
		rect.setCenter(center);

		Rectangle rect2 = new Rectangle(mob.bounds);
		Vector2 center2 = new Vector2();
		rect2.getCenter(center);
		center2.add(new Vector2(x, y));
		rect2.setCenter(center);
		for (int i = 0; i < Game.level.mobs.size(); i++) {
			if (!mob.equals(Game.level.mobs.get(i)) && !mob.equals(Game.level.player)
					&& mob.pos.sub(Game.level.mobs.get(i).pos).length() < 100) {
				rect2.set(Game.level.mobs.get(i).bounds);
				rect2.getCenter(center);
				center2.add(new Vector2(Game.level.mobs.get(i).vel.x, Game.level.mobs.get(i).vel.y));
				rect2.setCenter(center);
				if (rect.overlaps(rect2))
					return true;
			}
		}
		return false;
	}
}
