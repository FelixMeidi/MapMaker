package com.mygdx.game.ToBeRemoved;

import com.mygdx.game.Exceptions.InvalidVector2Exception;

public class InvalidVector2Int32Exception extends InvalidVector2Exception
{

    public InvalidVector2Int32Exception(float x, float y)
    {
        super(x,y,"EXCEPTION: Invalid Vector2Int32 ( x:");
    }

}
