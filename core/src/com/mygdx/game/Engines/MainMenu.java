package com.mygdx.game.Engines;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.WorldBuilding.MenuInput;

public class MainMenu extends ApplicationAdapter
{

 //   private OrthographicCamera cam;

  //  private MenuInput in;

 //   private Viewport vp;

    private Stage stage;

    private Table table;

    private Skin skin;


    public VisTextButton btx;


    public SpriteBatch batch;

    @Override

    public void create()
    {
        Gdx.app.log("Gdx version", com.badlogic.gdx.Version.VERSION);
        //UI
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        VisUI.load();

        table.setFillParent(true); //auto resize
        stage.addActor(table);

        btx = new VisTextButton("TEST");
        stage.addActor(btx);
        // table.setDebug(true);


        //initiating textures
    //   batch = new SpriteBatch();

        //cameras
  //      cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  //      vp = new ExtendViewport(cam.viewportWidth,cam.viewportHeight, cam);

        //input processor
      //  in = new MenuInput(cam,ui);
        ////////////

   //     vp.apply();

    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        /*

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
*/
    }






    @Override
    public void dispose()
    {
        TextureLoader.dispose();
       // batch.dispose();
        VisUI.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
       // vp.update(width,height);
        stage.getViewport().update(width, height, true);
    }
}