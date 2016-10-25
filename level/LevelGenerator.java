package com.game.weaponsoftime.level;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.weaponsoftime.entities.EntityManager;
import com.game.weaponsoftime.entities.Tile;

public class LevelGenerator {

	int tileSize = 8;
	Tile tiles[][];

	public void createMap(int width, int height) {
		tiles = new Tile[width][height]; // Create array

		// Create tiles, all solid
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tiles[i][j] = new Tile(i * tileSize, j * tileSize, tileSize, tileSize);

				tiles[i][j].solid = true;
			}
		}

		// Generate rooms
		generateRooms();

		// Generate maze
		generateMaze();

		EntityManager.tiles = tiles;
	}

	public void generateMaze() {
		int sx = (int) (Math.random() * (tiles.length - 4)) + 2;
		int sy = (int) (Math.random() * (tiles[0].length - 4)) + 2;
		while (!tiles[sx][sy].solid) {
			sx = (int) (Math.random() * (tiles.length - 4)) + 2;
			sy = (int) (Math.random() * (tiles[0].length - 4)) + 2;
		}
		tiles[sx][sy].solid = false;
		ArrayList<Tile> walls = new ArrayList<Tile>();
		ArrayList<Point> wallsXY = new ArrayList<Point>();
		walls.add(tiles[sx - 1][sy]);
		wallsXY.add(new Point(sx - 1, sy));

		walls.add(tiles[sx + 1][sy]);
		wallsXY.add(new Point(sx + 1, sy));

		walls.add(tiles[sx][sy - 1]);
		wallsXY.add(new Point(sx, sy - 1));

		walls.add(tiles[sx][sy + 1]);
		wallsXY.add(new Point(sx, sy + 1));

		while (!walls.isEmpty()) {
			int rand = (int) (Math.random() * walls.size());
			if (checkNeighbours(wallsXY.get(rand)) > 1) {
				wallsXY.remove(rand);
				walls.remove(rand);
			} else {
				Point p = wallsXY.get(rand);

				if (p.x > 1 && tiles[p.x - 1][p.y].solid) {
					walls.add(tiles[p.x - 1][p.y]);
					wallsXY.add(new Point(p.x - 1, p.y));
				}
				if (p.x < tiles.length - 2 && tiles[p.x + 1][p.y].solid) {
					walls.add(tiles[p.x + 1][p.y]);
					wallsXY.add(new Point(p.x + 1, p.y));
				}
				if (p.y > 1 && tiles[p.x][p.y - 1].solid) {
					walls.add(tiles[p.x][p.y - 1]);
					wallsXY.add(new Point(p.x, p.y - 1));
				}
				if (p.y < tiles[0].length - 2 && tiles[p.x][p.y + 1].solid) {
					walls.add(tiles[p.x][p.y + 1]);
					wallsXY.add(new Point(p.x, p.y + 1));
				}

				tiles[p.x][p.y].solid = false;
				wallsXY.remove(rand);
				walls.remove(rand);
			}
		}

	}

	public int checkNeighbours(Point p) {
		// Returns neighbours that are part of the maze
		int n = 0;
		if (p.x > 1 && !tiles[p.x - 1][p.y].solid)
			n++;
		if (p.x < tiles.length - 2 && !tiles[p.x + 1][p.y].solid)
			n++;
		if (p.y > 1 && !tiles[p.x][p.y - 1].solid)
			n++;
		if (p.y < tiles[0].length - 2 && !tiles[p.x][p.y + 1].solid)
			n++;
		return n;

	}

	public void generateRooms() {
		// Spawn rooms
		int attempts = 1000;
		int minRoomWidth = 10;
		int minRoomHeight = 10;
		int maxRoomWidth = 20;
		int maxRoomHeight = 20;
		ArrayList<Rectangle> allRooms = new ArrayList<Rectangle>();
		while (attempts > 0) {
			// Create a random room
			Rectangle room = new Rectangle((int) (Math.random() * (tiles.length - maxRoomWidth)) + 1,
					(int) (Math.random() * (tiles[0].length - maxRoomHeight)) + 1,
					(int) (Math.random() * (maxRoomWidth - minRoomWidth)) + minRoomWidth,
					(int) (Math.random() * (maxRoomHeight - minRoomHeight)) + minRoomHeight);

			// Try to fit it in 
			boolean fits = true;
			for (int i = 0; i < allRooms.size(); i++) {
				if (allRooms.get(i).intersects(room)) {
					fits = false;
					break;
				}
			}
			if (!fits) {
				attempts--;
				continue;
			} else {

				createRoom(room);
				allRooms.add(room);
			}
		}
	}

	public void createRoom(Rectangle r) {

		// Edit all tiles with generated rooms
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (i > r.x + 1 && i < r.x + r.getWidth() - 1 && j > r.y + 1 && j < r.y + r.getHeight() - 1) {
					tiles[i][j].solid = false;
				}
			}
		}
	}
}
