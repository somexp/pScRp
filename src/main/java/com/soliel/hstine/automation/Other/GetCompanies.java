package com.soliel.hstine.automation.Other;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetCompanies {

    public GetCompanies()
    {
    }

    public List<String> getCompanies()
    {
        List<String> result = new ArrayList<>();

        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("companies").getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                result.add(line);
            }
            scanner.close();

        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        return result;
    }
}
