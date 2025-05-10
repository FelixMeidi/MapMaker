package com.mygdx.game.tools.vector;

import com.mygdx.game.tools.exceptions.InvalidVector2IntLimException;

public class Vector2IntLim
{






    //region init

    public Vector2IntLim(int lim, int x, int y) throws InvalidVector2IntLimException
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
    public void setX(int x) throws InvalidVector2IntLimException
    {
        checkCoords(x);
        this.x = x;
    }

    protected int y;
    public int getY()
    {
        return y;
    }
    public void setY(int y) throws InvalidVector2IntLimException
    {
        checkCoords(y);
        this.y = y;
    }

    //endregion members






    //region functions


    private void checkCoords(int c) throws InvalidVector2IntLimException
    {
        if(c%lim!=0)throw new InvalidVector2IntLimException(x,y,lim,"Invalid coordinates for limited Vector2Int:");
    }

    public Vector2IntLim divideByLim() throws InvalidVector2IntLimException
    {
        return new Vector2IntLim(1,x/lim,y/lim);
    }



    //region add

    public Vector2IntLim add(Vector2IntLim v2i)throws InvalidVector2IntLimException
    {
        return new Vector2IntLim(lim, x+ v2i.x,y+ v2i.y);
    }
    public Vector2IntLim add(int xAdd, int yAdd) throws InvalidVector2IntLimException
    {
        return new Vector2IntLim(lim,x+ xAdd,y+ yAdd);
    }
/*
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

    */
    public Vector2IntLim abs() throws InvalidVector2IntLimException
    {
        Vector2IntLim v = new Vector2IntLim(lim,x,y);
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

    //endregion functions
}
