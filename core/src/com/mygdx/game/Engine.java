package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.WorldBuilding.UserInputWB;

public class Engine extends ApplicationAdapter
{
    Layer l1;
    Texture t;
    Sprite s;
    private OrthographicCamera cam;

    @Override
    public void create()
    {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.update();
        l1 = new Layer(cam);
        t = new Texture("badlogic2.jpg");
        s = new Sprite(t);
    }

    @Override
    public void render()
    {
        ScreenUtils.clear(0, 0, 0, 1);
     //   UserInputWB.update(cam);
        l1.begin();
     //   l1.draw(s);
        cam.update();
        l1.end();
    }

    @Override
    public void dispose()
    {
        l1.dispose();
        t.dispose();
    }
}