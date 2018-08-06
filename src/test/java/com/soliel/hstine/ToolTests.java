package com.soliel.hstine;

import com.soliel.hstine.automation.Other.GetCompanies;
import com.soliel.hstine.automation.Other.RPage;
import com.soliel.hstine.automation.Other.SearchResults;
import com.soliel.hstine.automation.Other.Watson;
import org.junit.Test;

import java.util.List;

public class ToolTests {

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
