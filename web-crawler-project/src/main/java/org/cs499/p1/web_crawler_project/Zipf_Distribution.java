package org.cs499.p1.web_crawler_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Zipf_Distribution 
{
	private HashMap<String,Document> data;
	private HashMap<String,Integer> wordFrequency;
	private ArrayList<String> rankedWords;
	private String fileName;
	public Zipf_Distribution(HashMap<String,Document> documents)
	{
		data=documents;
		wordFrequency=new HashMap<String,Integer>();
	}
	public Zipf_Distribution()
	{
		data=null;
		wordFrequency=new HashMap<String,Integer>();
	}
	
	//this method will store all word frequencys in the wordFrequency hashMap
	public void calculateFrequency()
	{
		Pattern checkAgainst=Pattern.compile("[a-zA-Z]+");  //this pattern will only accept alhabetical characters
		Matcher matchAgainst;
		String temp,matchString;
		for(String key: data.keySet())  //going through documents downloaded 
		{
			temp=Jsoup.parse(data.get(key).toString()).text().toLowerCase();  //converting html from document to plain text
			
			matchAgainst=checkAgainst.matcher(temp);  //taking out special symbols and numbers 
			while(matchAgainst.find())                 //iterating through words found in text
			{
				matchString=matchAgainst.group().toString();
				if(!wordFrequency.containsKey(matchString))   //if word found is not accounted for 
				{
					wordFrequency.put(matchString,1);        //start keeping track of how many times we see this new word 
				
				}
				else   //if word already seen
				{
					wordFrequency.put(matchString,wordFrequency.get(matchString)+1); //increment
				}
			}
		}
			
		
	}
	public HashMap<String,Integer> getWordFrequency()
	{
		return (HashMap<String, Integer>) wordFrequency.clone();
	}
	public void rankTopWords()
	{
		
	}
	
	
}
