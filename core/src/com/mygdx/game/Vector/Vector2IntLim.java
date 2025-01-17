package com.mygdx.game.Vector;

import com.mygdx.game.Exceptions.InvalidVector2LimException;

public class Vector2IntLim
{






    //region init

    public Vector2IntLim(int lim, int x, int y) throws InvalidVector2LimException
    {
        this.lim = lim;
        checkCoords(x);
        checkCoords(y);
        this.x = x;
        this.y = y;
    }

    public Vector2IntLim(int lim)
    {
        this.lim = lim;
        x = 0;
        y = 0;
    }

    //endregion init






    //region members

    private int lim;
    public int getLim(){return lim;}

    protected int x;
    public int getX()
    {
        return x;
    }
    public void setX(int x) throws InvalidVector2LimException
    {
        checkCoords(x);
        this.x = x;
    }

    protected int y;
    public int getY()
    {
        return y;
    }
    public void setY(int y) throws InvalidVector2LimException
    {
        checkCoords(y);
        this.y = y;
    }

    //endregion members






    //region functions


    private void checkCoords(int c) throws InvalidVector2LimException
    {
        if(c%lim!=0)throw new InvalidVector2LimException(x,y,lim,"Invalid coordinates for limited Vector2Int:");
    }

/*

    //region add

    public Vector2IntLim addEquals(Vector2IntLim v2i)
    {
        return new Vector2IntLim(x+ v2i.x,y+ v2i.y);
    }
    public Vector2IntLim addEquals(int xAdd, int yAdd)
    {
        return new Vector2IntLim(x+ xAdd,y+ yAdd);
    }

    public void addOn(Vector2IntLim v2fl)
    {
        this.x = x+ v2fl.x;
        this.y = y+ v2fl.y;
    }
    public void addOn(int xAdd, int yAdd)
    {
        this.x = x+ xAdd;
        this.y = y+ yAdd;
    }

    //endregion add

    //region sub

    public Vector2IntLim subEquals(Vector2IntLim v2i)
    {
        return new Vector2IntLim(x- v2i.x,y- v2i.y);
    }
    public Vector2IntLim subEquals(int xSub, int ySub)
    {
        return new Vector2IntLim(x- xSub,y- ySub);
    }

    public void subOn(Vector2IntLim v2fl)
    {
        this.x = x- v2fl.x;
        this.y = y- v2fl.y;
    }
    public void subOn(int xSub, int ySub)
    {
        this.x = x- xSub;
        this.y = y- ySub;
    }

    //endregion sub


    public Vector2IntLim abs()
    {
        Vector2IntLim v = new Vector2IntLim(x,y);
        if(v.x<0)
        {
            v.x = -v.x;
        }
        if(v.y<0)
        {
            v.y = -v.y;
        }
        return v;
    }




    public boolean isZero()
    {
        if(x == 0 && y == 0)
        {
            return true;
        }
        return false;
    }
    */
    //endregion functions
}
