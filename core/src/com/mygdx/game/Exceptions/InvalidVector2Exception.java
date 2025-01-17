package com.mygdx.game.Exceptions;

public class InvalidVector2Exception extends CustomException
{
    public InvalidVector2Exception(float x,float y, String message)
    {
        this.message = message+x+", y:"+y+") at:";
    }
}
