package com.soliel.hstine;

import com.soliel.hstine.automation.Other.*;
import org.junit.Test;

import java.util.*;

public class ToolTests {



    //@Test
    public void fifthSearch()
    {
        try
        {
            Date date1 = new Date();
            String dateString = date1.toString();
            String[] dateArray = dateString.split("\\s");

            System.out.println(dateArray[1]);
            System.out.println(dateArray[2]);
            System.out.println(dateArray[5]);

            Map<String, String> urlMap = new HashMap<>();
            Map<String, List<String>> searchLinks = new HashMap<>();


            SeekingAlpha sa = new SeekingAlpha();

            /**
             String comparator = "25,Feb,2014";

             System.out.println("Aug. 27, 2017 to " + comparator + " : " + sa.compareDates("Aug. 27, 2017", comparator));
             System.out.println("Sep. 15, 2015 to " + comparator + " : " + sa.compareDates("Sep. 15, 2015", comparator));
             System.out.println("Aug. 27, 2014 to " + comparator + " : " + sa.compareDates("Aug. 27, 2014", comparator));
             System.out.println("Mon, Apr. 30 to " + comparator + " : " + sa.compareDates("Mon, Apr. 30", comparator));
             System.out.println("Jan. 27, 2014 to " + comparator + " : " + sa.compareDates("Jan. 27, 2014", comparator));
             System.out.println("Aug. 27, 2010 to " + comparator + " : " + sa.compareDates("Aug. 27, 2010", comparator));
             System.out.println("Nov. 01, 2011 to " + comparator + " : " + sa.compareDates("Nov. 01, 2011", comparator));
             System.out.println("Tue, Jun. 19 to " + comparator + " : " + sa.compareDates("Tue, Jun. 19", comparator));
             System.out.println("Wed, Jun. 14 to " + comparator + " : " + sa.compareDates("Wed, Jun. 14", comparator));

             **/

            try {Thread.sleep(30);} catch (Exception e) { e.printStackTrace();}


            sa.conductSearch("HD");
            String url = sa.returnCurrentURL();
            urlMap.put("HD",url);
            List<String> articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("HD", articles);
            //sa1.cleanup();

            sa.conductSearch("DIS");
            url = sa.returnCurrentURL();
            urlMap.put("DIS",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("DIS", articles);
            //sa2.cleanup();

            sa.conductSearch("MSFT");
            url = sa.returnCurrentURL();
            urlMap.put("MSFT",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("MSFT", articles);
            //sa3.cleanup();

            sa.conductSearch("BA");
            url = sa.returnCurrentURL();
            urlMap.put("BA",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("BA", articles);
            //sa4.cleanup();

            sa.conductSearch("MMM");
            url = sa.returnCurrentURL();
            urlMap.put("MMM",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("MMM", articles);
            //sa5.cleanup();

            sa.conductSearch("PFE");
            url = sa.returnCurrentURL();
            urlMap.put("PFE",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("PFE", articles);
            //sa6.cleanup();

            sa.conductSearch("NKE");
            url = sa.returnCurrentURL();
            urlMap.put("NKE",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("NKE", articles);
            //sa7.cleanup();

            sa.conductSearch("JNJ");
            url = sa.returnCurrentURL();
            urlMap.put("JNJ",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("JNJ", articles);
            //sa8.cleanup();

            sa.conductSearch("MCD");
            url = sa.returnCurrentURL();
            urlMap.put("MCD",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("MCD", articles);
            //sa9.cleanup();

            sa.conductSearch("INTC");
            url = sa.returnCurrentURL();
            urlMap.put("INTC",url);
            articles = sa.getArticles("05,Jan,2018","15,Jun,2018");
            searchLinks.put("INTC", articles);
            //sa10.cleanup();

            WatsonAPI wa = new WatsonAPI();

            for (String key : searchLinks.keySet())
            {
                System.out.println(key + ": ");
                List<String> links = searchLinks.get(key);
                for (String link : links)
                {
                    wa.analyzeURL(link);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void fourthSearch()
    {
        try
        {
            Map<String, String> urlMap = new HashMap<>();
            Map<String, List<String>> searchLinks = new HashMap<>();


            SeekingAlpha sa = new SeekingAlpha();
            try {Thread.sleep(30);} catch (Exception e) { e.printStackTrace();}


            sa.conductSearch("HD");
            String url = sa.returnCurrentURL();
            urlMap.put("HD",url);
            List<String> articles = sa.getArticles(5);
            searchLinks.put("HD", articles);
            //sa1.cleanup();

            sa.conductSearch("DIS");
            url = sa.returnCurrentURL();
            urlMap.put("DIS",url);
            articles = sa.getArticles(5);
            searchLinks.put("DIS", articles);
            //sa2.cleanup();

            sa.conductSearch("MSFT");
            url = sa.returnCurrentURL();
            urlMap.put("MSFT",url);
            articles = sa.getArticles(5);
            searchLinks.put("MSFT", articles);
            //sa3.cleanup();

            sa.conductSearch("BA");
            url = sa.returnCurrentURL();
            urlMap.put("BA",url);
            articles = sa.getArticles(5);
            searchLinks.put("BA", articles);
            //sa4.cleanup();

            sa.conductSearch("MMM");
            url = sa.returnCurrentURL();
            urlMap.put("MMM",url);
            articles = sa.getArticles(5);
            searchLinks.put("MMM", articles);
            //sa5.cleanup();

            sa.conductSearch("PFE");
            url = sa.returnCurrentURL();
            urlMap.put("PFE",url);
            articles = sa.getArticles(5);
            searchLinks.put("PFE", articles);
            //sa6.cleanup();

            sa.conductSearch("NKE");
            url = sa.returnCurrentURL();
            urlMap.put("NKE",url);
            articles = sa.getArticles(5);
            searchLinks.put("NKE", articles);
            //sa7.cleanup();

            sa.conductSearch("JNJ");
            url = sa.returnCurrentURL();
            urlMap.put("JNJ",url);
            articles = sa.getArticles(5);
            searchLinks.put("JNJ", articles);
            //sa8.cleanup();

            sa.conductSearch("MCD");
            url = sa.returnCurrentURL();
            urlMap.put("MCD",url);
            articles = sa.getArticles(5);
            searchLinks.put("MCD", articles);
            //sa9.cleanup();

            sa.conductSearch("INTC");
            url = sa.returnCurrentURL();
            urlMap.put("INTC",url);
            articles = sa.getArticles(5);
            searchLinks.put("INTC", articles);
            //sa10.cleanup();

            WatsonAPI wa = new WatsonAPI();

            for (String key : searchLinks.keySet())
            {
                System.out.println(key + ": ");
                List<String> links = searchLinks.get(key);
                for (String link : links)
                {
                    System.out.println("     " + link);
                    wa.analyzeURL(link);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //@Test
    public void thirdSearch()
    {
        try
        {
            Map<String, String> urlMap = new HashMap<>();
            Map<String, List<String>> searchLinks = new HashMap<>();


            SeekingAlpha sa1 = new SeekingAlpha();
            sa1.conductSearch("HD");
            String url = sa1.returnCurrentURL();
            urlMap.put("HD",url);
            //sa1.cleanup();

            SeekingAlpha sa2 = new SeekingAlpha();
            sa2.conductSearch("DIS");
            url = sa2.returnCurrentURL();
            urlMap.put("DIS",url);
            //sa2.cleanup();

            SeekingAlpha sa3 = new SeekingAlpha();
            sa3.conductSearch("MSFT");
            url = sa3.returnCurrentURL();
            urlMap.put("MSFT",url);
            //sa3.cleanup();

            SeekingAlpha sa4 = new SeekingAlpha();
            sa4.conductSearch("BA");
            url = sa4.returnCurrentURL();
            urlMap.put("BA",url);
            //sa4.cleanup();

            SeekingAlpha sa5 = new SeekingAlpha();
            sa5.conductSearch("MMM");
            url = sa5.returnCurrentURL();
            urlMap.put("MMM",url);
            //sa5.cleanup();

            SeekingAlpha sa6 = new SeekingAlpha();
            sa6.conductSearch("PFE");
            url = sa6.returnCurrentURL();
            urlMap.put("PFE",url);
            //sa6.cleanup();

            SeekingAlpha sa7 = new SeekingAlpha();
            sa7.conductSearch("NKE");
            url = sa7.returnCurrentURL();
            urlMap.put("NKE",url);
            //sa7.cleanup();

            SeekingAlpha sa8 = new SeekingAlpha();
            sa8.conductSearch("JNJ");
            url = sa8.returnCurrentURL();
            urlMap.put("JNJ",url);
            //sa8.cleanup();

            SeekingAlpha sa9 = new SeekingAlpha();
            sa9.conductSearch("MCD");
            url = sa9.returnCurrentURL();
            urlMap.put("MCD",url);
            //sa9.cleanup();

            SeekingAlpha sa10 = new SeekingAlpha();
            sa10.conductSearch("INTC");
            url = sa10.returnCurrentURL();
            urlMap.put("INTC",url);
            //sa10.cleanup();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //@Test
    public void secondSearch()
    {
        try
        {
            Map<String, String> urlMap = new HashMap<>();
            List<String> urls = new ArrayList<>();

            GoogleResults gr = new GoogleResults();
            gr.conductSearch("Home Depot Company");
            String url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("HD",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Walt Disney Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("DIS",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Microsoft Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("MSFT",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Boeing Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("BA",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("3M Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("MMM",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Pfizer Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("PFE",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Nike Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("NKE",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Johnson & Johnson Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("JNJ",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("McDonalds Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("MCD",url);
            gr.cleanup();

            gr = new GoogleResults();
            gr.conductSearch("Intel Company");
            url = gr.returnCurrentURL();
            urls.add(url);
            urlMap.put("INTC",url);
            gr.cleanup();

            for (String key : urlMap.keySet())
            {
                String thisURL = urlMap.get(key);
                Watson watson = new Watson();
                watson.analyze(thisURL);
            }

            System.out.println("url: " + url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //@Test
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

    @Test
    public void testAnalyze()
    {
        String url = "https://seekingalpha.com/article/4190467-mcdonalds-best-fast-food-dividend-stocks";
        WatsonAPI wa = new WatsonAPI();
        wa.analyzeURL(url);
    }
}
