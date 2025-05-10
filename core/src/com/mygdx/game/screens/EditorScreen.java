package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.WorldBuilding.Project;
import com.mygdx.game.tools.exceptions.ExceptionHandler;
import com.mygdx.game.tools.exceptions.InvalidVector2IntLimException;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.WorldBuilding.UserInput;

public class EditorScreen implements Screen
{
    private UserInput userin;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Map map;

    @Override
    public void show()
    {
        TextureLoader.loadTextures();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        map = new Map();
        cam.update();
        userin = new UserInput(cam, new Project(map));
        Gdx.input.setInputProcessor(userin);
    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(Color.CLEAR);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        try
        {
            map.draw(batch);
        }
        catch (InvalidVector2IntLimException e)
        {
            ExceptionHandler.outputException(e);
        }
        batch.end();
        cam.update();
        userin.update();
    }

    //region not in use

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        TextureLoader.dispose();
    }

    //endregion not in use
}
