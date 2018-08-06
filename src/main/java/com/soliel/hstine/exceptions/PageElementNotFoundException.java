package com.soliel.hstine.exceptions;

/**
 * Created by HStine on 1/23/2018.
 */
public class PageElementNotFoundException extends Exception
{
    public PageElementNotFoundException()
    {
        //Do nothing
    }

    public PageElementNotFoundException(String message)
    {
        super(message);
    }
}
