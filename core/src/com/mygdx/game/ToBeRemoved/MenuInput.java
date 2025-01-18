package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MenuInput implements InputProcessor
{

    //region init

    public MenuInput(OrthographicCamera cam)
    {
        super();
        this.cam = cam;

        update();
    }

    //endregion init


    //region members

    private final OrthographicCamera cam;

    //endregion members


    //region functions

    public void update()
    {

        cam.update();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        return false;
    }

    //region notInUse

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }



 /*   @Override
    public boolean touchCancelled(int x, int y, int z, int u)
    {
        return false;
    }
*/


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
