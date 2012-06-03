package com.ehilla.webscrapping;
import java.net.*;
import java.io.*;
import java.util.regex.*;
public class ScrapeYahooFinance
{    
    public static void main(String args[])
            throws Exception
    {
        String urlAddress = "http://finance.yahoo.com";
        URL url = new URL(urlAddress);
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = "";        
        Pattern pattern_01 = Pattern.compile("GBP/USD.*\n<td>\\d+.\\d+");
        Pattern pattern_02 = Pattern.compile("\\d+.\\d+$");
        String htmlPage = "";
        while ((line = urlReader.readLine()) != null)
        {
            htmlPage = htmlPage + line + "\n";
        }
        Matcher m1 = pattern_01.matcher(htmlPage);
        int numMatches = 0;
        String lastMatch = null;
        while(m1.find())
        {
            numMatches ++;
            Matcher m2 = pattern_02.matcher(m1.group());            
            while(m2.find())
            {
                lastMatch = m2.group().trim();
            }
        }
        if (numMatches == 1)
        {
            System.out.println("The matched result is: " + lastMatch);
        }
        else
            System.out.println("There are more than one match, please review your regex...");
    }
}