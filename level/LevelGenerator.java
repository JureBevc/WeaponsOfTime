package com.game.weaponsoftime.level;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.weaponsoftime.entities.Tile;

public class LevelGenerator {

	int tileSize = 8;
	Tile tiles[][];

	ArrayList<Rectangle> allRooms = new ArrayList<Rectangle>();

	Level level;

	public LevelGenerator(Level level) {
		this.level = level;
	}

	public void createMap(int width, int height) {
		allRooms.clear();
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

		// Connect the maze with the rooms
		connect();

		// Clear out some dead ends for better maze
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (!tiles[i][j].solid && checkNeighbours(new Point(i, j), true) == 3) {
					tiles[i][j].solid = true;
				}
			}
		}

		// Clear out lonely walls

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].solid && checkNeighbours(new Point(i, j), false) > 3) {
					tiles[i][j].solid = false;
				}
			}
		}

		// Clear out some dead ends for better maze
		for (int k = 0; k < 100; k++)
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (!tiles[i][j].solid && checkNeighbours(new Point(i, j), true) == 3) {
						tiles[i][j].solid = true;
					}
				}
			}

		level.tiles = tiles;
	}

	public void connect() {

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].solid && checkNeighbours(new Point(i, j), false) == 2) {
					if (checkForRoom(new Point(i, j)) > 0) {
						tiles[i][j].solid = false;
					}
				}
			}
		}
	}

	public int checkForRoom(Point p) {
		// Checks around a tile how many non-solid tiles are a room 
		int n = 0;
		for (int i = 0; i < allRooms.size(); i++) {
			if (allRooms.get(i).intersects(new Rectangle(p.x - 1, p.y, 1, 1))
					|| allRooms.get(i).intersects(new Rectangle(p.x + 1, p.y, 1, 1))
					|| allRooms.get(i).intersects(new Rectangle(p.x, p.y - 1, 1, 1))
					|| allRooms.get(i).intersects(new Rectangle(p.x, p.y + 1, 1, 1))) {
				n++;
				break;
			}

		}
		return n;
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
			if (checkNeighbours(wallsXY.get(rand), false) > 1) {
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

	public int checkNeighbours(Point p, boolean solid) {
		// Returns neighbours that are part of the maze
		int n = 0;
		if (p.x > 1 && tiles[p.x - 1][p.y].solid == solid)
			n++;
		if (p.x < tiles.length - 2 && tiles[p.x + 1][p.y].solid == solid)
			n++;
		if (p.y > 1 && tiles[p.x][p.y - 1].solid == solid)
			n++;
		if (p.y < tiles[0].length - 2 && tiles[p.x][p.y + 1].solid == solid)
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
