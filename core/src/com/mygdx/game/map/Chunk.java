package com.mygdx.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.tools.vector.Vector2IntLim;

public class Chunk
{
    public Chunk()
    {
        tiles = new Tile[32][32];
    }







    private final Tile[][] tiles;







    public void addTile(Tile t, Vector2IntLim v2)
    {
        tiles[v2.getX()][v2.getY()] = t;
    }

    public void draw(SpriteBatch batch, Vector2IntLim offset)
    {
        for (int c1 = 0; c1 < tiles.length; c1++)
        {
            for (int c2 = 0; c2 < tiles.length; c2++)
            {
                if(tiles[c1][c2]!=null)
                {
                    tiles[c1][c2].draw(batch,offset,c1,c2);
                }
            }
        }

    }
}
