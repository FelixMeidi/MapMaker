package com.mygdx.game.WorldBuilding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Layer;
import com.mygdx.game.Vector2Int;

public class UserInputWB implements InputProcessor
{


    public UserInputWB(Camera cam, Layer targetLayer, String targetTexturePath)
    {
        super();
        this.cam = cam;
        this.targetLayer = targetLayer;
        this.targetTexturePath = targetTexturePath;

        update();
    }

    private Camera cam;
    private Layer targetLayer;
    private String targetTexturePath;

    private boolean isHeldDown_W;
    private boolean isHeldDown_S;
    private boolean isHeldDown_A;
    private boolean isHeldDown_D;


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
    public boolean keyDown(int keycode)
    {
        if (keycode==Input.Keys.W) isHeldDown_W = true;
        if (keycode==Input.Keys.S) isHeldDown_S = true;
        if (keycode==Input.Keys.A) isHeldDown_A = true;
        if (keycode==Input.Keys.D) isHeldDown_D = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode==Input.Keys.W) isHeldDown_W = false;
        if (keycode==Input.Keys.S) isHeldDown_S = false;
        if (keycode==Input.Keys.A) isHeldDown_A = false;
        if (keycode==Input.Keys.D) isHeldDown_D = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        if (button == Input.Buttons.LEFT)
        {
            Vector3 v3 =  cam.unproject(new Vector3(x, y, 0));
            Vector2Int v2 = new Vector2Int((int)v3.x,(int)v3.y);
            int xOver;
            if(v2.x>0)
            {
                xOver  = v2.x%32;
            }
            else
            {
                xOver  = (v2.x%32)+32;
            }
            int yOver;
            if(v2.y>0)
            {
                yOver  = v2.y%32;
            }
            else
            {
                yOver  = (v2.y%32)+32;
            }
            System.out.println(xOver+ "                  "+yOver);
            targetLayer.add(new Texture(targetTexturePath),v2.x-xOver,v2.y-yOver);
        }
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

    @Override
    public boolean scrolled(float amountX, float amountY)
    {
        return false;
    }
}
