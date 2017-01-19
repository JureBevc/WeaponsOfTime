package com.game.weaponsoftime.level;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.entities.Mob;
import com.game.weaponsoftime.entities.Player;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;

public class Level {
	// DEBUG VARIABLES
	boolean RENDER_MOB_BOUNDS = false;
	// END OF DEBUG

	public Tile tiles[][];
	public Tile entrance;
	public Tile exit;

	public Player player;
	public ArrayList<Mob> mobs = new ArrayList<Mob>();

	public Level() {

	}

	public void updateLevel() {
		for (Mob m : mobs)
			m.update();
		clearDead();
	}

	public void renderLevel() {

		// Render tiles
		for (Tile ta[] : tiles) {
			for (Tile t : ta) {
				if (onScreen(t.bounds))
					t.render();
			}
		}

		// Render mobs
		for (Mob m : mobs)
			m.render();

		// DEBUG AREA
		if (RENDER_MOB_BOUNDS)
			for (Mob m : mobs)
				Renderer.renderTextureRegion(Textures.DEBUG, m.bounds, false);

		// Exit of the level
		if (player.pos.sub(exit.pos).length() < 20)
			Game.levelGenerator.createMap(tiles.length, tiles[0].length);
	}

	public boolean onScreen(Rectangle r) {
		if (Renderer.camera.frustum.pointInFrustum(r.x, r.y, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x + r.width, r.y, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x, r.y + r.height, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x + r.width, r.y + r.height, 0))
			return true;
		return false;
	}

	public void clearDead() {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).hp <= 0) {
				mobs.remove(i);
				i--;
			}
		}
	}

}
