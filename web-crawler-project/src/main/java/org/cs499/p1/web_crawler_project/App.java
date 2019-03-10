package org.cs499.p1.web_crawler_project;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Web_Crawler crawler=new Web_Crawler();  
       crawler.crawl("https://www.oracle.com/java/",0);
       System.out.println("done");
      // File file=new File("C:\\Users\\derek\\git\\CS499-Web-Crawler\\web-crawler-project\\src\\main\\Resources"); //specific path, do not know how to make it flexible
      
    }
}
