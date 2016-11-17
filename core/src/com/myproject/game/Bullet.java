package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet {
	private Vector2 positionBullet;
	private Vector2 posSpriteBullet;
	public boolean canShoot;
	private Texture bulletImg;
	World world;
	private Tank tank;
	public static final int SPEED = 15;
	private int currentDirection = 1;
	public SpriteBatch batch;
	private Sprite bulletSprite;
	private Stage stage;
	private ArrayList<Bullet> bullets;
	private Rectangle bulletRect;
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
    	bullets = new ArrayList<Bullet>();
    	bulletImg = new Texture(setBulletImg(currentDirection));
    	bulletSprite = new Sprite(bulletImg);
    	bulletSprite.setPosition(positionBullet.x-WorldRenderer.BLOCK_SIZE/2, 
    			TankGame.HEIGHT-positionBullet.y-WorldRenderer.BLOCK_SIZE/2);
    	
    	posSpriteBullet = new Vector2();
    	posSpriteBullet.x = bulletSprite.getX();
    	posSpriteBullet.y = bulletSprite.getY();
    	bulletRect =  new Rectangle (bulletSprite.getX(),bulletSprite.getY(),
    			bulletSprite.getHeight(),bulletSprite.getWidth());
    	
    	bulletRect.setPosition(bulletSprite.getX(),bulletSprite.getY());
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
	
	public float getX() {
		return bulletSprite.getX();
	}
	
	public float getY() {
		return bulletSprite.getY();
	}

	public Rectangle getRect(){
		return bulletRect;
	}
	
	public void render(){
		update();
		bulletSprite.setPosition(posSpriteBullet.x,posSpriteBullet.y);
		bulletImg = new Texture(setBulletImg(currentDirection));
		batch = TankGame.batch;
		batch.begin();
		bulletSprite.draw(batch);
		batch.end();
		bulletRect.setPosition(bulletSprite.getX(),bulletSprite.getY());
	}
}