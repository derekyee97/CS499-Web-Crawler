package org.cs499.p1.web_crawler_project;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jsoup.nodes.Document;


public class XYSeriesDemo extends ApplicationFrame {

/**
* A demonstration application showing an XY series containing a null value.
*
* @param title  the frame title.
*/
public XYSeriesDemo(final String title, Zipf_Distribution zipf) {

   super(title);
   final XYSeries series = new XYSeries("Collected Data");
   double index = 0.0;
   for (Map.Entry<String, Integer> frequency : zipf.list) { 
	    double wordFreq = frequency.getValue();
   		series.add(wordFreq, index);
   		index += 1.0;
   } 

   final XYSeriesCollection data = new XYSeriesCollection(series);
   final JFreeChart chart = ChartFactory.createXYLineChart(
       "Word Frequency vs. Rank Plot",
       "Word Rank", 
       "Word Frequency", 
       data,
       PlotOrientation.VERTICAL,
       true,
       true,
       false
   );

   final ChartPanel chartPanel = new ChartPanel(chart);
   chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
   setContentPane(chartPanel);

}

public static void main(final String[] args) throws IOException {

	Web_Crawler crawler= new Web_Crawler();  
    crawler.crawl("https://www.nbcnews.com/think/opinion/bachelor-finale-colton-underwood-threw-out-rules-new-bachelorette-hannah-ncna982841",0);
    HashMap<String,Document> result=crawler.getDocuments();
    Zipf_Distribution zipf=new Zipf_Distribution(result);
    zipf.calculateFrequency(); 
    System.out.println("FREQUENCIES:");
    System.out.println(Arrays.asList(zipf.wordFrequency));
    System.out.println("RANK:");
    zipf.rankTopWords(); 
    
   final XYSeriesDemo demo = new XYSeriesDemo("CS499 Project 1", zipf);
   demo.pack();
   RefineryUtilities.centerFrameOnScreen(demo);
   demo.setVisible(true);

}

}