package com.mygdx.game.Exceptions;

public class InvalidAnchor32Exception extends Exception
{

    public InvalidAnchor32Exception()
    {
        message = "Invalid Anchor at: Vector2Int32:";
    }
    public InvalidAnchor32Exception addMessageReturn(String messagetoadd)
    {
        message+=messagetoadd;
        return this;
    }
    @Override
    public String getMessage()
    {
        return message;
    }

    public String message;
}
