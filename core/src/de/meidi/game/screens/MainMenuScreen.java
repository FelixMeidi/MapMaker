package de.meidi.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;

public class MainMenuScreen implements Screen
{

    public MainMenuScreen(Game master,Integer sizeX, Integer sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.master = master;
        stage = new Stage();
    }







    private final Game master;
    private final Integer sizeX;
    private final Integer sizeY;
    private final Stage stage;
    private Window window;
    private Skin skin;
    private ClickListener clickNewProject;
    private ClickListener clickOpenProject;
    private ClickListener clickExit;








    @Override
    public void show()
    {
        VisUI.load();
        skin = VisUI.getSkin();
        window = new Window("Main Menu",skin);

        Gdx.input.setInputProcessor(stage);
        initClickListeners();
        buildUI();
    }







    // region not in use

    @Override
    public void render(float delta)
    {
        stage.draw();
    }

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
        stage.dispose();
    }

    //endregion not in use








    private void buildUI()
    {
        //load UI skin


        //build main menu window

        window.getTitleTable().setFillParent(true);
        window.setSize(sizeX,sizeY);
        window.setResizable(false);
        window.setMovable(false);
        window.setPosition((stage.getWidth() - sizeX)/2,(stage.getHeight()- sizeY)/2);
        window.setTransform(true);
        //activate



        //button new project
        TextButton newProjectButton = newButton("New Project",skin);
        newProjectButton.addListener(clickNewProject);
        window.add(newProjectButton).size(300,50).pad(50);
        window.row();

        //button open project
        TextButton openProjectButton = newButton("Open Project",skin);
        openProjectButton.addListener(clickOpenProject);
        window.add(openProjectButton).size(300,50).pad(50);
        window.row();

        //button exit
        TextButton exitButton = newButton("Exit",skin);
        exitButton.addListener(clickExit);
        window.add(exitButton).size(300,50).pad(50);
        window.row();

        window.setVisible(true);
        stage.addActor(window);
    }

    private TextButton newButton(String title, Skin skin)
    {
        TextButton button = new TextButton(title,skin);
        button.setTransform(true);
        button.setSize(300,300);
        button.setVisible(true);
        return button;
    }
    
    private void initClickListeners()
    {
        clickNewProject = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                master.setScreen(new EditorScreen());
            }
        };
        
        clickOpenProject = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {


            }
        };
        
        clickExit = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        };
    }
}
