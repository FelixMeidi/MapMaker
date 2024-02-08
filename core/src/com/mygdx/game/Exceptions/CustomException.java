package com.mygdx.game.Exceptions;

public abstract class CustomException extends Exception
{
    public CustomException()
    {
        message = "This class should not be used";
    }

    public void addMessage(String messagetoadd)
    {
        message+=messagetoadd;
    }
    @Override
    public String getMessage()
    {
        return message;
    }

    public String message;
}
