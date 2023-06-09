package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Layer
{
    public Layer(SpriteBatch batch)
    {
 //       this.batch = batch;
    }
    public Layer()
    {
//        batch = new SpriteBatch();
   //     this.cam = cam;
    }

  //  private Camera cam;
    private SpriteBatch batch;

    public void draw(Sprite s)
    {
     //   try
     //   {
      //      batch.draw(s,s.getX(),s.getY());
      //      batch.setProjectionMatrix(cam.combined);
    //    }
     //   catch (NullPointerException e)
      //  {
     //       System.out.println(e.getLocalizedMessage());
     //   }
    }
    //region default stuff
    public void begin()
    {
   //     batch.begin();
    }
    public void end()
    {
  //      batch.end();
    }


    public void dispose()
    {
  //      batch.dispose();
    }
    //endregion default stuff
}
