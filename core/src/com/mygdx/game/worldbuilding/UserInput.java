package com.mygdx.game.worldbuilding;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.map.Map;
import com.mygdx.game.tools.exceptions.ExceptionHandler;
import com.mygdx.game.tools.exceptions.InvalidVector2IntLimException;
import com.mygdx.game.map.Tile;
import com.mygdx.game.tools.vector.Vector2IntLim;

public class UserInput implements InputProcessor
{

    //region init

    public UserInput(OrthographicCamera cam, Map map)
    {
        super();
        this.cam = cam;
        this.map = map;

        update();
    }

    //endregion init


    //region members

    private final OrthographicCamera cam;

    private boolean isHeldDown_W;
    private boolean isHeldDown_S;
    private boolean isHeldDown_A;
    private boolean isHeldDown_D;

    private final Map map;
    //endregion members


    //region functions

    public void update()
    {
        float x = cam.position.x;
        float y = cam.position.y;
        if (isHeldDown_W) y += 25;
        if (isHeldDown_S) y -= 25;
        if (isHeldDown_A) x -= 25;
        if (isHeldDown_D) x += 25;
        cam.position.set(new Vector3(x, y, 0));
        cam.update();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        if (button == Input.Buttons.LEFT)
        {
            try
            {
                placeTile(x, y);
            }
            catch (InvalidVector2IntLimException e)
            {
                ExceptionHandler.outputException(e);
            }
        }
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        try
        {
            placeTile(screenX,screenY);
        }
        catch (InvalidVector2IntLimException e)
        {
            ExceptionHandler.outputException(e);
        }
        return false;
    }

    private void placeTile(int x, int y) throws InvalidVector2IntLimException
    {
        Vector3 targetPosV3 = cam.unproject(new Vector3(x, y, 0));
        Vector2IntLim targetPos = new Vector2IntLim(1,(int) targetPosV3.x, (int) targetPosV3.y);
        try
        {
            map.add(new Tile(0), targetPos);
        }
        catch (InvalidVector2IntLimException e)
        {
            e.addMessage("UserInput,touchDownLMB:");
            ExceptionHandler.outputException(e);
        }
    }






    @Override
    public boolean keyDown(int keycode)
    {
        //region WASD
        if (keycode == Input.Keys.W) isHeldDown_W = true;
        else if (keycode == Input.Keys.S) isHeldDown_S = true;
        else if (keycode == Input.Keys.A) isHeldDown_A = true;
        else if (keycode == Input.Keys.D) isHeldDown_D = true;
        //endregion WASD
        else if (keycode == Input.Keys.P)
        {
            map.getProject().save();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.W) isHeldDown_W = false;
        if (keycode == Input.Keys.S) isHeldDown_S = false;
        if (keycode == Input.Keys.A) isHeldDown_A = false;
        if (keycode == Input.Keys.D) isHeldDown_D = false;
        return false;
    }




    @Override
    public boolean scrolled(float amountX, float amountY)
    {
        if (amountY > 0)
        {
            cam.zoom += 0.1;
        }
        else
        {
            if(cam.zoom>0.2)
            {
                cam.zoom -= 0.1;
            }
        }
        return true;
    }

    //region notInUse

    @Override
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
