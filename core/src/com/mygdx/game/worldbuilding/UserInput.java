package com.mygdx.game.worldbuilding;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.map.Map;

public class UserInput implements InputProcessor
{
    public UserInput(OrthographicCamera cam, Map map)
    {
        super();
        this.cam = cam;
        editorController = new EditorController(map, cam);
        update();
    }

    private final OrthographicCamera cam;
    private final EditorController editorController;

    private boolean isHeldDown_W;
    private boolean isHeldDown_S;
    private boolean isHeldDown_A;
    private boolean isHeldDown_D;

    private boolean isHeldDown_LMB;


    public void update()
    {
        int x = 0;
        int y = 0;
        if (isHeldDown_W) y += 25;
        if (isHeldDown_S) y -= 25;
        if (isHeldDown_A) x -= 25;
        if (isHeldDown_D) x += 25;
        editorController.addToCamPosition(x,y);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        if (button == Input.Buttons.LEFT)
        {
            isHeldDown_LMB = true;
            editorController.placeTile(x, y);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (button == Input.Buttons.LEFT)
        {
            isHeldDown_LMB = false;
        }
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(isHeldDown_LMB)
        {
            editorController.placeTile(screenX, screenY);
        }
        return false;
    }




    @Override
    public boolean keyDown(int keycode)
    {
        if      (keycode == Input.Keys.W) isHeldDown_W = true;
        else if (keycode == Input.Keys.S) isHeldDown_S = true;
        else if (keycode == Input.Keys.A) isHeldDown_A = true;
        else if (keycode == Input.Keys.D) isHeldDown_D = true;

        else if (keycode == Input.Keys.P)
        {
            editorController.saveProject();
        }

        else if (keycode >=8 && keycode <=16)
        {
            editorController.changeTextureIndex(keycode-7);
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
            editorController.addToCamZoom(0.1);
        }
        else
        {
            if(editorController.getZoom()>0.2)
            {
                editorController.addToCamZoom(-0.1);
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
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }




    //endregion notInUse
}
