package de.meidi.game.tools.vector;

public class Vector2Int
{




    //region init
    public Vector2Int(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2Int()
    {
        x = 0;
        y = 0;
    }

    //endregion init





    //region members
    protected int x;
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }

    protected int y;
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    //endregion members









    //region functions





    //region add

    public Vector2Int addEquals(Vector2Int v2i)
    {
        return new Vector2Int(x+ v2i.x,y+ v2i.y);
    }
    public Vector2Int addEquals(int xAdd, int yAdd)
    {
        return new Vector2Int(x+ xAdd,y+ yAdd);
    }

    public void addOn(Vector2Int v2fl)
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

    public Vector2Int subEquals(Vector2Int v2i)
    {
        return new Vector2Int(x- v2i.x,y- v2i.y);
    }
    public Vector2Int subEquals(int xSub, int ySub)
    {
        return new Vector2Int(x- xSub,y- ySub);
    }

    public void subOn(Vector2Int v2fl)
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


    public Vector2Int abs()
    {
        Vector2Int v = new Vector2Int(x,y);
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





    //region divide

    public Vector2Int divideBy32eq()
    {
       return new Vector2Int(x/32,y/32);
    }
    public Vector2Int divideBy1024eq()
    {
        return new Vector2Int(x/1024,y/1024);
    }

    //endregion divide







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
