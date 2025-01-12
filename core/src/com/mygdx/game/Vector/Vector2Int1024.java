package com.mygdx.game.Vector;

import com.mygdx.game.Exceptions.InvalidVector2Int1024Exception;

public class Vector2Int1024 extends Vector2Int
{

    //init

    public Vector2Int1024(int x, int y) throws InvalidVector2Int1024Exception
    {
        super(x,y);
        if(x%1024!=0||y%1024!=0) throw new InvalidVector2Int1024Exception(x,y);
    }
    public Vector2Int1024()
    {
        super();
    }


    //functions


    public Vector2Int1024 addEquals(Vector2Int1024 v2fl) throws InvalidVector2Int1024Exception
    {
        return new Vector2Int1024(x+ v2fl.x,y+ v2fl.y);
    }
}
