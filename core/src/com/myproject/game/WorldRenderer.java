package com.myproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private TankGame tankGame;
	private Tank tank;
	private Tank2 tank2;
	public SpriteBatch batch;
	public static final int BLOCK_SIZE = 50;
	World world;
	private StageRenderer stageRenderer;

	
	public WorldRenderer(TankGame tankGame, World world) {
        this.tankGame = tankGame;
        batch = tankGame.batch;
        this.world = world;
        tank = world.getTank();
        tank2 = world.getTank2();
        stageRenderer = new StageRenderer(batch,world.getStage());
	}
	
	public void render(float delta) {
        stageRenderer.render();
		tank.update();
		tank2.update();
		batch.begin();
		world.update();
		tank.render();
        tank2.render();
        batch.end();
	}
}
