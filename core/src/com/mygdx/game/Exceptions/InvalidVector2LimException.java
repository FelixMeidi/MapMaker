package com.mygdx.game.Exceptions;

public class InvalidVector2LimException extends CustomException
{
 ///TO BE EDITED
    public InvalidVector2LimException(float x, float y, int lim,String message)
    {
        this.message = message+"lim:"+lim+", x:"+x+", y:"+y+") at:";
    }
}
