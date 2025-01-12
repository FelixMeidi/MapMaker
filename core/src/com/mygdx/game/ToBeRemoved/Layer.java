package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.Map;

import java.util.ArrayList;

public class Layer
{

    public Layer(Camera cam, Map map)
    {
        this.cam = cam;
        this.map = map;
    }


    private final Camera cam;
    private final Map map;


    public void draw(SpriteBatch batch)
    {
        try
        {
            batch.setProjectionMatrix(cam.combined); //must be called before draw
/*
            cam.position.set(0,0,0);
            TextureRegion r = TextureLoader.get(1);
            batch.draw(r, 0,0, r.getRegionWidth(), r.getRegionHeight());
*/
            try
            {
                map.draw(batch);
            }
            catch (Exception e)
            {
                System.out.println("this shouldnt happen");
            }
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            batch.flush();
        }
    }

    public static ArrayList<Layer> layerList = new ArrayList<>();

    public static void drawAll(SpriteBatch batch)
    {
        for (Layer layer : layerList)
        {
            layer.draw(batch);
        }
    }

}
