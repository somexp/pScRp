package com.soliel.hstine.exceptions;

/**
 * Created by HStine on 1/23/2018.
 */
public class StalePageException extends Exception
{
    public StalePageException()
    {
        //Do nothing
    }

    public StalePageException(String message)
    {
        super(message);
    }
}
