/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehilla.webscrapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hasanein Khafaji
 */
public class HttpScrapper implements HttpScrapperInterface
{
    private URL urlAddress;
    private Pattern firstPattern, secondPattern;

    public HttpScrapper(URL urlAddress, Pattern firstPattern, Pattern secondPattern)
    {
        this.urlAddress = urlAddress;
        this.firstPattern = firstPattern;
        this.secondPattern = secondPattern;
    }

    public String scrapIt()
    {
        try
        {
            String line = "";
            String htmlPage = "";
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlAddress.openStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(urlAddress.openStream()));
            while ((line = urlReader.readLine()) != null)
            {
                htmlPage = htmlPage + line + "\n";
            }
            Matcher m1 = firstPattern.matcher(htmlPage);
            int numMatches = 0;
            String lastMatch = null;
            while (m1.find())
            {
                numMatches++;
                Matcher m2 = secondPattern.matcher(m1.group());
                while (m2.find())
                {
                    lastMatch = m2.group().trim();
                }
            }
            if (numMatches == 1)
            {
                return lastMatch;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            System.out.println("Error Detected...Printing the stack trace");
            e.printStackTrace();
            return null;
        }
    }
}
