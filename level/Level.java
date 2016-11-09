package com.game.weaponsoftime.level;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.entities.Mob;
import com.game.weaponsoftime.entities.Player;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.graphics.Renderer;

public class Level {
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
	}

	//int renderingTiles = 0;

	public void renderLevel() {
		int[] firstRenderingTile = getFirstRenderingTile();
		int tileSize = (int) tiles[0][0].bounds.width;
		int renderOffX = firstRenderingTile[0];
		if (renderOffX < 0)
			renderOffX = 0;
		int tileRenderWidth = 42 + renderOffX;
		if (tileRenderWidth >= tiles.length)
			tileRenderWidth = tiles.length;

		int renderOffY = firstRenderingTile[1];
		if (renderOffY < 0)
			renderOffY = 0;

		int tileRenderHeight = 25 + renderOffY;
		if (tileRenderHeight >= tiles[0].length)
			tileRenderHeight = tiles[0].length;

		//renderingTiles = 0;
		for (int i = renderOffX; i < tileRenderWidth; i++) {
			for (int j = renderOffY; j < tileRenderHeight; j++) {
				//if (onScreen(tiles[i][j].bounds)) 
				tiles[i][j].render();
				//renderingTiles++;
			}
		}
		//System.out.println(renderingTiles);
		for (Mob m : mobs)
			m.render();
	}

	public boolean onScreen(Rectangle r) {
		if (Renderer.camera.frustum.pointInFrustum(r.x, r.y, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x + r.width, r.y, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x, r.y + r.height, 0)
				|| Renderer.camera.frustum.pointInFrustum(r.x + r.width, r.y + r.height, 0))
			return true;
		return false;
	}

	public void renderFront() { // TODO Render all decorations that are shown in
									// front of mobs and map

	}

	public int[] getFirstRenderingTile() {
		int[] r = { 0, 0 };
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (onScreen(tiles[i][j].bounds)) {
					r[0] = i;
					r[1] = j;
					return r;
				}
			}
		}
		return r;
	}

}
