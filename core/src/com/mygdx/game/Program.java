package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.EditorScreen;
import com.mygdx.game.screens.MainMenuScreen;
import jdk.tools.jmod.Main;

public class Program extends Game
{
    @Override
    public void create()
    {
        setScreen(new MainMenuScreen(this,400,400));
    }
}
