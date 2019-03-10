package org.cs499.p1.web_crawler_project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Web_Crawler
{
	private HashSet<String> visited;//will keep track of websites we visited (dont want to revisit)
	//private HashSet<String> frontier;
	private int maxPages;
	private String fileName;
	private int docNumber;
	public Web_Crawler()
	{
		visited=new HashSet<String>();
		//frontier=new HashSet<String>();
		maxPages=100;
		fileName="document";
		docNumber=0;
	}
	public void setMaxPages(int pages)
	{
		maxPages=pages;
		
	}
	public void crawl(String websiteURL, int max)
	{
		if(max==maxPages)
		{
			return;
		}
		else if(!visited.contains(websiteURL))
		{
			String nameFile=fileName+Integer.toString(docNumber)+".txt";   //creating new file to write html for EACH link
			try 
			{
				File file=new File("src\\main\\Resources\\"+nameFile);
				PrintWriter writer=new PrintWriter(file,"UTF-8");
				
				docNumber++; //gives each link its own txt file name
				
				visited.add(websiteURL);  //add to visited
				Document webPage=Jsoup.connect(websiteURL).get(); //get the html code corresponding to webpage of url
				writer.write(webPage.toString());  //writing html to a file
			//	System.out.print(webPage.toString());  //prints out html for you to test
				Elements otherLinks=webPage.select("a[href]");  //get links on that page
				System.out.println(visited.size());
				
				for(Element pageToVisit: otherLinks)
				{
					crawl(pageToVisit.attr("abs:href"),visited.size());  //this loop will go visit other urls.
				}
				
				writer.close();
			} 
			
			catch (IOException e)    
			{
				System.out.println("Error: "+websiteURL);
			}
			catch(IllegalArgumentException ex) //will capture anything that is NOT a link to another webpage (instead links to a picture for example, and ignores it)
			{
				
				return;
			}
		}
		
	}
	
	
}
