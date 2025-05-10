package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.mygdx.game.Map.Map;
import com.mygdx.game.WorldBuilding.Project;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.tools.vector.Vector2Int;

import javax.swing.*;
import java.io.File;

public class MapMaker extends ApplicationAdapter
{

    public SpriteBatch batchDraw;
    private OrthographicCamera cam;
    private Viewport vp;
    private Map m;
    private Project currentProject;
    private InputHandler inputHandler;
    private Skin skin;

    private final Vector2Int menuSize = new Vector2Int(400,500);

    @Override

    public void create()
    {



        mainMenu();
    }

    private void mainMenu()
    {
        Window w = new Window("Main Menu",skin);

        w.getTitleTable().setFillParent(true);
        w.setSize(menuSize.getX(),menuSize.getY());
        w.setResizable(false);
        w.setMovable(false);
        w.setPosition((inputHandler.getWidth() - menuSize.getX())/2,(inputHandler.getHeight()- menuSize.getY())/2);
        w.setVisible(true);
        //w.setTransform(true);




        TextButton bNew = new TextButton("New Project",skin);
        bNew.setTransform(true);
        bNew.setSize(300,300);
        bNew.setVisible(true);
        bNew.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                actor.remove();
            }
        });

        TextButton bOpen = new TextButton("Open Project",skin);
        bOpen.setTransform(true);
        bOpen.setSize(300,300);
        bOpen.setVisible(true);
        bOpen.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("Open Project");
            }
        });

        TextButton bExit = new TextButton("Exit",skin);
        bExit.setTransform(true);
        bExit.setSize(300,300);
        bExit.setVisible(true);
        bExit.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("Exit");
            }
        });



        w.add(bNew).size(300,50).pad(50);
        w.row();
        w.add(bOpen).size(300,50).pad(50);
        w.row();
        w.add(bExit).size(300,50).pad(50);


        inputHandler.addActor(w);
    }



    int roation = 0;





    public void initProject()
    {



    }

    private void newProject()
    {
        //let user input name


    }

    @Override
    public void render()
    {


        /**/
    }

    @Override
    public void dispose()
    {
        inputHandler.dispose();
        TextureLoader.dispose();
        //batchDraw.dispose();
        VisUI.dispose();

        System.exit(0);
    }

    @Override
    public void resize(int width, int height)
    {
        vp.update(width,height);
    }

}