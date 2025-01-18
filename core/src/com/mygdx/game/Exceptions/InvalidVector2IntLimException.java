package com.mygdx.game.Exceptions;

public class InvalidVector2IntLimException extends CustomException
{
 ///TO BE EDITED
    public InvalidVector2IntLimException(float x, float y, int lim, String message)
    {
        this.message = message+"lim:"+lim+", x:"+x+", y:"+y+") at:";
    }
}
