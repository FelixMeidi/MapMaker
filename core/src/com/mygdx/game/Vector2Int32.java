package com.mygdx.game;

import com.mygdx.game.Exceptions.InvalidAnchor32Exception;

public class Vector2Int32
{
    public Vector2Int32(int x, int y) throws InvalidAnchor32Exception
    {
        if(x%32!=0) throw new InvalidAnchor32Exception();
        if(y%32!=0) throw new InvalidAnchor32Exception();
        this.x = x;
        this.y = y;
    }
    public Vector2Int32()
    {
        this.x = 0;
        this.y = 0;
    }

    private int x;
    public int getX()
    {
        return x;
    }
    public void setX(int x) throws InvalidAnchor32Exception
    {
        if(x%32!=0) throw new InvalidAnchor32Exception();
        this.x = x;
    }

    private int y;
    public int getY()
    {
        return y;
    }
    public void setY(int y)  throws InvalidAnchor32Exception
    {
        if(y%32!=0) throw new InvalidAnchor32Exception();
        this.y = y;
    }
    public Vector2Int32 add(Vector2Int32 vectorToAdd)
    {
        int newx = x+ vectorToAdd.getX();
        int newy = y+ vectorToAdd.getY();
        try
        {
            return new Vector2Int32(newx,newy);
        }
        catch (InvalidAnchor32Exception e)
        {
            System.out.println("What happened?");
        }
        return null;
    }



    public boolean isZero()
    {
        if(x==0&&y==0) return true;
        return false;
    }
}
