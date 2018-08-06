package com.soliel.hstine.automation.Other;

import com.soliel.hstine.automation.DriverPool;
import com.soliel.hstine.exceptions.StalePageException;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by HStine on 6/18/2018.
 */
public class GoogleResults
{
    private String url = "https://www.google.com";
    private Properties configProperties;
    private WebDriver  driver;
    private WebDriverWait wait;
    private String browser = "firefox";
    boolean stale = true;

    public GoogleResults()
    {
        loadConfigurations();

        accessPage(browser);

        wait = new WebDriverWait(driver, 30);

        DriverPool.addDriver(driver);

        stale = false;
    }

    public String returnCurrentURL() throws StalePageException
    {
        if (stale == true) { throw new StalePageException("GPage is stale!!");}

        String url = driver.getCurrentUrl();

        return url;
    }

    public void conductSearch(String goal) throws StalePageException
    {
        if (stale == true) { throw new StalePageException("RPage is stale!!");}

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tsf")));

        WebElement el = driver.findElement(By.id("tsf"));

        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(el, By.tagName("input")));

        WebElement input = el.findElement(By.tagName("input"));

        wait.until(ExpectedConditions.elementToBeClickable(input));

        input.sendKeys(goal);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hplogo")));

        WebElement logo = driver.findElement(By.id("hplogo"));

        wait.until(ExpectedConditions.elementToBeClickable(logo));

        logo.click();

        Point point = el.getLocation();

        System.out.println("X: " + point.x);
        System.out.println("Y: " + point.y);



        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gbqfbb")));

        WebElement feelingLucky = driver.findElement(By.id("gbqfbb"));

        WebElement parent = feelingLucky.findElement(By.xpath(".."));

        WebElement submit = parent.findElement(By.xpath("input[1]"));

        wait.until(ExpectedConditions.elementToBeClickable(submit));

        System.out.println("type: " + submit.getAttribute("type"));

        System.out.println("value: " + submit.getAttribute("value"));

        submit.click();
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public void cleanup()
    {
        driver.quit();
    }

    private void accessPage(String browser)
    {
        if ("firefox".equals(browser))
        {
            accessFirefox();
        }
        else if ("chrome".equals(browser))
        {
            accessChrome();
        }
        else if ("edge".equals(browser))
        {
            accessEdge();
        }
        else if ("ie".equals(browser))
        {
            accessIE();
        }
    }


    private void accessFirefox()
    {
        String exePath = "" + configProperties.get("FIREFOX_DRIVER_PATH");
        String headlessMode = "" + configProperties.get("HEADLESS_MODE");
        String authentication= "" + configProperties.get("AUTHENTICATION");

        System.out.println("exePath: " + exePath);
        System.setProperty("webdriver.gecko.driver", exePath);
        System.out.println("Prop: " + System.getProperty("webriver.gecko.driver"));
        FirefoxBinary firefoxBinary = new FirefoxBinary();

        if ("on".equals(headlessMode))
        {
            firefoxBinary.addCommandLineOptions("--headless");
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            firefoxOptions.setBinary(firefoxBinary);

            driver = new FirefoxDriver(firefoxOptions);
        }
        else
        {
            driver = new FirefoxDriver();
        }



        driver.get(url);
    }


    private void accessChrome()
    {
        String chrPath = "" + configProperties.get("CHROME_DRIVER_PATH");
        String headlessMode = "" + configProperties.get("HEADLESS_MODE");
        String authentication= "" + configProperties.get("AUTHENTICATION");

        System.setProperty("webdriver.chrome.driver", chrPath);

        ChromeOptions chromeOptions = new ChromeOptions();;

        if ("on".equals(headlessMode))
        {
            chromeOptions.addArguments("headless");
        }

        driver = new ChromeDriver(chromeOptions);

        driver.get(url);
    }


    private void accessEdge()
    {
        String edgPath = "" + configProperties.get("EDGE_DRIVER_PATH");
        String headlessMode = "" + configProperties.get("HEADLESS_MODE");
        String authentication= "" + configProperties.get("AUTHENTICATION");

        System.setProperty("webdriver.edge.driver", edgPath);

        EdgeOptions edgeOptions = new EdgeOptions();;

        if ("on".equals(headlessMode))
        {
            //figure this out
        }

        driver = new EdgeDriver();

        driver.get(url);
    }


    private void accessIE()
    {
        String iePath = "" + configProperties.get("IE_DRIVER_PATH");
        String headlessMode = "" + configProperties.get("HEADLESS_MODE");
        String authentication= "" + configProperties.get("AUTHENTICATION");

        System.setProperty("webdriver.ie.driver", iePath);

        if ("on".equals(headlessMode))
        {
            //Something needs adding here
        }
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();

        driver.get(url);
    }

    private void loadConfigurations()
    {
        InputStream propertiesinput = getClass().getClassLoader().getResourceAsStream("ControlConfig.properties");
        configProperties = new Properties();
        try
        {
            configProperties.load(propertiesinput);
        }
        catch (IOException ioe)
        {
            System.out.println("Cannot access config properties file.");
            ioe.printStackTrace();
        }
        String pjsPath = "" + configProperties.get("PHANTOM_DRIVER_PATH");

        System.setProperty("phantomjs.binary.path", pjsPath);

        browser = ""+configProperties.get("DRIVER_OPTION")+"";
    }
}
