package com.mygdx.game.Engines;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.ToBeRemoved.UI;
import com.mygdx.game.WorldBuilding.MenuInput;

import javax.swing.*;

public class MainMenu extends ApplicationAdapter
{

    private OrthographicCamera cam;

    private MenuInput in;

    private Viewport vp;

    private Stage stage;

    private Table table;

//    private UI ui;

 //   private JMenuBar menuBar;





    public SpriteBatch batch;

    @Override

    public void create()
    {
        //UI
        stage = new Stage();
        table = new Table();

        table.setFillParent(true); //auto resize
        stage.addActor(table);

        // table.setDebug(true);


        //initiating textures
        batch = new SpriteBatch();

        //cameras
    //    cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    //    vp = new ExtendViewport(cam.viewportWidth,cam.viewportHeight, cam);

        //input processor
   //     in = new MenuInput(cam,ui);
        Gdx.input.setInputProcessor(stage); ////////////

        vp.apply();



      /*  menuBar = new JMenuBar();


         JMenu menuItem = new JMenu("test");

        menuBar.add(menuItem);


        menuBar.updateUI();*/
    }

    @Override
    public void render()
    {
        //prepare
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        //draw
        //ui.draw();

        //end draw
        batch.end();

        //input
     //   in.update();
        cam.update();

    }

    @Override
    public void dispose()
    {
        TextureLoader.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
       // vp.update(width,height);
        stage.getViewport().update(width, height, true);
    }
}