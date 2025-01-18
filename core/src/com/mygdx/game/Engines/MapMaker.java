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
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.mygdx.game.Exceptions.InvalidVector2IntLimException;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Project;
import com.mygdx.game.Textures.TextureLoader;
import com.mygdx.game.ToBeRemoved.InvalidVector2Int32Exception;
import com.mygdx.game.ToBeRemoved.Layer;
import com.mygdx.game.WorldBuilding.UserInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class MapMaker extends ApplicationAdapter
{

    public SpriteBatch batchDraw;

    private OrthographicCamera TextureCam;

    private UserInput userin;

    private Viewport vp;

    private Map m;

    private JFrame mainMenu;

    private boolean activeProject;

    private Project currentProject;

    @Override

    public void create()
    {

        activeProject = false;

        //look for project

        mainMenu();


        //open project


    }








    private final int menuSizeX = 400;

    private final int menuSizeY = 800;



    private void mainMenu()
    {
        //JFrame
        mainMenu = new JFrame();
        mainMenu.setLocation(Gdx.graphics.getDisplayMode().width/2 -menuSizeX/2,Gdx.graphics.getDisplayMode().height/2 -menuSizeY/2);
        mainMenu.getContentPane().setLayout(null);
        mainMenu.setSize(400,800);
        mainMenu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(!activeProject)
                {
                    Gdx.app.exit();
                    System.exit(0);
                }
            }
        });

        //Button: new Project

        Button buttonNewProject = new Button("New Project");
        buttonNewProject.setBackground(Color.white);
        buttonNewProject.setBounds(50,20,300,50);
        buttonNewProject.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                newProject();
            }
        });
        mainMenu.getContentPane().add(buttonNewProject);

        //Button: Open Project

        Button buttonOpenProject = new Button("Open Project");
        buttonOpenProject.setBackground(Color.white);
        buttonOpenProject.setBounds(50,90,300,50);
        buttonOpenProject.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        mainMenu.getContentPane().add(buttonOpenProject);
        mainMenu.getContentPane().setBackground(Color.black);
        mainMenu.setVisible(true);

    }











    public void initProject()
    {
        TextureLoader.loadTextures();
        batchDraw = new SpriteBatch();

        //cameras
        TextureCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        vp = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), TextureCam);

        m = new Map();
        TextureCam.zoom=0.5f;
        TextureCam.update();
        userin = new UserInput(TextureCam, m);
        Gdx.input.setInputProcessor(userin);
        vp.apply();
    }

    private void newProject()
    {
        //let user input name

        String title = JOptionPane.showInputDialog(null,"Input Project Title...");
        if(!title.equals(""))
        {
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

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                String dir = chooser.getSelectedFile().getAbsolutePath();

                //create project
                new File(dir+"/"+title).mkdirs();
                new File(dir+"/"+title+"/textures").mkdirs();

                currentProject = new Project(title);
                activeProject = true;
                mainMenu.dispatchEvent(new WindowEvent(mainMenu,WindowEvent.WINDOW_CLOSING));
                try
                {
                    Gdx.graphics.wait(1000);
                }
                catch (Exception e)
                {

                }
            }
        }
    }

    @Override
    public void render()
    {
        if(activeProject)
        {
            if(batchDraw==null)
            {
                initProject();
            }
            //prepare
            ScreenUtils.clear(0, 0, 0, 1);
            batchDraw.setProjectionMatrix(TextureCam.combined);
            batchDraw.begin();



            //draw

            //   ui.draw();
            try
            {
                m.draw(batchDraw);
            }
            catch (InvalidVector2IntLimException e)
            {
                throw new RuntimeException(e);
            }

            //end draw
            batchDraw.end();

            //input
            userin.update();
            TextureCam.update();
        }
    }

    @Override
    public void dispose()
    {
        if(activeProject)
        {
            TextureLoader.dispose();
            batchDraw.dispose();
            VisUI.dispose();
        }
        System.exit(0);
    }

    @Override
    public void resize(int width, int height)
    {
        if(activeProject)
        {
            if(batchDraw==null)
            {
                initProject();
            }
            vp.update(width,height);
        }
    }

}