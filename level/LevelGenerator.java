package com.game.weaponsoftime.level;

import com.game.weaponsoftime.entities.EntityManager;
import com.game.weaponsoftime.entities.Tile;

public class LevelGenerator {

	int tileSize = 50;
	Tile tiles[][];

	public void createMap(int width, int height) {
		tiles = new Tile[width][height];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j] = new Tile(i * tileSize, j * tileSize, tileSize, tileSize);
				tiles[i][j].solid = true;
			}
		}
		
		EntityManager.tiles = tiles;
	}
}
