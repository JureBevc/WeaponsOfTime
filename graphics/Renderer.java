package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Renderer {
	public static SpriteBatch sb;
	public static ShapeRenderer sr;
	static OrthographicCamera camera;

	public static int WIDTH = 1280, HEIGHT = 720;
	public static float camOffX = WIDTH / 2, camOffY = HEIGHT / 2;

	public static void init() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		camera = new OrthographicCamera(WIDTH, HEIGHT);

		camera.update();

	}

	public static void startSpriteBatch() {
		if (!sb.isDrawing()) {
			if (sr.isDrawing())
				sr.end();
			sb.begin();
		}
	}

	public static void startShapeRenderer() {
		if (!sr.isDrawing()) {
			if (sb.isDrawing())
				sb.end();
			sr.begin(ShapeRenderer.ShapeType.Filled);
		}
	}

	public static void moveCam(float x, float y) {
		camera.position.x = x;
		camera.position.y = y;
		camOffX = camera.position.x;
		camOffY = camera.position.y;
		camera.update();
		sb.setProjectionMatrix(camera.combined);
		sr.setProjectionMatrix(camera.combined);
	}

	public static void renderTexture(Texture texture, float x, float y, float w, float h) {
		startSpriteBatch();
		sb.draw(texture, x, y, w, h);
		sb.end();
	}
	
	public static void renderTextureRegion(TextureRegion texture, float x, float y, float w, float h) {
		startSpriteBatch();
		sb.draw(texture, x, y, w, h);
		sb.end();
	}

	public static void renderRect(Rectangle bounds, Color color) {
		startShapeRenderer();
		sr.setColor(color);
		sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		sr.end();
	}
}
