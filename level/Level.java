package com.game.weaponsoftime.level;

import java.util.ArrayList;

import com.game.weaponsoftime.entities.Mob;
import com.game.weaponsoftime.entities.Player;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.graphics.Renderer;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.util.Collision;

public class Level {
	// DEBUG VARIABLES
	static boolean RENDER_MOB_BOUNDS = true;
	//

	public static Tile tiles[][]; // currently loaded tiles
	public static Tile entrance; // entrance tile
	public static Tile exit; // exit tile

	public static Player player; // the player
	public static ArrayList<Mob> mobs = new ArrayList<Mob>(); // every mob in level

	public static void init() {

	}

	public static void updateLevel() {
		for (Mob m : mobs)
			m.update();
		clearDead(); // remove dead mobs
	}

	public static void renderLevel() {

		// Render tiles
		for (Tile ta[] : tiles) {
			for (Tile t : ta) {
				if (Collision.onScreen(t.getBounds()))
					t.render();
			}
		}

		// Render mobs
		for (Mob m : mobs)
			m.render();

		// Exit of the level
		if (player.pos.sub(exit.pos).length() < 20)
			LevelGenerator.createMap(tiles.length, tiles[0].length);

		// DEBUG -- AREA -- BELOW
		if (RENDER_MOB_BOUNDS)
			for (Mob m : mobs)
				Renderer.renderTextureRegion(Textures.DEBUG, m.getBounds(), false);

	}

	/* For clearing dead mobs, called after they update */
	public static void clearDead() {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).hp <= 0) {
				mobs.remove(i);
				i--;
			}
		}
	}

}
