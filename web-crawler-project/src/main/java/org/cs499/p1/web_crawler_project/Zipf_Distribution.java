package org.cs499.p1.web_crawler_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.*; 
import java.lang.*; 

public class Zipf_Distribution 
{
	private HashMap<String,Document> data;
	public HashMap<String,Integer> wordFrequency;
	public ArrayList<String> rankedWords;
	public List<Map.Entry<String, Integer>> list;
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
	
	//this method will store all word frequencies in the wordFrequency hashMap
	public void calculateFrequency()
	{
		Pattern checkAgainst=Pattern.compile("[a-zA-Z]+");  //this pattern will only accept alphabetical characters
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
		 // create list of every word mapped to its frequency
		 list = new ArrayList<Map.Entry<String, Integer> >(wordFrequency.entrySet());

        //Sort the list in descending order of frequency 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
        System.out.println(Arrays.toString(list.toArray()));
        
        rankedWords = new ArrayList<String>();
        // put the 100 most frequent words in rankedWord
        for (Map.Entry<String, Integer> frequency : list) { 
        	if (rankedWords.size() == 100)
        		break;
            rankedWords.add(frequency.getKey()); 
        } 
        
        System.out.println(Arrays.toString(rankedWords.toArray()));
	        
	}
	
	
}
