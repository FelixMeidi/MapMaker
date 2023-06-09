package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Engine extends ApplicationAdapter
{
    Layer l1;
    Texture t;
    Sprite s;

    @Override
    public void create()
    {
        l1 = new Layer();
       t = new Texture("badlogic.jpg");
       s = new Sprite(t);
    }

    @Override
    public void render()
    {
        l1.begin();
   //     ScreenUtils.clear(0, 0, 0, 1);
//
    //    float x = s.getX();
    //    float y = s.getY();
     //   x+=100*Gdx.graphics.getDeltaTime();
    //    y+=100*Gdx.graphics.getDeltaTime();
     //   s.setPosition(x,y);
     //   l1.end();
    }

    @Override
    public void dispose()
    {
        l1.dispose();
        t.dispose();
    }
}