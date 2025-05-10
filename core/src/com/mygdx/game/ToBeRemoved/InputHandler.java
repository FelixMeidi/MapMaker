package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.tools.data.DataManager;
import com.mygdx.game.tools.exceptions.InvalidVector2IntLimException;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Map.Tile;
import com.mygdx.game.tools.vector.Vector2IntLim;

public class InputHandler extends Stage
{

    //region init

    public InputHandler(OrthographicCamera cam, Map targetMap)
    {
        super();
        this.cam = cam;
        this.targetMap = targetMap;

        update();
    }

    //endregion init


    //region members

    private final OrthographicCamera cam;
    private final Map targetMap;

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
        if(super.touchDown(x,y,pointer,button))return true;


        //check inputs
        if (button == Input.Buttons.LEFT)
        {
            try
            {
                System.out.println(x+"    "+y);
                placeTile(x, y);
            }
            catch (InvalidVector2IntLimException e)
            {
                throw new RuntimeException(e);
            }
        }
        else if (button == Input.Buttons.RIGHT)
        {
        }
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(super.touchDragged(screenX,screenY,pointer))return true;

        try
        {
            placeTile(screenX,screenY);
        }
        catch (InvalidVector2IntLimException e)
        {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void placeTile(int x, int y) throws InvalidVector2IntLimException
    {
        Vector3 targetPosV3 = cam.unproject(new Vector3(x, y, 0));
        Vector2IntLim targetPos = new Vector2IntLim(1,(int) targetPosV3.x, (int) targetPosV3.y);
        try
        {
            targetMap.add(new Tile(0), targetPos);
        }
        catch (InvalidVector2IntLimException e)
        {
            e.addMessage("UserInput,touchDownLMB:");
            System.out.println(e.getMessage());
        }
    }






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
        if(super.keyUp(keycode))return true;

        if (keycode == Input.Keys.W) isHeldDown_W = false;
        if (keycode == Input.Keys.S) isHeldDown_S = false;
        if (keycode == Input.Keys.A) isHeldDown_A = false;
        if (keycode == Input.Keys.D) isHeldDown_D = false;
        return false;
    }


    @Override
    public boolean scrolled(float amountX, float amountY)
    {
        if(super.scrolled(amountX,amountY))return true;

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

}
