package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.exceptions.InvalidVector2IntLimException;
import com.mygdx.game.tools.vector.Vector2Int;
import com.mygdx.game.tools.vector.Vector2IntLim;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Map
{
    //outer: x, inner: y
    private ArrayList<ArrayList<Chunk>> mapChunkLists2D;
    private Vector2IntLim anchor;
    public final int chunkSize = 1024;

    public Map()
    {
        mapChunkLists2D = new ArrayList<>();
        anchor = new Vector2IntLim(1024);
    }

    public void add(Tile t, Vector2IntLim targetCords) throws InvalidVector2IntLimException
    {
        System.out.println(targetCords.getX()+"      "+ targetCords.getY());
        //get chunk coordinates

        Vector2IntLim chunk = new Vector2IntLim(1024 ,targetCords.getX() - (targetCords.getX() % 1024),targetCords.getY() - (targetCords.getY() % 1024));
        //System.out.println("1:   "+targetCords.getX()+"    "+targetCords.getY());
        //System.out.println("2:   "+chunk.getX()+"    "+chunk.getY());
        if(targetCords.getX()<0)
        {
            chunk.setX(chunk.getX()-1024);
        }
        if(targetCords.getY()<0)
        {
            chunk.setY(chunk.getY()-1024);
        }

        //region change anchor
        Vector2IntLim offsetVector = null;
        try
        {
            offsetVector = new Vector2IntLim(1024);
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
               // System.out.println(chunk.getX()+"    "+chunk.getY());
               // offsetVector.divideByLim();
               // offsetVector = new Vector2IntLim(1, offsetVector.getX()*1,offsetVector.getY()*1);
                changeAnchor(offsetVector);
            }

        }
        catch(InvalidVector2IntLimException e)
        {
            e.addMessage("Map,changeAnchor:");
            throw e;
        }
        //endregion change anchor

        Vector2IntLim anchorIndex = anchor.divideByLim();
        anchorIndex = anchorIndex.abs();
//
        Vector2IntLim realChunkIndex = anchorIndex.add(chunk.divideByLim());


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

//this fucking error


        Vector2Int arrayIndex = new Vector2Int(targetCords.getX(), targetCords.getY());
        boolean xisnegative = false;
        boolean yisnegative = false;
        if(arrayIndex.getX()<0)
        {
            xisnegative = true;
        }
        if(arrayIndex.getY()<0)
        {
            yisnegative = true;
        }
        arrayIndex = arrayIndex.abs();

        arrayIndex = new Vector2Int(arrayIndex.getX()%1024,arrayIndex.getY()%1024);
        arrayIndex = new Vector2Int(arrayIndex.getX()/32,arrayIndex.getY()/32);
        if(xisnegative)
        {
            arrayIndex.setX(-arrayIndex.getX());
            arrayIndex.setX(arrayIndex.getX()+31);
        }
        if(yisnegative)
        {
            arrayIndex.setY(-arrayIndex.getY());
            arrayIndex.setY(arrayIndex.getY()+31);
        }


        mapChunkLists2D.get(realChunkIndex.getX()).get(realChunkIndex.getY()).addTile(t,new Vector2IntLim(1,arrayIndex.getX(),arrayIndex.getY()));
    }
    private void changeAnchor(Vector2IntLim offsetVector) throws InvalidVector2IntLimException
    {
      //  System.out.println(offsetVector.getX()+"    "+offsetVector.getY());
        //creates list elements that arent needed yet
        Vector2IntLim indexOffset = offsetVector.divideByLim();
        System.out.println(indexOffset.getX()+"    "+indexOffset.getY());
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
        anchor = anchor.add(offsetVector);
    }

    public void draw(SpriteBatch batch) throws InvalidVector2IntLimException
    {
        for(int c1 = 0; c1 < mapChunkLists2D.size(); c1++)
        {
            for(int c2 = 0; c2 < mapChunkLists2D.get(c1).size(); c2++)
            {
                mapChunkLists2D.get(c1).get(c2).draw(batch,anchor.add(new Vector2IntLim(32,c1*1024,c2*1024)));
            }
        }
    }
}