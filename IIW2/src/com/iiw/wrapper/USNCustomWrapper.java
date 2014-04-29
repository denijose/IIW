package com.iiw.wrapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openrdf.repository.RepositoryException;

import com.iiw.entities.*;
import com.iiw.rdf.*;

public class USNCustomWrapper {
	
	public USNCustomWrapper() throws IOException {
			// Initialize all the categories and specialities
			//getOptions("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=50&program=");
	}
	
	public void getOptions(String url) throws IOException {
		Document website = Jsoup.connect(url).get();
		Elements categoriesList = website.select(".t-small");
		Elements categories = categoriesList.select("option");
		categories.remove(0);
		for (Element c : categories) {
			System.out.println(c.attr("value")+"\t"+c.text());
			Category cat = new Category(url+c.attr("value"),c.text());
			Set<Category> subCats = getSpecialities(url+c.attr("value"));
			try {
				Manage.createCategoryAndSubCategories(cat, subCats);
			} catch (RepositoryException re) {
				System.out.println("Exception: RDF didn't like us.\n\n"+re.toString());
			}
		}
		//System.out.println(categories.attr("value")+"\t"+categories.text()+"\n");
	}
	
	public Set<Category> getSpecialities(String url) throws IOException {
		Document website = Jsoup.connect(url).get();
		//System.out.println("Specialities of "+url);
		Elements specialitiesList = website.select("#gradSearchSpecialty");
		Elements specialities = specialitiesList.select("option");
		//System.out.println(specialitiesList);
		Set<Category> subCats = new HashSet<Category>();
		// Remove "Any Speciality" option
		if (specialities.size() != 0) {
			specialities.remove(0);
		}
		
		for (Element s : specialities) {
			System.out.println("\t"+s.attr("value")+"\t"+s.text());
			Category cat = new Category(url+"&specialty="+s.attr("value"),s.text());
			subCats.add(cat);
		}
		return subCats;
	}
	
	public void wrap(String url) throws IOException {
		do {
		Document website = Jsoup.connect(url).get();
			
		Elements column0 = website.select(".col0");
    	Elements column1 = website.select(".col1");
    	Elements column2 = website.select(".col2");
    	Elements column3 = website.select(".col3");
    	
    	// Removing the table titles
    	if (column0.size()>0) column0.remove(0);
    	if (column1.size()>0) column1.remove(0);
    	if (column2.size()>0) column2.remove(0);
    	if (column3.size()>0) column3.remove(0);
    	
    	String schoolURI,schoolName,cityState[],location,rankString,costString;
    	int rank,enrollments;
    	int[] feeInOut = {-1,-1};
    	//Pattern pattern = Pattern.compile("(\\d+),(\\d+)");
    	Pattern pattern = Pattern.compile("\\$(\\d+,*\\d+)");
    	Matcher matcher;
    	
    	for (int i = 0; i < column0.size(); i++) {
    		feeInOut[0] = feeInOut[1] = -1;
    		schoolURI = column0.get(i).select(".schoolname").attr("href");
    		schoolName = column0.get(i).select(".schoolname").text();
        	location = column0.get(i).select(".citystate").text();
        	cityState = location.split(", ");  // splitting city and state
        	rankString = column1.get(i).text(); // #21 or Unranked or ...
        	costString = column3.get(i).text(); //$11,220 per year (in-state, full-time); $26,322 per year (out-of-state, full-time)
        	
        	try {
        		enrollments = Integer.parseInt(column2.get(i).text().replaceAll(",","")); //3,623 --> 3623
        	} catch (NumberFormatException nfe) {
        		enrollments = -1;
        	}
        	
    		try {
    			rank = Integer.parseInt(rankString.replaceAll("#", ""));
    		} catch (NumberFormatException nfe) {
    			rank = -1;
    		}
    		
    		//Extracting the currency
    		matcher = pattern.matcher(costString);
    		int index = 0;
    		while (matcher.find()) {
    			feeInOut[index++]=Integer.parseInt(matcher.group(1).replaceAll(",",""));
    		}
    		
    		System.out.println(schoolURI+"\t"+schoolName+"\t"+cityState[0]+"\t"+cityState[1]+"\t"+rank+"\t"+enrollments+"\t"+feeInOut[0]+"\t"+feeInOut[1]);
    	}
    	
    	Element last = website.select(".pager_link").last();
    	
    	if (last.text().equals(">")) {  // If there is a next page link, set it as the new url 
    		url = "http://grad-schools.usnews.rankingsandreviews.com"+last.attr("href");
    		//System.out.println("new url is "+url);
    	} else {
    		url = null;
    		//System.out.println("new url is "+url);
    	}
		}
    	while(url!=null);
    	
	}

}
