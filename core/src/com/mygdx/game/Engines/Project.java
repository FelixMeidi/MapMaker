package com.mygdx.game.Engines;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ToBeRemoved.Layer;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.WorldBuilding.UserInput;

public class Project extends ApplicationAdapter
{

    public SpriteBatch batchDraw;

    private OrthographicCamera TextureCam;

    private UserInput userin;

    private Viewport vp;

    private Layer l1;

 //   private UI ui;


    @Override

    public void create()
    {
        //initiating textures
        batchDraw = new SpriteBatch();
        TextureLoader.loadTextures();

        //cameras
        TextureCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        vp = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), TextureCam);


    //    ui = new UI(new SpriteBatch(),new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));



        Map m = new Map();
        l1 = new Layer(TextureCam,m);
        TextureCam.zoom=0.5f;
        TextureCam.update();
     //   userin = new UserInput(TextureCam, m,ui);
        Gdx.input.setInputProcessor(userin);
        Layer.layerList.add(l1);
        vp.apply();
    }

    @Override
    public void render()
    {
        //prepare
        ScreenUtils.clear(0, 0, 0, 1);
        batchDraw.setProjectionMatrix(TextureCam.combined);
        batchDraw.begin();

        //draw
        Layer.drawAll(batchDraw);
     //   ui.draw();

        //end draw
        batchDraw.end();

        //input
        userin.update();
        TextureCam.update();

    }

    @Override
    public void dispose()
    {
        TextureLoader.dispose();
        batchDraw.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        vp.update(width,height);
    }

}