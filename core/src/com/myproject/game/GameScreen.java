package com.myproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter{
	private TankGame tankGame;
	Tank tank;
	World world;
	WorldRenderer worldRenderer;
	Bullet bullet;
	public GameScreen(TankGame tankGame) {
	       this.tankGame = tankGame;
	       world = new World(tankGame);
	       worldRenderer = new WorldRenderer(tankGame,world);
	}
	
	public void update(float delta) {
		world.update(delta);
		updateTankDirection();
	} 
	
	private void updateTankDirection(){
		Tank tank = world.getTank();
		Bullet  bullet = world.getBullet();
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            tank.setNextDirection(Tank.DIRECTION_DOWN);		
            tank.setNextImg("myTankDown.png");

		}
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            tank.setNextDirection(Tank.DIRECTION_UP);
            tank.setNextImg("myTank.png");

        }	
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            tank.setNextDirection(Tank.DIRECTION_RIGHT);
            tank.setNextImg("myTankRight.png");

        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            tank.setNextDirection(Tank.DIRECTION_LEFT);
            tank.setNextImg("myTankLeft.png");

        }
	}
	
	public void render(float delta) {
	        update(delta);
	        Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        worldRenderer.render(delta);
	    }
}
