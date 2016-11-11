package com.myproject.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankGame extends Game
{
	 public static SpriteBatch batch;
	 public static final int HEIGHT =1000;
	 public static final int WIDTH = 1100;
    @Override
    public void create () {
    	batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }
 
    @Override
    public void render () {
        super.render();
    }
 
    @Override
    public void dispose () {
        batch.dispose();
    }
}