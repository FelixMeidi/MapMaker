package com.mygdx.game.WorldBuilding;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.ToBeRemoved.UI;

public class MenuInput implements InputProcessor
{

    //region init

    public MenuInput(OrthographicCamera cam, UI ui)
    {
        super();
        this.cam = cam;
        this.ui = ui;

        update();
    }

    //endregion init


    //region members

    private final OrthographicCamera cam;
    private final UI ui;

    //endregion members


    //region functions

    public void update()
    {

        cam.update();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        //check for UI
        if(ui.interact(x, y,pointer,button))
        {
        }
        return false;
    }

    //region notInUse

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }







    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }


    @Override
    public boolean scrolled(float amountX, float amountY)
    {
        return false;
    }



    public boolean keyTyped(char character)
    {
        return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    //endregion notInUse


    //endregion functions
}
