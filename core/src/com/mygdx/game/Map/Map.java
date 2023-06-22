package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Exceptions.InvalidAnchor32Exception;
import com.mygdx.game.Vector2Int;
import com.mygdx.game.Vector2Int32;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@XmlRootElement
public class Map extends Drawable
{
    //outer: x, inner: y
    private ArrayList<ArrayList<Chunk>> mapChunkLists2D;
    private Vector2Int32 anchor;

    public Map()
    {
        mapChunkLists2D = new ArrayList<>();
        anchor = new Vector2Int32();
    }

    public void add(Tile t, Vector2Int targetCords) throws InvalidAnchor32Exception
    {
        //get chunk coordinates
        int overX = targetCords.x % 32;
        int chunkX = ((targetCords.x - overX)) - anchor.getX();
        int chunkXindex = chunkX / 32;

        int overY = targetCords.y % 32;
        int chunkY = ((targetCords.y - overY)) - anchor.getY();
        int chunkYindex = chunkY / 32;

        //change anchor if needed
        try
        {
            Vector2Int32 offsetVector = new Vector2Int32();
            if(chunkX < anchor.getX())
            {
                offsetVector.setX(chunkX);
            }
            if(chunkY < anchor.getY())
            {
                offsetVector.setY(chunkY);
            }
            if(!offsetVector.isZero())
            {
                changeAnchor(offsetVector);
            }

        }
        catch(InvalidAnchor32Exception e)
        {
            throw e.addMessageReturn("Map,add:");
        }
        Vector2Int anchorIndex = new Vector2Int(anchor.getX() / 32, anchor.getY() / 32);
        int realChunkIndexX = chunkXindex - anchorIndex.x;
        int realChunkIndexY = chunkYindex - anchorIndex.y;

        Chunk targetChunk = null;
        for(int c1 = 0; c1 < realChunkIndexX; c1++)
        {
            try
            {
                mapChunkLists2D.get(c1);
            }
            catch(Exception e)
            {
                mapChunkLists2D.add(new ArrayList<Chunk>());
            }
        }
        mapChunkLists2D.add(realChunkIndexX, new ArrayList<Chunk>());

        for(int c1 = 0; c1 < realChunkIndexY; c1++)
        {
            try
            {
                mapChunkLists2D.get(c1);
            }
            catch(Exception e)
            {
                mapChunkLists2D.get(realChunkIndexX).add(c1, new Chunk(new Vector2Int32(chunkXindex + anchor.getX(), c1 * 32 + anchor.getY()), this));

            }
            mapChunkLists2D.get(realChunkIndexX).add(c1, new Chunk(new Vector2Int32(chunkX, chunkY), this));

        }
        try
        {
            targetChunk = new Chunk(new Vector2Int32(chunkX, chunkY), this);
            mapChunkLists2D.get(realChunkIndexX).add(realChunkIndexY, targetChunk);
        }
        catch(InvalidAnchor32Exception e)
        {
            throw e.addMessageReturn("Map,add:");
        }

        if(targetChunk == null)
        {
            targetChunk = mapChunkLists2D.get(realChunkIndexX).get(realChunkIndexY);
        }
        targetChunk.add(t, new Vector2Int(targetCords.x % 32, targetCords.y % 32));
    }

    private void changeAnchor(Vector2Int32 offsetAnchor) throws InvalidAnchor32Exception
    {
        try
        {
            for(int c1 = 0; c1 < mapChunkLists2D.size(); c1++)
            {
                for(int c2 = 0; c2 < mapChunkLists2D.get(c1).size(); c2++)
                {
                    mapChunkLists2D.get(c1).get(c2).changeAnchorByOffset(offsetAnchor);
                }
            }
            anchor = new Vector2Int32(anchor.getX() + offsetAnchor.getX(), anchor.getY() + offsetAnchor.getY());
        }
        catch(InvalidAnchor32Exception e)
        {
            throw e.addMessageReturn("Map,changeAnchor:");
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        for(int c1 = 0; c1 < mapChunkLists2D.size(); c1++)
        {
            for(int c2 = 0; c2 < mapChunkLists2D.get(c1).size(); c2++)
            {
                mapChunkLists2D.get(c1).get(c2).draw(batch);
            }

        }
    }

    public Vector2Int32 convertAnchor32LocalToGlobal(Vector2Int32 anchor32Local)
    {
        return anchor32Local.add(anchor32Local);
    }

    //region static
    public void save(String path)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            File f = new File(path);
            if(f.exists()/* && !f.isDirectory()*/)
            {

            }
            else
            {

            }
            writer.close();
        }
        catch(IOException e)
        {

        }
    }

    public Map load(String path)
    {
        return null;
    }
    //endregion static
}