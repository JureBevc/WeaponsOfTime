package com.game.weaponsoftime.util;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.entities.GameObject;
import com.game.weaponsoftime.entities.Mob;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.level.Level;

public class Collision {

	public static boolean checkWalls(GameObject go) {
		Rectangle r1 = go.getBounds();
		for (Tile[] ta : Level.tiles) {
			for (Tile t : ta) {
				if (t.solid && r1.overlaps(t.getBounds())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkMobs(GameObject go) {
		Rectangle r1 = go.getBounds();
		for (Mob m : Level.mobs) {
			if (r1.overlaps(m.getBounds())) {
				return true;
			}
		}

		return false;
	}

	public static boolean onScreen(Rectangle r) {
		if (Renderer.camera.frustum.pointInFrustum(r.x, r.y, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x + r.width, r.y, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x, r.y + r.height, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x + r.width, r.y + r.height, 0))
			return true;
		return false;
	}
}
