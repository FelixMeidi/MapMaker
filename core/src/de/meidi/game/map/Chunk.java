package de.meidi.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.meidi.game.tools.vector.Vector2IntLim;
import lombok.Getter;

@Getter
public class Chunk
{
    public Chunk()
    {
        tiles = new Tile[32][32];
        hasTiles = false;
    }






    private final Tile[][] tiles;

    private boolean hasTiles;







    public void addTile(Tile newTile, Vector2IntLim v2)
    {
        tiles[v2.getX()][v2.getY()] = newTile;
        hasTiles = true;
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
