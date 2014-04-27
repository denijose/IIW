package com.iiw.wrapper;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class USNCustomWrapper {
	
	public USNCustomWrapper() throws IOException {
			// Initialize all the categories and specialities
			getOptions("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=10&program=");
	}
	
	public void getOptions(String url) throws IOException {
		Document website = Jsoup.connect(url).get();
		Elements categoriesList = website.select(".t-small");
		Elements categories = categoriesList.select("option");
		categories.remove(0);
		for (Element c : categories) {
			System.out.println(c.attr("value")+"\t"+c.text());
			getSpecialities(url+c.attr("value"));
		}
		//System.out.println(categories.attr("value")+"\t"+categories.text()+"\n");
	}
	
	public void getSpecialities(String url) throws IOException {
		Document website = Jsoup.connect(url).get();
		//System.out.println("Specialities of "+url);
		Elements specialitiesList = website.select("#gradSearchSpecialty");
		Elements specialities = specialitiesList.select("option");
		//System.out.println(specialitiesList);
		
		// Remove "Any Speciality" option
		if (specialities.size() != 0) {
			specialities.remove(0);
		}
		
		for (Element s : specialities) {
			System.out.println("\t"+s.attr("value")+"\t"+s.text());
		}
	}
	
	public void wrap(String url) throws IOException {
		Document website = Jsoup.connect(url).get();
		//Document website = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
    	Elements ranks = website.select(".rankscore-bronze");
    	Elements schools = website.select(".school-name");
    	Elements locations = website.select(".location");
    	Elements costs = website.select(".search_tuition");
    	Elements enrolleds = website.select(".total_enrolled");
    	
    	// Removing the table titles
    	//System.out.println(costs.get(0));
    	costs.remove(0);
    	//System.out.println(enrolleds.get(0));
    	enrolleds.remove(0);
    	
    	// The number of rows per page
    	//System.out.println(ranks.size()+" "+schools.size()+" "+locations.size()+" "+costs.size()+" "+enrolleds.size());
    	
    	String costsString = null;
    	Pattern pattern = Pattern.compile("(\\d+),(\\d+)");
    	Matcher matcher;
    	int[] tuitionValues = {0,0}; 
    	for (int i = 0; i < ranks.size();i++){
    		// <span class="rankscore-bronze"> <span title="Overall Score: 56 out of 100.">#25</span> </span>
    		// <a class="school-name" href="/best-graduate-schools/top-engineering-schools/college-of-engineering-02155">Pennsylvania State University—?University Park</a>
    		// <td class="column-odd table-column-odd  search_tuition  "> $19,304 <span class="rankings-costInfo">per year (in-state, full-time)</span>; $32,416 <span class="rankings-costInfo">per year (out-of-state, full-time)</span> <span class="footnote"> <a style="cursor: help" href=""> <sup></sup> </a> </span> </td>
    		costsString = costs.get(i).text();
    		matcher = pattern.matcher(costsString);
    		int index = 0;
    		while (matcher.find()) {
    			tuitionValues[index++] = Integer.parseInt(matcher.group(1)+matcher.group(2));
    		}
    		
    		System.out.println(ranks.get(i).text().replaceAll("#", "") +"\t"+ schools.get(i).attr("href") +"\t"+ schools.get(i).text().replaceAll("\u200B","").replaceAll("\u2014"," - ") +"\t"+ 
    		                   locations.get(i).text().replaceAll(", ","\t") +"\t"+ tuitionValues[0] +"\t"+ tuitionValues[1] +"\t"+ enrolleds.get(i).text().replaceAll(",",""));
    	}
	}

}
