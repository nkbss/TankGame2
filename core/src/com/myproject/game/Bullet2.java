package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet2 {
	private Vector2 positionBullet2;
	private Vector2 positionSpriteBullet2;
	private int currentDirection = 1;
	private SpriteBatch batch;
	private Sprite bullet2Sprite;
	private Texture bullet2Img;
	private Stage stage;
	private Tank2 tank2;
	private ArrayList<Bullet2> bullets2;
	private Rectangle bullet2Rect;
	public static final int SPEED = 15;
	private static final int [][] DIR_OFFSETS = new int [][] {
        {0,0},
        {0,1},
        {1,0},
        {0,-1},
        {-1,0}
    };
    
    public Bullet2(Tank2 tank2){
    	positionBullet2 = new Vector2();
    	positionBullet2 = tank2.getPosition();
    	batch = TankGame.batch;
    	currentDirection = tank2.nextDirection;
    	this.tank2 = tank2;
    	bullets2 = new ArrayList<Bullet2>();
    	bullet2Img = new Texture(setBulletImg(currentDirection));
    	bullet2Sprite = new Sprite(bullet2Img);
    	bullet2Sprite.setPosition(positionBullet2.x-WorldRenderer.BLOCK_SIZE/2, 
    			TankGame.HEIGHT-positionBullet2.y-WorldRenderer.BLOCK_SIZE/2);
    	
    	positionSpriteBullet2 = new Vector2();
    	positionSpriteBullet2.x = bullet2Sprite.getX();
    	positionSpriteBullet2.y = bullet2Sprite.getY();
    	bullet2Rect =  new Rectangle (bullet2Sprite.getX(),bullet2Sprite.getY(),
    			bullet2Sprite.getHeight(),bullet2Sprite.getWidth());
    	
    	bullet2Rect.setPosition(bullet2Sprite.getX(),bullet2Sprite.getY());
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
		positionSpriteBullet2.x += SPEED * DIR_OFFSETS[currentDirection][0];
	    positionSpriteBullet2.y += SPEED * DIR_OFFSETS[currentDirection][1]; 
	 }

    public float getX() {
		return bullet2Sprite.getX();
	}
    
    public float getY() {
		return bullet2Sprite.getY();
	}
    
    public Rectangle getRect(){
    	return bullet2Rect;
    }
    
    public void render(){
    	update();
    	bullet2Sprite.setPosition(positionSpriteBullet2.x, positionSpriteBullet2.y);
    	bullet2Img = new Texture(setBulletImg(currentDirection));
    	batch = TankGame.batch;
    	batch.begin();
    	bullet2Sprite.draw(batch);
    	batch.end();
    	bullet2Rect.setPosition(bullet2Sprite.getX(),bullet2Sprite.getY());
    }
}
