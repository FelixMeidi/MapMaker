package com.mygdx.game.tools.exceptions;

public abstract class CustomException extends Exception
{
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
