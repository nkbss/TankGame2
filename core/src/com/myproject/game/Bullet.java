package com.myproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 positionBullet;
	private Vector2 posSpriteBullet;
	public boolean canShoot;
	private Texture bulletImg;
	World world;
	private Tank tank;
	public boolean clash ;
	public static final int SPEED = 15;
	private int currentDirection = 1;
	public SpriteBatch batch;
	private Sprite bulletSprite;
	private Stage stage;
	private static final int [][] DIR_OFFSETS = new int [][] {
        {0,0},
        {0,1},
        {1,0},
        {0,-1},
        {-1,0}
    };
	
    public Bullet(Tank tank){
    	positionBullet = new Vector2();
    	positionBullet = tank.getPosition();
    	batch = TankGame.batch;
    	currentDirection = tank.nextDirection;
    	this.tank = tank;
    	bulletImg = new Texture(setBulletImg(currentDirection));
    	bulletSprite = new Sprite(bulletImg);
    	bulletSprite.setPosition(positionBullet.x-WorldRenderer.BLOCK_SIZE/2, TankGame.HEIGHT-positionBullet.y-WorldRenderer.BLOCK_SIZE/2);
    	posSpriteBullet = new Vector2();
    	posSpriteBullet.x = bulletSprite.getX();
    	posSpriteBullet.y = bulletSprite.getY();
    }

	public String setBulletImg(int dir) {
		if(dir == 1){
			return "bullet.png";
		}
		else if(dir == 3){
			return "bulletDown.png";
		}
		else if(dir == 2){
			return "bulletRight.png";
		}
		else {
			return "bulletLeft.png";
		}
	}

	public void update(){
		posSpriteBullet.x += SPEED * DIR_OFFSETS[currentDirection][0];
	    posSpriteBullet.y += SPEED * DIR_OFFSETS[currentDirection][1];
	 
	}

	public boolean clashWall(int r, int c){
		if(stage.STAGE[r].charAt(c) == 'b'){
			clash = false;
		}
		return clash;
	}
	
	public void render(){
		update();
    	bulletSprite.setPosition(posSpriteBullet.x,posSpriteBullet.y);
		bulletImg = new Texture(setBulletImg(currentDirection));
		batch = TankGame.batch;
		batch.begin();
		bulletSprite.draw(batch);
		batch.end();
	}

}