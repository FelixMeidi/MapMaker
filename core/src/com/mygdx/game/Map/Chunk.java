package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Vector2Int;

public class Chunk
{

    //region init

    protected Chunk()
    {
        tiles = new Tile[32][32];
    }

    //endregion init


    //region members

    private Tile[][] tiles;

    //endregion members


    public void addTile(Tile t, Vector2Int v2)
    {
        tiles[v2.getX()][v2.getY()] = t;
    }


    public void draw(SpriteBatch batch,Vector2Int offset)
    {
        TextureRegion r;
        for (int c1 = 0; c1 < 32; c1++)
        {
            for (int c2 = 0; c2 < 32; c2++)
            {
                if(tiles[c1][c2]!=null)
                {
                    r = tiles[c1][c2].getTextureRegion();
                    batch.draw(r, offset.getX() + c1*32, offset.getY() + c2*32, r.getRegionWidth(), r.getRegionHeight());
                }
            }
        }

    }
}
