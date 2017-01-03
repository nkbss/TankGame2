package com.myproject.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StageRenderer {
	private Stage stage;
	private SpriteBatch batch;
	private Texture wallImage;
	private Texture brickImage;
	private Texture MetalImage;
	private Texture baseImage;
	private Texture exploreImage;
	public StageRenderer(SpriteBatch batch, Stage stage){
	this.stage = stage;
	this.batch = batch;
	wallImage = new Texture("edge.PNG");
	brickImage = new Texture("brick.PNG");
	MetalImage = new Texture("metal.PNG");
	baseImage = new Texture("base.png");
	exploreImage = new Texture("explore.PNG");
	}
	
	public void render(){
		batch.begin();
        for(int r = 0; r < stage.getHeight(); r++) {
            for(int c = 0; c < stage.getWidth(); c++) {
                int x = c * WorldRenderer.BLOCK_SIZE;
                int y = TankGame.HEIGHT - (r * WorldRenderer.BLOCK_SIZE) - WorldRenderer.BLOCK_SIZE;
                if(stage.hasWallAt(r, c)) {
                    batch.draw(wallImage, x, y);
                } else if(stage.hasBrickAt(r, c)) {
                    batch.draw(brickImage, x, y);
                } else if(stage.hasMetalAt(r, c)) {
                    batch.draw(MetalImage, x, y);
                }else if(stage.hasBaseAt1(r, c)) {
                    batch.draw(baseImage, x, y);
                }else if(stage.hasBaseAt2(r, c)) {
                    batch.draw(baseImage, x, y);
                }else if(stage.hasExploreAt(r, c)) {
                    batch.draw(exploreImage, x, y);
                }
            }
        }
        batch.end();
	}
}
