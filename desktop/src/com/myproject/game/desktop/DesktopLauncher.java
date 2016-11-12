package com.myproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myproject.game.TankGame;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TankGame.WIDTH;
        config.height = TankGame.HEIGHT;
        new LwjglApplication(new TankGame(), config);
	}
}
