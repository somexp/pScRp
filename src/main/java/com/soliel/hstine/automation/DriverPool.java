package com.soliel.hstine.automation;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HStine on 4/23/2018.
 */
public class DriverPool
{
    static List<WebDriver> drivers = new ArrayList<>();

    public static void addDriver(WebDriver driver)
    {
        drivers.add(driver);
    }

    public static void clearDrivers()
    {
        for (WebDriver driver : drivers)
        {
            try
            {
                driver.quit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
    }
}
