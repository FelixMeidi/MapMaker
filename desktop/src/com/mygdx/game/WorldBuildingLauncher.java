package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Engines.WorldBuildingEngine;

public class WorldBuildingLauncher
{
    public static void main(String[] arg)
    {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("project2");
        config.setWindowedMode(1920/2, 1080/2);

        new Lwjgl3Application(new WorldBuildingEngine(), config);
/*
        Graphics.Monitor m = Gdx.graphics.getPrimaryMonitor();
        Graphics.DisplayMode dm = Gdx.graphics.getDisplayModes(m)[0];
        config.setFullscreenMode(dm);*/
    }
}
