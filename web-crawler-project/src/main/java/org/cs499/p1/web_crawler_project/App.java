package org.cs499.p1.web_crawler_project;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.nodes.Document;

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
       HashMap<String,Document> result=crawler.getDocuments();
       
//      for(String key:result.keySet())
//      {
//    	  System.out.println(result.get(key).toString());
//      }
       
       
     
      
    }
}
