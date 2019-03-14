package org.cs499.p1.web_crawler_project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import au.com.bytecode.opencsv.CSVWriter;
public class Web_Crawler
{
	private HashSet<String> visited;//will keep track of websites we visited (dont want to revisit)
	private int maxPages;
	private String fileName;
	private int docNumber;
	private File csvFile;
	private CSVWriter csv;
	private HashMap<String,Document> documentsHolder;
	public Web_Crawler() throws IOException
	
	{
		visited=new HashSet<String>();
		documentsHolder=new HashMap<String,Document>();
		maxPages=11;
		fileName="document";
		docNumber=0;
		csvFile=new File("src\\main\\report.csv");
		
		csv=new CSVWriter(new FileWriter(csvFile));
		csv.writeNext(new String[] {"URL","NumOutputLinks"});
		csv.close();
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
				int totalLinks=0;
				String[] writeToCSV=new String[2]; //will only contain url and numbered links.
				writeToCSV[0]=websiteURL;
				File file=new File("src\\main\\Resources\\"+nameFile);
				PrintWriter writer=new PrintWriter(file,"UTF-8");
				CSVWriter writeCSV=new CSVWriter(new FileWriter(csvFile,true));
				visited.add(websiteURL);  //add to visited
				Document webPage=Jsoup.connect(websiteURL).get(); //get the html code corresponding to webpage of url
				
				writer.write(webPage.toString());  //writing html to a file
				
				documentsHolder.put(nameFile,webPage);  
				docNumber++; 				
				Elements otherLinks=webPage.select("a[href]");  //get links on that page
						
				for(Element pageToVisit: otherLinks) //iterate through links listed on webpage
				{
					crawl(pageToVisit.attr("abs:href"),visited.size());  //this loop will go visit other urls.
					totalLinks++;
					//System.out.println(totalLinks);
				}
				writeToCSV[1]=Integer.toString(totalLinks);
				writeCSV.writeNext(writeToCSV);
				writeCSV.close();
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
	public HashMap<String,Document> getDocuments()
	{
		HashMap<String,Document> temp=(HashMap<String, Document>) documentsHolder.clone();//send copy of this
		return temp;
	}
	
}
