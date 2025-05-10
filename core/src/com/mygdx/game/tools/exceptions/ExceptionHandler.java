package com.mygdx.game.tools.exceptions;

public abstract class ExceptionHandler
{
    public static void outputException(CustomException e)
    {
        System.out.println(e.getMessage());
    }
}
