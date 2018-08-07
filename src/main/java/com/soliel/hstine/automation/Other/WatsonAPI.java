package com.soliel.hstine.automation.Other;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class WatsonAPI {

    NaturalLanguageUnderstanding service;
    public WatsonAPI()
    {
        service = new NaturalLanguageUnderstanding(
                "2018-08-07",
                "2cbd4b11-71a4-4f9e-8fce-38c66b167042",
                "UvTQSbp17My5"
        );
    }

    public void testAnalyze(String text)
    {
        EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        Features features = new Features.Builder()
                .entities(entitiesOptions)
                .keywords(keywordsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();
        System.out.println(response);
    }

    public void analyzeURL(String url)
    {
        try {
            EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                    .emotion(true)
                    .sentiment(true)
                    .limit(2)
                    .build();

            Features features = new Features.Builder()
                    .entities(entitiesOptions)
                    .build();

            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .url(url)
                    .features(features)
                    .build();

            AnalysisResults response = service
                    .analyze(parameters)
                    .execute();
            System.out.println(response);
        }
        catch (Exception e)
        {
            /** Keep silent about failed requests. Only a brief message and no stacktract. No point trying to resolve that nonsense. **/
            System.out.println("Exception for url: " + url);
            //e.printStackTrace();

        }
    }
}
