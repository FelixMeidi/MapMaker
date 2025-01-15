package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Engines.MainMenu;
import com.mygdx.game.Engines.Project;

public class Launcher
{
    private static Lwjgl3Application app;
    public static void main(String[] arg)
    {
        openMainMenu();
/*
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("project2");
        config.setWindowedMode(1920/2, 1080/2);

        app = new Lwjgl3Application(new Project(), config);
*/


    }
    private static void openMainMenu()
    {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Main Menu  -  MapMaker32");
        config.setWindowedMode(400, 600);

        app = new Lwjgl3Application(new MainMenu(), config);
    }
}
