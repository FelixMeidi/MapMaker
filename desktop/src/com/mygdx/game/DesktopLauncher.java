package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Engine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
	//	config.setForegroundFPS(60);
		//config.setTitle("project2");
	//	config.setWindowedMode(1920,1080);
	//	Graphics.Monitor m = Gdx.graphics.getPrimaryMonitor();
	//	Graphics.DisplayMode dm = Gdx.graphics.getDisplayModes(m)[0];
	//	config.setFullscreenMode(dm);
		new Lwjgl3Application(new Engine(), config);
	}
}
