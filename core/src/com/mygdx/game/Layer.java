package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Layer
{

    //region constructors
    public Layer(SpriteBatch batch, Camera cam)
    {
        this.batch = batch;
        spritesList = new ArrayList<Sprite>();
    }

    public Layer(Camera cam)
    {
        batch = new SpriteBatch();
        this.cam = cam;
        spritesList = new ArrayList<Sprite>();
    }
    //endregion

    private Camera cam;
    private SpriteBatch batch;
    private ArrayList<Sprite> spritesList;

    //region methods

    //region add method
    public void add(Sprite s)
    {
        spritesList.add(s);
    }
    public void add(Texture t,int x, int y)
    {
        Sprite s = new Sprite(t);
        s.setPosition(x,y);
        spritesList.add(s);
    }
    public void add(Texture t) throws NullPointerException
    {
        if(t == null)
        {
            throw new NullPointerException();
        }
        spritesList.add(new Sprite(t,0,0));
    }

    //endregion

    public void draw()
    {
        try
        {
            begin();
            batch.setProjectionMatrix(cam.combined); //must be called before draw
            for (int c1 = 0; c1 < spritesList.size(); c1++)
            {

                Sprite s = spritesList.get(c1);
                batch.draw(s.getTexture(),s.getX(),s.getY());
            }

        } catch (NullPointerException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        finally
        {
            end();
        }
    }

    //region default stuff
    public void begin()
    {
        batch.begin();
    }

    public void end()
    {
        batch.end();
    }


    public void dispose()
    {
        batch.dispose();
        for (int c1 = 0; c1 < spritesList.size(); c1++)
        {
            spritesList.get(c1).getTexture().dispose();
            spritesList.remove(c1);
        }
    }
    //endregion default stuff



    //region static stuff

    public static ArrayList<Layer> layerList = new ArrayList<Layer>();

    public static void drawAll()
    {
        for (int c1 = 0; c1 < layerList.size(); c1++)
        {
            layerList.get(c1).draw();
        }
    }

    public static void beginAll()
    {
        for (int c1 = 0; c1 < layerList.size(); c1++)
        {
            layerList.get(c1).begin();
        }
    }

    public static void endAll()
    {
        for (int c1 = 0; c1 < layerList.size(); c1++)
        {
            layerList.get(c1).end();
        }
    }

    public static void disposeAll()
    {
        for (int c1 = 0; c1 < layerList.size(); c1++)
        {
            layerList.get(c1).dispose();
        }
    }

    //endregion

    //endregion
}
