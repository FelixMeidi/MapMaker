package de.meidi.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import de.meidi.game.worldbuilding.Project;
import de.meidi.game.tools.exceptions.ExceptionHandler;
import de.meidi.game.tools.exceptions.InvalidVector2IntLimException;
import de.meidi.game.map.Map;
import de.meidi.game.tools.TextureLoader;
import de.meidi.game.worldbuilding.UserInput;

public class EditorScreen implements Screen
{






    private UserInput userInput;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Project project;
    private Map map;






    @Override
    public void show()
    {
        project = new Project();
        TextureLoader.loadTexturesFromSheet();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        map = new Map();
        project.addMap(map);
        cam.update();
        userInput = new UserInput(cam, map);
        Gdx.input.setInputProcessor(userInput);
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
        userInput.update();
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
