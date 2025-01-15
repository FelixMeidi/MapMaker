package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Renders.Button;

import java.util.ArrayList;

public class UI
{

    public UI(SpriteBatch batch, OrthographicCamera cam)
    {
        this.batch = batch;
        this.cam = cam;
        buttonList = new ArrayList<Button>();
    }

    private SpriteBatch batch;

    private OrthographicCamera cam;

    private ArrayList<Button> buttonList;

    public void draw()
    {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        //test

        Texture tx = new Texture("assets/mapmaker_UI_file.png");
   //     batch.draw(tx,-(Gdx.graphics.getWidth()/2),(Gdx.graphics.getHeight()/2)-tx.getHeight());





        batch.end();
    }

    public boolean interact(int x,int y,int pointer,int button)
    {
        return false;
    }

    public void addButton(Button b)
    {
        buttonList.add(b);
    }
}
