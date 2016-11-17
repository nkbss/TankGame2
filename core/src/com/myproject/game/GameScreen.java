package com.myproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter{
	private TankGame tankGame;
	Tank tank;
	World world;
	WorldRenderer worldRenderer;
	Bullet bullet;
	public static BitmapFont font;
	public static SpriteBatch batch;
	private Sound bg;
	private Sound go;
	public GameScreen(TankGame tankGame) {
	       this.tankGame = tankGame;
	       world = new World(tankGame);
	       worldRenderer = new WorldRenderer(tankGame,world);
	       bg = Gdx.audio.newSound(Gdx.files.internal("gameTank.mp3"));
	       go = Gdx.audio.newSound(Gdx.files.internal("gameover.mp3"));
	}
	
	public void update(float delta) {
		updateTankDirection();
		updateTank2Direction();
	} 
	
	private void updateTankDirection(){
		Tank tank = world.getTank();
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
	
		private void updateTank2Direction(){
			Tank2 tank2 = world.getTank2();
			
			if(Gdx.input.isKeyPressed(Keys.W)){
					tank2.setNextDirection(Tank2.DIRECTION_UP);
			}
			
			if(Gdx.input.isKeyPressed(Keys.S)) {
		            tank2.setNextDirection(Tank.DIRECTION_DOWN);
		        }	
		    
			if(Gdx.input.isKeyPressed(Keys.D)) {
		            tank2.setNextDirection(Tank.DIRECTION_RIGHT);
		        }
		        
			if(Gdx.input.isKeyPressed(Keys.A)) {
		            tank2.setNextDirection(Tank.DIRECTION_LEFT);
		        }
		}
	
		public void render(float delta) {
	        if(World.checkGameOver || World.checkGameOver2){
	        	if(World.checkGameOver){
	        		GameOver(1);
	        		bg.stop();
	        		go.play();
	        		
	        	}
	        	else{
	        		GameOver(2);
	        		bg.stop();
	        		go.play();
	        	}
	        }
	        else if (!(World.checkGameOver && World.checkGameOver2)){
	        	update(delta);
	        	Gdx.gl.glClearColor(0, 0, 0, 1);
	        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        	worldRenderer.render(delta);
	        	bg.play();
	        }
	  }

		public static void GameOver(int i){
			batch = TankGame.batch;
			font = new BitmapFont();
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			font.setColor(Color.WHITE);
			font.getData().setScale(2);
			batch.begin();
			font.draw(batch,"PLAYER "+ i +" GAMEOVER" , 400,500);
			batch.end();
		}
}
