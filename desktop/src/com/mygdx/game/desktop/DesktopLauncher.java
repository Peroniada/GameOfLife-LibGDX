package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameOfLife;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Title";
//		cfg.useGL20 = true;
		cfg.backgroundFPS = 30;
		cfg.height = 1000;
		cfg.width = 1000;
		new LwjglApplication(new GameOfLife(), cfg);
	}
}
