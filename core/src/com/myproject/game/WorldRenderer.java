package com.myproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private TankGame tankGame;
	private Texture tankImg;
	private Texture nextTankImg;
	private Tank tank;
	private Tank2 tank2;
	private Texture tank2Img;
	private Texture bulletImg;
	private Bullet bullet;
	public SpriteBatch batch;
	public static final int BLOCK_SIZE = 50;
	World world;
	private StageRenderer stageRenderer;
	private GameScreen gameScreen;
	
	public WorldRenderer(TankGame tankGame, World world) {
        this.tankGame = tankGame;
        batch = tankGame.batch;
        this.world = world;
        tank = world.getTank();
        tank2 = world.getTank2();
        stageRenderer = new StageRenderer(batch,world.getStage());
	}
	
	public void render(float delta) {
	        //tankImg = new Texture(tank.getNextImg());
	        tank2Img = new Texture("myTankDown.png");
	        stageRenderer.render();
			batch = tankGame.batch;
//			tank.update();
			tank2.update();
			batch.begin();
			tank.render();
			world.update();
			//Vector2 posTank = world.getTank().getPosition();
	        Vector2 posTank2 = world.getTank2().getPosition();
	        //batch.draw(tankImg, posTank.x-BLOCK_SIZE/2,TankGame.HEIGHT-posTank.y-BLOCK_SIZE/2);
	        batch.draw(tank2Img,posTank2.x-BLOCK_SIZE/2,TankGame.HEIGHT-posTank2.y-BLOCK_SIZE/2);
	        batch.end();
	}
}
