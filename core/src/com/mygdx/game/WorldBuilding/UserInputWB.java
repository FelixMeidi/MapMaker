package com.mygdx.game.WorldBuilding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Exceptions.InvalidVector2Int32Exception;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Map.Tile;
import com.mygdx.game.Vector2Int;

public class UserInputWB implements InputProcessor
{

    //region init

    public UserInputWB(OrthographicCamera cam, Map targetMap)
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
        float x = cam.position.x;
        float y = cam.position.y;
        if (isHeldDown_W) y += 5;
        if (isHeldDown_S) y -= 5;
        if (isHeldDown_A) x -= 5;
        if (isHeldDown_D) x += 5;

        cam.position.set(new Vector3(x, y, 0));
        Gdx.input.setInputProcessor(this);
        cam.update();
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        touchDownLMB(x, y, button);
        touchDownRMB(x, y, button);
        return false;
    }

    private void touchDownLMB(int x, int y, int button)
    {
        if (button == Input.Buttons.LEFT)
        {

            Vector3 v3 = cam.unproject(new Vector3(x, y, 0));

            Vector2Int v2 = new Vector2Int((int) v3.x, (int) v3.y);

            int xOver = 0;
            if (v2.getX() < 0)
            {
                xOver = (v2.getX() % 32)+32;
            }

            int yOver = 0;
            if (v2.getY() < 0)
            {
                yOver = (v2.getY() % 32)+32;
            }


            Vector2Int vfinal = new Vector2Int(v2.getX()-xOver, v2.getY()-yOver);


            try
            {
                targetMap.add(new Tile(0), vfinal);
            }
            catch (InvalidVector2Int32Exception e)
            {
                e.addMessage("UserInputWB,touchDownLMB:");
                System.out.println(e.getMessage());
            }


        }
    }


    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.W) isHeldDown_W = true;
        if (keycode == Input.Keys.S) isHeldDown_S = true;
        if (keycode == Input.Keys.A) isHeldDown_A = true;
        if (keycode == Input.Keys.D) isHeldDown_D = true;
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
            cam.zoom *= 1.1;
        }
        else
        {
            cam.zoom *= 0.9;
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
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    private void touchDownRMB(int x, int y, int button)
    {
        if (button == Input.Buttons.RIGHT)
        {

        }
    }

    //endregion notInUse


    //endregion functions
}
