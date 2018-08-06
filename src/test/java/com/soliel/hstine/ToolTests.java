package com.soliel.hstine;

import com.soliel.hstine.automation.Other.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ToolTests {

    @Test
    public void secondSearch()
    {
        try
        {
            List<String> urls = new ArrayList<>();
            GoogleResults gr = new GoogleResults();
            gr.conductSearch("intel");
            String url = gr.returnCurrentURL();
            urls.add(url);
            System.out.println("url: " + url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void firstSearch()
    {
        try {
            GetCompanies gComp = new GetCompanies();

            List<String> companies = gComp.getCompanies();

            for (String company : companies) {
                System.out.println("---" + company);
                RPage rPage = new RPage();
                SearchResults searchResults = rPage.conductSearch(company);

                List<String> urls = searchResults.getLinks();

                searchResults.clean();

                for (String url : urls)
                {
                    Watson wPage = new Watson();
                    wPage.analyze(url);

                }
            }
            //RPage rPage = new RPage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
