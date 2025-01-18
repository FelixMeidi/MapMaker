package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.mygdx.game.Textures.TextureLoader;


import javax.swing.*;
import java.util.ArrayList;

public class MainMenu extends ApplicationAdapter
{
    private Stage stage;

    private Table table;

    private ArrayList<VisTextButton> buttonList;



    public SpriteBatch batch;

    @Override

    public void create()
    {

        //region UI

        buttonList = new ArrayList<>();

        Gdx.app.log("Gdx version", com.badlogic.gdx.Version.VERSION);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        VisUI.load();
        table.setFillParent(true); //auto resize
        stage.addActor(table);



        //region Buttons



        //region buttonNewProject
        VisTextButton buttonNewProject;
        buttonNewProject = new VisTextButton("New MapMaker");
        buttonNewProject.setWidth(300);
        buttonNewProject.setPosition(50,500);
        stage.addActor(buttonNewProject);

        buttonNewProject.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                newProject();
            }
        });
        buttonList.add(buttonNewProject);



        //endregion buttonNewProject




        //endregion Buttons




        //endregion UI







    }

    private void newProject()
    {
        //let user input name


        //get path
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e)
        {
            System.out.println("fuck, well whatever");
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select MapMaker Path");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            String dir = chooser.getSelectedFile().getAbsolutePath();

            //create project

        }

    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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