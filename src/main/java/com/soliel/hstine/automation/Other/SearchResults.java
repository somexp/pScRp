package com.soliel.hstine.automation.Other;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HStine on 6/18/2018.
 */
public class SearchResults
{

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResults(WebDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
    }

    public List<String> getLinks()
    {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-result-list")));

        WebElement searchResults = driver.findElement(By.className("search-result-list"));

        List<WebElement> options = searchResults.findElements(By.className("search-result-indiv"));

        List<String> urls = new ArrayList<>();

        for (WebElement option : options)
        {
            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(option, By.xpath("div[1]/h3/a")));
            WebElement link = option.findElement(By.xpath("div[1]/h3/a"));
            String path = link.getAttribute("href");
            String url = "www.reuters.com" + path;
            System.out.println("-------" + url);
            urls.add(url);
        }

        return urls;
    }

    public void clean()
    {
        driver.quit();
    }
}
