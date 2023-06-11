package com.mygdx.game.WorldBuilding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Engine;
import com.mygdx.game.Layer;

import java.util.ArrayList;
import java.util.List;

public class WorldBuildingEngine extends Engine
{


    Layer l1;
    private OrthographicCamera cam;
    UserInputWB userin;

    @Override
    public void create()
    {
        init();


        Layer.layerList.add(l1);
        l1.add(new Texture("32x32/CobbleStoneStreet.png"), 0, 0);
        l1.add(new Texture("32x32/Floor_Rock.png"), 32, 32);
    }

    @Override
    public void render()
    {
        ScreenUtils.clear(0, 0, 0, 1);
        Layer.drawAll();
        userin.update();
    }

    @Override
    public void dispose()
    {
        Layer.disposeAll();
    }


    public boolean init()
    {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      //  cam = new OrthographicCamera(300, 300);
        l1 = new Layer(cam);
        cam.update();
        userin = new UserInputWB(cam, l1, "32x32/CobbleStoneStreet.png");
        Gdx.input.setInputProcessor(userin);
        return true;
    }
}