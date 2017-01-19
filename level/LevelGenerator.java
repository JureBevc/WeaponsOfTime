package com.game.weaponsoftime.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.weaponsoftime.Game;
import com.game.weaponsoftime.entities.Player;
import com.game.weaponsoftime.entities.Rat;
import com.game.weaponsoftime.entities.Tile;
import com.game.weaponsoftime.graphics.Animation;
import com.game.weaponsoftime.graphics.Textures;
import com.game.weaponsoftime.util.Point;

public class LevelGenerator {

	int tileSize = 16;
	Tile tiles[][];

	ArrayList<Rectangle> allRooms = new ArrayList<Rectangle>();

	Level level;

	public LevelGenerator(Level level) {
		this.level = level;

	}

	public void createMap(int width, int height) {
		Game.animationManager.animations.clear();
		level = Game.level;
		allRooms.clear();

		tiles = new Tile[width / 2][height / 2]; // Create array

		// Create tiles, all solid
		for (int i = 0; i < width / 2; i++) {
			for (int j = 0; j < height / 2; j++) {
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
		for (int k = 0; k < 100; k++)
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (!tiles[i][j].solid && checkNeighbours(new Point(i, j), true) == 3) {
						tiles[i][j].solid = true;
					}
				}
			}

		// Clear out lonely walls

		for (int k = 0; k < 100; k++)
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (tiles[i][j].solid && checkNeighbours(new Point(i, j), false) > 2) {
						tiles[i][j].solid = false;
					}
				}
			}
		// Double the tiles
		Tile[][] temp = new Tile[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				temp[i][j] = new Tile(i * tileSize, j * tileSize, tileSize, tileSize);
				temp[i][j].solid = tiles[i / 2][j / 2].solid;
			}
		}
		tiles = temp;
		addTextures();
		Game.level.tiles = tiles;
		addMobs();
		addExit();
	}

	public void addMobs() {
		Game.level.mobs.clear(); // Clear mob list
		int ex = 0, ey = 0;
		// Define entrance and exit of level
		outerloop: for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (!tiles[i][j].solid && checkNeighbours(new Point(i, j), false) >= 4) {
					ex = i;
					ey = j;
					Game.level.entrance = tiles[i][j];
					Game.animationManager.animations
							.add(new Animation(Textures.entrance, 3, 1, 5, Game.level.entrance));
					break outerloop;
				}
			}
		}

		// Add mobs
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (!tiles[i][j].solid && checkNeighbours(new Point(i, j), false) >= 4
						&& tileDistance(i, j, ex, ey) > 20 && Math.random() < 0.01) {
					Game.level.mobs.add(new Rat(tiles[i][j].bounds.x, tiles[i][j].bounds.y, tiles[i][j].bounds.width,
							tiles[i][j].bounds.height, Textures.ratRunning));

				}
			}
		}

		// Create Player and add him to mob list
		Game.level.player = new Player(Game.level.entrance.pos.x,
				Game.level.entrance.pos.y + Game.level.entrance.bounds.height / 2, tileSize / 4, tileSize / 4,
				Textures.knightRunning);
		Game.level.mobs.add(Game.level.player);
	}

	public void addExit() {
		outerloop: for (int i = tiles.length - 1; i >= 0; i--) {
			for (int j = tiles[i].length - 1; j >= 0; j--) {
				if (!tiles[i][j].solid && checkNeighbours(new Point(i, j), false) >= 4) {
					Game.level.exit = tiles[i][j];
					Game.animationManager.animations.add(new Animation(Textures.exit, 3, 1, 5, Game.level.exit));
					break outerloop;
				}
			}
		}
	}

	public void addTextures() {
		// Add textures to all tiles in level
		int w = 16, h = 16;
		TextureRegion floor[][] = Textures.floor_wood.split(w, h);
		TextureRegion wall[][] = Textures.wall_panel.split(w, h);
		System.out.println(floor[0].length);
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if(tiles[i][j].solid && checkNeighbours(new Point(i,j), true) == 4){
					tiles[i][j].texture = Textures.black_tile;
					continue;
				}
				if (!tiles[i][j].solid) {
					tiles[i][j].texture = floor[0][j % 2];
				} else {
					tiles[i][j].texture = wall[0][j % 2];

				}
			}
		}

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
			if (allRooms.get(i).overlaps(new Rectangle(p.x - 1, p.y, 1, 1))
					|| allRooms.get(i).overlaps(new Rectangle(p.x + 1, p.y, 1, 1))
					|| allRooms.get(i).overlaps(new Rectangle(p.x, p.y - 1, 1, 1))
					|| allRooms.get(i).overlaps(new Rectangle(p.x, p.y + 1, 1, 1))) {
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
		if (p.x > 0 && tiles[p.x - 1][p.y].solid == solid)
			n++;
		if (p.x < tiles.length - 1 && tiles[p.x + 1][p.y].solid == solid)
			n++;
		if (p.y > 0 && tiles[p.x][p.y - 1].solid == solid)
			n++;
		if (p.y < tiles[0].length - 1 && tiles[p.x][p.y + 1].solid == solid)
			n++;
		return n;

	}

	public void generateRooms() {
		// Spawn rooms
		int attempts = 1000;
		int minRoomWidth = 5;
		int minRoomHeight = 5;
		int maxRoomWidth = 10;
		int maxRoomHeight = 10;
		while (attempts > 0) {
			// Create a random room
			Rectangle room = new Rectangle((int) (Math.random() * (tiles.length - maxRoomWidth)) + 1,
					(int) (Math.random() * (tiles[0].length - maxRoomHeight)) + 1,
					(int) (Math.random() * (maxRoomWidth - minRoomWidth)) + minRoomWidth,
					(int) (Math.random() * (maxRoomHeight - minRoomHeight)) + minRoomHeight);

			// Try to fit it in
			boolean fits = true;
			for (int i = 0; i < allRooms.size(); i++) {
				if (allRooms.get(i).overlaps(room)) {
					fits = false;
					break;
				}
			}
			if (!fits) {
				attempts--;
				continue;
			} else {
				attempts--;
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

	public int tileDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
