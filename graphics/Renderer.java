package com.game.weaponsoftime.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Renderer {
	public static SpriteBatch sb;
	//public static ShapeRenderer sr;
	public static OrthographicCamera camera;

	public static int WIDTH = 1280, HEIGHT = 720;
	public static float SCALE = 0.5f;
	public static float camOffX = WIDTH / 2, camOffY = HEIGHT / 2;

	public static void init() {
		sb = new SpriteBatch();
		//sr = new ShapeRenderer();
		camera = new OrthographicCamera(WIDTH, HEIGHT);

		camera.update();

	}

	//	public static void startSpriteBatch() {
	//		if (!sb.isDrawing()) {
	//			if (sr.isDrawing())
	//				sr.end();
	//			sb.begin();
	//		}
	//	}

	//	public static void startShapeRenderer() {
	//		if (!sr.isDrawing()) {
	//			if (sb.isDrawing())
	//				sb.end();
	//			sr.begin(ShapeRenderer.ShapeType.Filled);
	//		}
	//	}

	public static void moveCam(float x, float y) {
		camera.position.x = x;
		camera.position.y = y;
		camOffX = camera.position.x;
		camOffY = camera.position.y;
		camera.update();
		sb.setProjectionMatrix(camera.combined);

	}

	public static void zoomCam(float scale) {
		camera.zoom = scale;
		camera.update();
	}

	public static void renderTexture(Texture texture, float x, float y, float w, float h, boolean flip) {

		if (flip)
			sb.draw(texture, x + w, y, -w, h);
		else
			sb.draw(texture, x, y, w, h);

	}

	public static void renderTextureRegion(TextureRegion texture, float x, float y, float w, float h, boolean flip) {

		if (flip)
			sb.draw(texture, x + w, y, -w, h);
		else
			sb.draw(texture, x, y, w, h);

	}

	public static void renderTextureRegion(TextureRegion texture, Rectangle bounds, boolean flip) {

		if (flip)
			sb.draw(texture, bounds.x + bounds.width, bounds.y, -bounds.width, bounds.height);
		else
			sb.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);

	}

	//	public static void renderRect(Rectangle bounds, Color color) {
	//		startShapeRenderer();
	//		sr.setColor(color);
	//		sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	//		sr.end();
	//	}
}
