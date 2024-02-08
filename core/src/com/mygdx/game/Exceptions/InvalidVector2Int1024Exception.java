package com.mygdx.game.Exceptions;

public class InvalidVector2Int1024Exception extends InvalidVector2Exception
{

    public InvalidVector2Int1024Exception(float x, float y)
    {
        super(x,y,"EXCEPTION: Invalid Vector2Int1024 ( x:");
    }

}
