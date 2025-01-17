package com.mygdx.game.ToBeRemoved;

import com.mygdx.game.Vector.Vector2Int;

public class Vector2Int32 extends Vector2Int
{

    //init

    public Vector2Int32(int x, int y) throws InvalidVector2Int32Exception
    {
        super(x,y);
        if(x%32!=0||y%32!=0) throw new InvalidVector2Int32Exception(x,y);
    }
    public Vector2Int32()
    {
        super();
    }


    //functions


    public Vector2Int32 addEquals(Vector2Int32 v2fl) throws InvalidVector2Int32Exception
    {
        return new Vector2Int32(x+ v2fl.x,y+ v2fl.y);
    }

    public Vector2Int32 abs32() throws InvalidVector2Int32Exception
    {
        try
        {
            Vector2Int32 v = new Vector2Int32(x, y);
            if (v.x < 0)
            {
                v.x = -v.x;
            }
            if (v.y < 0)
            {
                v.y = -v.y;
            }
            return v;
        }
        catch (Exception e)
        {

        }
        return null;
    }
}
