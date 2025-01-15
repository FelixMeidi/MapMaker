package com.mygdx.game.WorldBuilding;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Data.DataManager;
import com.mygdx.game.Exceptions.InvalidVector2Int1024Exception;
import com.mygdx.game.Exceptions.InvalidVector2Int32Exception;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Map.Tile;
import com.mygdx.game.ToBeRemoved.UI;
import com.mygdx.game.Vector.Vector2Int;

public class UserInput implements InputProcessor
{

    //region init

    public UserInput(OrthographicCamera cam, Map targetMap, UI ui)
    {
        super();
        this.cam = cam;
        this.targetMap = targetMap;
        this.ui = ui;

        update();
    }

    //endregion init


    //region members

    private final OrthographicCamera cam;
    private final Map targetMap;
    private final UI ui;

    private boolean isHeldDown_W;
    private boolean isHeldDown_S;
    private boolean isHeldDown_A;
    private boolean isHeldDown_D;

    //endregion members


    //region functions

    public void update()
    {
        //WASD
        float x = cam.position.x;
        float y = cam.position.y;
        if (isHeldDown_W) y += 25;
        if (isHeldDown_S) y -= 25;
        if (isHeldDown_A) x -= 25;
        if (isHeldDown_D) x += 25;

        cam.position.set(new Vector3(x, y, 0));

        //Gdx.input.setInputProcessor(this);/////////////////////
        cam.update();

        //
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        //check for UI
        if(ui.interact(x, y,pointer,button))
        {
            return false;
        }

        //check inputs
        if (button == Input.Buttons.LEFT)
        {
            placeTile(x, y);
        }
        else if (button == Input.Buttons.RIGHT)
        {
        }
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        placeTile(screenX,screenY);
        return false;
    }



    private void placeTile(int x, int y)
    {
        Vector3 targetPosV3 = cam.unproject(new Vector3(x, y, 0));
        Vector2Int targetPos = new Vector2Int((int) targetPosV3.x, (int) targetPosV3.y);
        try
        {
            targetMap.add(new Tile(0), targetPos);
        }
        catch (InvalidVector2Int32Exception | InvalidVector2Int1024Exception e)
        {
            e.addMessage("UserInput,touchDownLMB:");
            System.out.println(e.getMessage());
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
            DataManager.saveMap(targetMap);
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
            cam.zoom -= 0.1;
        }
        return true;
    }

    //region notInUse

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



    private void touchDownRMB(int x, int y){}

    //endregion notInUse


    //endregion functions
}
