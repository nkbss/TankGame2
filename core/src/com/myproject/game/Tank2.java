package com.myproject.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tank2 {
	
	private Vector2 position;
	public int nextDirection;
	private int currentDirection;
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    public static final int SPEED = 5;
	ArrayList<Bullet2> bullets2;
	private Texture tankTexture;
	private Sprite tankSprite;
	private Rectangle tank2Rect;
	private String tankImg;
	private SpriteBatch batch;
    private static final int [][] DIR_OFFSETS = new int [][] {
	        {0,0},
	        {0,-1},
	        {1,0},
	        {0,1},
	        {-1,0}
	    };
	    
	
	
	Tank2(int x, int y, Stage stage){
		position = new Vector2(x,y);
		bullets2 = new ArrayList<Bullet2>();
		batch = TankGame.batch;
		tankImg = "myTankDown.png";
		tankTexture = new Texture(tankImg);
	    tankSprite = new Sprite(tankTexture);
	    tankSprite.setPosition(position.x - WorldRenderer.BLOCK_SIZE / 2,
	        		TankGame.HEIGHT - position.y - WorldRenderer.BLOCK_SIZE / 2);
	        
	        tank2Rect = new Rectangle(tankSprite.getX(), tankSprite.getY(), tankSprite.getHeight(), tankSprite.getWidth());
	        tank2Rect.setPosition(tankSprite.getX(), tankSprite.getY());
	}
	
	Vector2 getPosition(){
		return position;
	}

	 
	public void setNextDirection(int dir) {
	        nextDirection = dir;
	    }
	 
	public boolean isAtCenter() {
	        int blockSize = WorldRenderer.BLOCK_SIZE;
	        return ((((int)position.x - blockSize/2) % blockSize) == 0) &&
	                ((((int)position.y - blockSize/2) % blockSize) == 0);
	    }

	private int getRow() {
        return ((int)position.y) / WorldRenderer.BLOCK_SIZE; 
    }
 
	private int getColumn() {
        return ((int)position.x) / WorldRenderer.BLOCK_SIZE; 
    }
	
	private boolean canMoveInDirection(int dir) {
        int newRow = this.getRow() + DIR_OFFSETS[dir][1]; 
        int newCol = this.getColumn() + DIR_OFFSETS[dir][0]; 
        if(Stage.STAGE[newRow].charAt(newCol) != '.'){
        	return false;
        }
        return true;
    }
	
	public void shoot(){
		bullets2.add(new Bullet2(this));
	}
	public void update() {
		 if(isAtCenter()) {
			 if(canMoveInDirection(nextDirection)) {
				 currentDirection = nextDirection;    
			 } else {
				 currentDirection = DIRECTION_STILL;    
	            }
	        }
	        position.x += SPEED * DIR_OFFSETS[currentDirection][0];
	        position.y += SPEED * DIR_OFFSETS[currentDirection][1];
	        
	    if(Gdx.input.isKeyJustPressed(Keys.G)){
			shoot();
	    }
	    for (Bullet2 bullet2 : bullets2) {
	    	bullet2.render();
	    }
	 }
	
	public ArrayList<Bullet2> getBullet2List() {
		return bullets2;
	}

	public Rectangle getRect(){
		return tank2Rect;
	}
	
	public void setNextImg(String nextImg) {
		this.tankImg = nextImg; 
	}

	public String getNextImg(){
		return tankImg;
	}
	
	public void render(){
		tankTexture = new Texture(tankImg);
		tankSprite = new Sprite(tankTexture);
        tankSprite.setPosition(position.x - WorldRenderer.BLOCK_SIZE / 2,
        		TankGame.HEIGHT - position.y - WorldRenderer.BLOCK_SIZE / 2);
        tankSprite.draw(batch);
        tank2Rect.setPosition(tankSprite.getX(), tankSprite.getY());
	}

}
