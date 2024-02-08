package com.mygdx.game.Engines;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Layer;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.WorldBuilding.UserInputWB;

public class WorldBuildingEngine extends ApplicationAdapter
{

    public SpriteBatch batch;
    Layer l1;
    private OrthographicCamera cam;
    UserInputWB userin;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        init();
        Layer.layerList.add(l1);
    }

    @Override
    public void render()
    {
        batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);
        Layer.drawAll(batch);
        userin.update();
        batch.end();
    }

    @Override
    public void dispose()
    {
        TextureLoader.dispose();
        batch.dispose();
    }


    public void init()
    {
        initAlways();
        initTemp();
    }

    private void initTemp()
    {

    }
    private void initAlways()
    {
        TextureLoader.loadTextures();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Map m = new Map();

        l1 = new Layer(cam,m);
        cam.zoom=0.5f;
        cam.update();
        userin = new UserInputWB(cam, m);
        Gdx.input.setInputProcessor(userin);
    }
}