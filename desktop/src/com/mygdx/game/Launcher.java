package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Engines.MapMaker;

public class Launcher
{
   // private static Lwjgl3Application app;
    public static void main(String[] arg)
    {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("MapMaker32");
        config.setWindowedMode(1000, 600);

        /*app = */new Lwjgl3Application(new MapMaker(), config);
    }
}
