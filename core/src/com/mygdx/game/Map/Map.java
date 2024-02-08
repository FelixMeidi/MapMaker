package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Exceptions.InvalidVector2Int32Exception;

import com.mygdx.game.Vector2Int;
import com.mygdx.game.Vector2Int1024;
import com.mygdx.game.Vector2Int32;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@XmlRootElement
public class Map
{
    //outer: x, inner: y
    private ArrayList<ArrayList<Chunk>> mapChunkLists2D;
    private Vector2Int1024 anchor;

    public Map()
    {
        mapChunkLists2D = new ArrayList<>();
        anchor = new Vector2Int1024();
        testcount = 0;
    }

    public void add(Tile t, Vector2Int targetCords) throws InvalidVector2Int32Exception
    {

        //get chunk coordinates

        Vector2Int32 chunk = new Vector2Int32(targetCords.getX() - (targetCords.getX() % 1024),targetCords.getY() - (targetCords.getY() % 1024));

        if(targetCords.getX()<0)
        {
            chunk.setX(chunk.getX()-1024);
        }
        if(targetCords.getY()<0)
        {
            chunk.setY(chunk.getY()-1024);
        }
        //change anchor if needed
        Vector2Int32 offsetVector = null;
        try
        {
            offsetVector = new Vector2Int32();
            if(chunk.getX() < anchor.getX())
            {
                offsetVector.setX(chunk.getX());
            }
            if(chunk.getY() < anchor.getY())
            {
                offsetVector.setY(chunk.getY());
            }
            if(!offsetVector.isZero())
            {
                changeAnchor(offsetVector);
            }

        }
        catch(InvalidVector2Int32Exception e)
        {
            e.addMessage("Map,changeAnchor:");
            throw e;
        }

        Vector2Int anchorIndex = anchor.divideBy1024eq();
        anchorIndex = anchorIndex.abs();

        Vector2Int realChunkIndex = anchorIndex.addEquals(chunk.divideBy1024eq());


        Chunk targetChunk = null;
        for(int c1 = 0; c1 <= realChunkIndex.getX(); c1++)
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

        for(int c1 = 0; c1 <= realChunkIndex.getY(); c1++)
        {
            try
            {
                mapChunkLists2D.get(realChunkIndex.getX()).get(c1);
            }
            catch(Exception e)
            {
                mapChunkLists2D.get(realChunkIndex.getX()).add(c1, new Chunk());
            }
        }




        Vector2Int arrayIndex = new Vector2Int(targetCords.getX()/32, targetCords.getY()/32);
        System.out.println("1:                 "+targetCords.getX()+"   "+ targetCords.getY());
        if(arrayIndex.getX()<0)
        {
            arrayIndex.setX(arrayIndex.getX()+32);
        }
        if(arrayIndex.getY()<0)
        {
            arrayIndex.setY(arrayIndex.getY()+32);
        }

        System.out.println("2:                 "+arrayIndex.getX()+"   "+ arrayIndex.getY());
        mapChunkLists2D.get(realChunkIndex.getX()).get(realChunkIndex.getY()).addTile(t,new Vector2Int(arrayIndex.getX(),arrayIndex.getY()));

        testcount++;
        if(testcount>20)
        {

        }
    }

    private int testcount;
    private void changeAnchor(Vector2Int32 offsetVector) throws InvalidVector2Int32Exception
    {
        //creates list elements that arent needed yet
        Vector2Int indexOffset = offsetVector.divideBy1024eq();
        for(int c1 = 0;c1 < mapChunkLists2D.size();c1++)
        {
            mapChunkLists2D.get(c1);
            for(int c2 = 0;c2> indexOffset.getY();c2--)
            {
                mapChunkLists2D.get(c1).add(0,new Chunk());
            }
        }

        for(int c1 = 0;c1 > indexOffset.getX();c1--)
        {
            mapChunkLists2D.add(0,new ArrayList<Chunk>());
        }
        anchor.addOn(offsetVector);
    }

    public void draw(SpriteBatch batch) throws InvalidVector2Int32Exception
    {
        for(int c1 = 0; c1 < mapChunkLists2D.size(); c1++)
        {
            for(int c2 = 0; c2 < mapChunkLists2D.get(c1).size(); c2++)
            {
                mapChunkLists2D.get(c1).get(c2).draw(batch,anchor.addEquals(new Vector2Int32(c1*1024,c2*1024)));
            }
        }
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