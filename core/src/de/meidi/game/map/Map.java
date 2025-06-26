package de.meidi.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.meidi.game.tools.DataManager;
import de.meidi.game.tools.exceptions.InvalidVector2IntLimException;
import de.meidi.game.tools.vector.Vector2Int;
import de.meidi.game.tools.vector.Vector2IntLim;
import de.meidi.game.worldbuilding.Project;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

public class Map
{
    public Map()
    {
        mapChunkLists2D = new ArrayList<>();
        anchor = new Vector2IntLim(1024);
        base = 32;
        usedTextureIndexes = new ArrayList<>();
        usedTextureIndexesAmounts = new ArrayList<>();
    }



    @Setter
    private String name;
    public String getName()
    {
        if(name==null||name.isEmpty()) name = DataManager.findName("Input Map name...");
        return name;
    }

    @Getter
    //outer: x, inner: y
    private final ArrayList<ArrayList<Chunk>> mapChunkLists2D;
    @Getter
    private Vector2IntLim anchor;
    @Getter
    private final Integer base;

    @Getter
    public List<Integer> usedTextureIndexes;
    @Getter
    public List<Integer> usedTextureIndexesAmounts;



    @Getter
    @Setter
    private Project project;





    public void add(Tile t, Vector2IntLim targetCords) throws InvalidVector2IntLimException
    {
        Vector2IntLim chunk = new Vector2IntLim(1024 ,targetCords.getX() - (targetCords.getX() % 1024),targetCords.getY() - (targetCords.getY() % 1024));
        if(targetCords.getX()<0)
        {
            chunk.setX(chunk.getX()-1024);
        }
        if(targetCords.getY()<0)
        {
            chunk.setY(chunk.getY()-1024);
        }

        Vector2IntLim offsetVector;
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
                changeAnchor(offsetVector);
            }

        }
        catch(InvalidVector2IntLimException e)
        {
            e.addMessage("map,changeAnchor:");
            throw e;
        }
        Vector2IntLim anchorIndex = anchor.divideByLim();
        anchorIndex = anchorIndex.abs();
        Vector2IntLim realChunkIndex = anchorIndex.add(chunk.divideByLim());
        for(int c1 = 0; c1 <= realChunkIndex.getX(); c1++)
        {
            if(mapChunkLists2D.size()<=c1)
            {
                mapChunkLists2D.add(new ArrayList<Chunk>());
            }
        }
        for(int c1 = 0; c1 <= realChunkIndex.getY(); c1++)
        {
            if(mapChunkLists2D.get(realChunkIndex.getX()).size()<=c1)
            {
                mapChunkLists2D.get(realChunkIndex.getX()).add(new Chunk());
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
        Tile existingTile = mapChunkLists2D.get(realChunkIndex.getX()).get(realChunkIndex.getY()).getTiles()[arrayIndex.getX()][arrayIndex.getY()];

        mapChunkLists2D.get(realChunkIndex.getX()).get(realChunkIndex.getY()).addTile(t,new Vector2IntLim(1,arrayIndex.getX(),arrayIndex.getY()));

        if(existingTile!=null)
        {
            for (int c = 0; c < usedTextureIndexes.size(); c++)
            {
                if(usedTextureIndexes.get(c).equals(existingTile.getTexturesIndex()))
                {
                    usedTextureIndexesAmounts.set(c,usedTextureIndexesAmounts.get(c)-1);
                    if(usedTextureIndexesAmounts.get(c)==0)
                    {
                        usedTextureIndexesAmounts.remove(usedTextureIndexesAmounts.get(c));
                        usedTextureIndexes.remove(usedTextureIndexes.get(c));
                    }
                }
            }
        }
        addTextureUse(t.getTexturesIndex());
    }


    private void addTextureUse(Integer textureIndex)
    {
        boolean found = false;
        for (int c = 0; c < usedTextureIndexes.size(); c++)
        {
            if(usedTextureIndexes.get(c).equals(textureIndex))
            {
                usedTextureIndexesAmounts.set(c,usedTextureIndexesAmounts.get(c)+1);
                found = true;
            }
        }
        if(!found)
        {
            usedTextureIndexes.add(textureIndex);
            usedTextureIndexesAmounts.add(1);
        }
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



    private void changeAnchor(Vector2IntLim offsetVector) throws InvalidVector2IntLimException
    {
        Vector2IntLim indexOffset = offsetVector.divideByLim();
        for(int c1 = 0;c1 < mapChunkLists2D.size();c1++)
        {
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

}