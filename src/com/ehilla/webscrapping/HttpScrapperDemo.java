/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehilla.webscrapping;

import java.net.URL;
import java.util.regex.Pattern;

/**
 *
 * @author hasanein
 */
public class HttpScrapperDemo implements HttpScrapperInterface
{
    private URL theURL;
    private Pattern pattern_01;
    private Pattern pattern_02;

    public HttpScrapperDemo()
    {
        theURL = null;
        pattern_01 = null;
        pattern_02 = null;
    }

    public HttpScrapperDemo(URL theURL, Pattern pattern_01, Pattern pattern_02)
    {
        this.theURL = theURL;
        this.pattern_01 = pattern_01;
        this.pattern_02 = pattern_02;
    }

    public void setSiteUrl(URL siteURL)
    {
        theURL = siteURL;
    }

    public String getURL()
    {
        return theURL.toExternalForm();
    }

    public void setFirstPattern(Pattern firstPattern)
    {
        this.pattern_01 = firstPattern;
    }

    public String getFirstPattern()
    {
        return this.pattern_01.toString();
    }

    public void setSecondPattern(Pattern secondPattern)
    {
        this.pattern_02 = secondPattern;
    }

    public String getSecondPattern()
    {
        return this.pattern_02.toString();
    }

    public static void main(String args[])
            throws Exception
    {
        HttpScrapperDemo httpDemo = new HttpScrapperDemo();
        URL siteURL = new URL("http://finance.yahoo.com");
        Pattern pattern_01 = Pattern.compile("GBP/USD.*\n<td>\\d+.\\d+");
        Pattern pattern_02 = Pattern.compile("\\d+.\\d+$");
        httpDemo.setFirstPattern(pattern_01);
        httpDemo.setSecondPattern(pattern_02);
        httpDemo.setSiteUrl(siteURL);
        System.out.println(httpDemo.getURL() + ":" + httpDemo.scrapIt());
    }

    public String scrapIt()
    {
        HttpScrapper httpScrapper = new HttpScrapper(theURL, pattern_01, pattern_02);
        return httpScrapper.scrapIt();
    }
}
