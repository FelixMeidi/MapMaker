package de.meidi.game;

import com.badlogic.gdx.Game;
import de.meidi.game.screens.MainMenuScreen;

public class Program extends Game
{
    @Override
    public void create()
    {
        setScreen(new MainMenuScreen(this,400,400));
    }
}
