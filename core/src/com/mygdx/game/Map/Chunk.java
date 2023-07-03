package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Exceptions.InvalidAnchor32Exception;
import com.mygdx.game.Vector2Int;
import com.mygdx.game.Vector2Int32;

public class Chunk extends Drawable
{
    protected Chunk(Vector2Int32 anchor32Local, Map rootMap)
    {
        this.rootMap = rootMap;
        tiles = new Tile[32][32];
        this.anchor32Local = anchor32Local;

    }

    private Tile[][] tiles;

    private Vector2Int32 anchor32Local;
    private Map rootMap;

    public void add(Tile t, Vector2Int v2)
    {
        tiles[v2.x][v2.y] = t;
    }
    public void changeAnchorByOffset(Vector2Int32 offset) throws InvalidAnchor32Exception
    {
        try
        {
            anchor32Local = new Vector2Int32(offset.getX() + anchor32Local.getX(), offset.getY() + anchor32Local.getY());
        }
        catch(InvalidAnchor32Exception e)
        {
            throw e.addMessageReturn("Chunk,changeAnchorByOffset:");
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        for (int c1 = 0; c1 < 32; c1++)
        {
            for (int c2 = 0; c2 < 32; c2++)
            {
                if(tiles[c1][c2]!=null)
                {
                    TextureRegion r = tiles[c1][c2].getTextureRegion();
                    Vector2Int32 globalAnchor = rootMap.convertAnchor32LocalToGlobal(anchor32Local);
                    batch.draw(r, globalAnchor.getX() + c1 + c2, globalAnchor.getY() + c1 + c2, r.getRegionWidth(), r.getRegionHeight());
                }
            }
        }

    }
}
