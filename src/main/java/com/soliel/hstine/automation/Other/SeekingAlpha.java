package com.soliel.hstine.automation.Other;

import com.soliel.hstine.automation.DriverPool;
import com.soliel.hstine.exceptions.StalePageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by HStine on 6/18/2018.
 */
public class SeekingAlpha
{
    private String endpoint = "https://seekingalpha.com";
    private Properties configProperties;
    private WebDriver  driver;
    private WebDriverWait wait;
    private String browser = "firefox";
    boolean stale = true;
    boolean showingSearchResults = false;

    public SeekingAlpha()
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

    public void conductSearch(String goal) throws StalePageException, Exception
    {
        if (stale == true) { throw new StalePageException("RPage is stale!!");}

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sa-search")));

        WebElement form = driver.findElement(By.id("sa-search"));


        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(form, By.id("hd-auto")));

        WebElement search = form.findElement(By.id("hd-auto"));

        wait.until(ExpectedConditions.elementToBeClickable(search));

        search.sendKeys(goal);


        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(form, By.tagName("button")));

        WebElement submitButton = form.findElement(By.tagName("button"));

        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        submitButton.click();

        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("headlines_")));

        }
        catch (Exception e)
        {
            showingSearchResults = false;

            throw new Exception("Search results fail to display!");
        }

        showingSearchResults = true;
    }

    public List<String> getArticles(int numOfArticles) throws StalePageException, Exception
    {
        if (stale == true) { throw new StalePageException("RPage is stale!!");}

        if (!showingSearchResults)
        {
            throw new Exception("No articles to get");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("headlines_")));

        WebElement articlesContainer = driver.findElement(By.id("headlines_"));

        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articlesContainer, By.xpath("div[1]/div[1]/ul[1]")));

        WebElement articlesList = articlesContainer.findElement(By.xpath("div[1]/div[1]/ul[1]"));

        List<WebElement> articleEls = articlesList.findElements(By.className("symbol_item"));

        List<String> links = new ArrayList<>();

        int index = 0;

        for (WebElement articleEl : articleEls)
        {
            if (index >= numOfArticles) {break;}
            /**
            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articleEl, By.className("content")));

            WebElement content = articleEl.findElement(By.className("content"));

            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(content, By.className("symbol_article")));

            WebElement symbArt = content.findElement(By.className("symbol_article"));

            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(symbArt, By.xpath("a[1]")));

            WebElement href = symbArt.findElement(By.xpath("a[1]"));
            **/

            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articleEl, By.xpath("div[2]/div[1]/a[1]")));

            WebElement href = articleEl.findElement(By.xpath("div[2]/div[1]/a[1]"));

            String path = href.getAttribute("href");

            //String link = endpoint + path;

            //System.out.println("Link: " + link);

            links.add(path);

            index++;
        }

        return links;
    }

    public List<String> getArticles(String beginDate, String endDate) throws StalePageException, Exception
    {
        if (stale == true) { throw new StalePageException("RPage is stale!!");}

        if (!showingSearchResults)
        {
            throw new Exception("No articles to get");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("headlines_")));

        WebElement articlesContainer = driver.findElement(By.id("headlines_"));

        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articlesContainer, By.xpath("div[1]/div[1]/ul[1]")));

        WebElement articlesList = articlesContainer.findElement(By.xpath("div[1]/div[1]/ul[1]"));

        List<WebElement> articleEls = articlesList.findElements(By.className("symbol_item"));

        List<String> links = new ArrayList<>();

        int index = 0;

        for (WebElement articleEl : articleEls)
        {


            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articleEl, By.xpath("div[2]/div[1]/div[1]")));

            WebElement dateEl = articleEl.findElement(By.xpath("div[2]/div[1]/div[1]"));

            String artMetaData = dateEl.getText();

            String[] metaData = artMetaData.split("•");

            String artDate = metaData[1];

            System.out.println("Date:" + artDate);
            if (index >= 10) {break;}
            /**
             wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articleEl, By.className("content")));

             WebElement content = articleEl.findElement(By.className("content"));

             wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(content, By.className("symbol_article")));

             WebElement symbArt = content.findElement(By.className("symbol_article"));

             wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(symbArt, By.xpath("a[1]")));

             WebElement href = symbArt.findElement(By.xpath("a[1]"));
             **/

            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(articleEl, By.xpath("div[2]/div[1]/a[1]")));

            WebElement href = articleEl.findElement(By.xpath("div[2]/div[1]/a[1]"));

            String path = href.getAttribute("href");

            links.add(path);

            index++;
        }

        return links;
    }

    //true=before; false=after or same
    //if year is 2015 and compareTo is 2017, return true
    private boolean compareYears(String year, String compareTo)
    {
        int yearInt = Integer.parseInt(year);
        int compareToInt = Integer.parseInt(compareTo);
        return yearInt < compareToInt;
    }

    //true=before; false=after or same
    //if day is 15 and compareTo is 10, return false
    private boolean compareDays(String day, String compareTo)
    {
        int dayInt = Integer.parseInt(day);
        int compareToInt = Integer.parseInt(compareTo);
        return dayInt < compareToInt;
    }

    //true=before; false=after or same
    //if month is Feb and compareTo is 10, return false
    private boolean compareMonths(String month, String compareTo)
    {

        int monthInt = 0;
        int compareToInt = 0;

        switch(month)
        {
            case "Jan":
                monthInt = 0;
            case "Feb":
                monthInt = 1;
            case "Mar":
                monthInt = 2;
            case "Apr":
                monthInt = 3;
            case "May":
                monthInt = 4;
            case "Jun":
                monthInt = 5;
            case "Jul":
                monthInt = 6;
            case "Aug":
                monthInt = 7;
            case "Sep":
                monthInt = 8;
            case "Oct":
                monthInt = 9;
            case "Nov":
                monthInt = 10;
            case "Dec":
                monthInt = 11;
        }

        switch(compareTo)
        {
            case "Jan":
                compareToInt = 0;
            case "Feb":
                compareToInt = 1;
            case "Mar":
                compareToInt = 2;
            case "Apr":
                compareToInt = 3;
            case "May":
                compareToInt = 4;
            case "Jun":
                compareToInt = 5;
            case "Jul":
                compareToInt = 6;
            case "Aug":
                compareToInt = 7;
            case "Sep":
                compareToInt = 8;
            case "Oct":
                compareToInt = 9;
            case "Nov":
                compareToInt = 10;
            case "Dec":
                compareToInt = 11;
        }

        return monthInt < compareToInt;
    }

    public boolean compareDates(String date, String compareTo)
    {
        String currentYear = "2018";

        String[] compareArray = compareTo.split(",");
        String compDay = compareArray[0];
        String compMon = compareArray[1];
        String compYear = compareArray[2];

        if (date.contains("Sun")
                ||date.contains("Mon")
                ||date.contains("Tue")
                ||date.contains("Wed")
                ||date.contains("Thu")
                ||date.contains("Fri")
                ||date.contains("Sat"))//date is current year
        {
            String year = currentYear;
            String[] segments = date.split(",");
            String remaining = segments[1];
            String[] halves = remaining.split("\\.");
            String month = halves[0].substring(1);
            String day = halves[1].substring(1);

            if (compareYears(year, compYear)) return true;
            if (compareYears(compYear, year)) return false;
            if (compareMonths(month, compMon)) return true;
            if (compareMonths(compMon, month)) return false;
            if (compareDays(day, compDay)) return true;
            if (compareDays(compDay, day)) return false;
            return false;
        }
        else if (date.contains("Yesterday")||date.contains("Today"))
        {
            Date date1 = new Date();
            String dateString = date1.toString();
            String[] dateArray = dateString.split("\\s");

            String month = dateArray[1];
            String day = dateArray[2];
            String year = dateArray[5];

            if (date.contains("Yesterday"))
            {
                int d = Integer.parseInt(day);
                d = d - 1;
                if (d <= 0)
                {
                    //Oh lord, this will be a challenge
                    //Replace the below with a change of both the month and the day to get an appropriate decrement
                    day = String.valueOf(d);
                }
                else {
                    day = String.valueOf(d);
                }
            }



            if (compareYears(year, compYear)) return true;
            if (compareYears(compYear, year)) return false;
            if (compareMonths(month, compMon)) return true;
            if (compareMonths(compMon, month)) return false;
            if (compareDays(day, compDay)) return true;
            if (compareDays(compDay, day)) return false;
            return false;
        }
        else
        {
            String[] segments = date.split(",");
            String year = segments[1].substring(1);
            String remaining = segments[0];
            String[] halves = remaining.split("\\.");
            String month = halves[0];
            String day = halves[1].substring(1);

            if (compareYears(year, compYear)) return true;
            if (compareYears(compYear, year)) return false;
            if (compareMonths(month, compMon)) return true;
            if (compareMonths(compMon, month)) return false;
            if (compareDays(day, compDay)) return true;
            if (compareDays(compDay, day)) return false;
            return false;
        }
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



        driver.get(endpoint);
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

        driver.get(endpoint);
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

        driver.get(endpoint);
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

        driver.get(endpoint);
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
