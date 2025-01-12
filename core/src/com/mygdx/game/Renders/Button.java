package com.mygdx.game.Renders;

public class Button
{
    public Button(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private int x;
    private int y;
    private int width;
    private int height;

    public boolean check(int targetX,int targetY)
    {
        if(targetX > x && targetX < x + width
        && targetY > y && targetY < y + height)
        {
            return true;
        }
        return false;
    }
}
