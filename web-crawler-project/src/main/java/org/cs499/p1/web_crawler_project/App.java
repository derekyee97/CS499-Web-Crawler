package org.cs499.p1.web_crawler_project;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
       Web_Crawler crawler=new Web_Crawler();  
       crawler.crawl("https://www.cosmopolitan.com/food-cocktails/a23440/health-benefits-dark-chocolate/",0);
       HashMap<String,Document> result=crawler.getDocuments();
       Zipf_Distribution zipf=new Zipf_Distribution(result);
       zipf.calculateFrequency();   
       zipf.rankTopWords(); 
    }
}
