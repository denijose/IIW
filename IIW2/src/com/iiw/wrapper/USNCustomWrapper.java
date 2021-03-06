package com.iiw.wrapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
	public static HashMap<String, String> states = new HashMap<String, String>();
	 
	public USNCustomWrapper() throws IOException {
		// Set the dictionary
		states.put("AL","Alabama");
		states.put("AK","Alaska");
		states.put("AB","Alberta");
		states.put("AS","American Samoa");
		states.put("AZ","Arizona");
		states.put("AR","Arkansas");
		states.put("AE","Armed Forces (AE)");
		states.put("AA","Armed Forces Americas");
		states.put("AP","Armed Forces Pacific");
		states.put("BC","British Columbia");
		states.put("CA","California");
		states.put("CO","Colorado");
		states.put("CT","Connecticut");
		states.put("DE","Delaware");
		states.put("DC","District Of Columbia");
		states.put("FL","Florida");
		states.put("GA","Georgia");
		states.put("GU","Guam");
		states.put("HI","Hawaii");
		states.put("ID","Idaho");
		states.put("IL","Illinois");
		states.put("IN","Indiana");
		states.put("IA","Iowa");
		states.put("KS","Kansas");
		states.put("KY","Kentucky");
		states.put("LA","Louisiana");
		states.put("ME","Maine");
		states.put("MB","Manitoba");
		states.put("MD","Maryland");
		states.put("MA","Massachusetts");
		states.put("MI","Michigan");
		states.put("MN","Minnesota");
		states.put("MS","Mississippi");
		states.put("MO","Missouri");
		states.put("MT","Montana");
		states.put("NE","Nebraska");
		states.put("NV","Nevada");
		states.put("NB","New Brunswick");
		states.put("NH","New Hampshire");
		states.put("NJ","New Jersey");
		states.put("NM","New Mexico");
		states.put("NY","New York");
		states.put("NF","Newfoundland");
		states.put("NC","North Carolina");
		states.put("ND","North Dakota");
		states.put("NT","Northwest Territories");
		states.put("NS","Nova Scotia");
		states.put("NU","Nunavut");
		states.put("OH","Ohio");
		states.put("OK","Oklahoma");
		states.put("ON","Ontario");
		states.put("OR","Oregon");
		states.put("PA","Pennsylvania");
		states.put("PE","Prince Edward Island");
		states.put("PR","Puerto Rico");
		states.put("PQ","Quebec");
		states.put("RI","Rhode Island");
		states.put("SK","Saskatchewan");
		states.put("SC","South Carolina");
		states.put("SD","South Dakota");
		states.put("TN","Tennessee");
		states.put("TX","Texas");
		states.put("UT","Utah");
		states.put("VT","Vermont");
		states.put("VI","Virgin Islands");
		states.put("VA","Virginia");
		states.put("WA","Washington");
		states.put("WV","West Virginia");
		states.put("WI","Wisconsin");
		states.put("WY","Wyoming");
		states.put("YT","Yukon Territory");
			// Initialize all the categories and specialities
			getOptions("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=50&program=");
	}
	
	public void getOptions(String url) throws IOException {
		Document website = Jsoup.connect(url).timeout(0).get();
		Elements categoriesList = website.select(".t-small");
		Elements categories = categoriesList.select("option");
		categories.remove(0);
		for (Element c : categories) {
			System.out.println(c.attr("value")+"\t"+c.text());
			Category cat = new Category(url+c.attr("value"),c.text());
			Set<Category> subCats = getSpecialities(url+c.attr("value"));
			
			// Remove hardcode
			//if (cat.getURI().contains("top-computer-science-schools")){
			
			try {
				Manage.createCategoryAndSubCategories(cat, subCats);
				wrapperEngine(cat,subCats);
			} catch (RepositoryException re) {
				System.out.println("Exception: RDF didn't like us.\n\n"+re.toString());
			}
			// REMOVE THE BREAK TO UNLEASH THE PROGRAM!
			//break;
			//}
		}
		//System.out.println(categories.attr("value")+"\t"+categories.text()+"\n");
	}
	
	public Set<Category> getSpecialities(String url) throws IOException {
		Document website = Jsoup.connect(url).timeout(0).get();
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
	
	public void wrapperEngine(Category cat, Set<Category> subCats) throws IOException {
		String uri = cat.getURI();
		System.out.println("cat - "+uri);
		if (uri.contains("program=top-business-schools") || uri.contains("program=top-engineering-schools") || uri.contains("program=top-education-schools") ||
				uri.contains("program=top-law-schools") || uri.contains("program=top-medical-schools")){
			wrap(uri, false, cat, null);
			for (Category c : subCats) {
				wrap(c.getURI(),true, cat, c);
				// REMOVE THE BREAK TO UNLEASH
				//break;
			}
		} else {
			/*wrapScienceAndArts(uri, false, cat, null);
			for (Category c : subCats) {
				wrapScienceAndArts(c.getURI(), true, cat, c);
			}*/
		}
		
	}
	
	public void wrap(String url, boolean subCategory, Category cat, Category subCat) throws IOException {
		do {
			
		Document website = Jsoup.connect(url).timeout(0).get();
		Elements column0, column1,column2,column3;
		
		if (!subCategory) {
			column0 = website.select(".col0");
			column1 = website.select(".col1");
			if (!url.contains("program=top-medical-schools")){
				column2 = website.select(".col2");
				column3 = website.select(".col3");
			} else {
				column2 = website.select(".col3");
				column3 = website.select(".col4");
    	}
		} else { // for subcategories, skip the program rank and get the specialty ranks
			column0 = website.select(".col0");
	    	column1 = website.select(".col2");
	    	if (!url.contains("program=top-medical-schools")){
	    	column2 = website.select(".col3");
	    	column3 = website.select(".col4");
	    	} else {
	    		column2 = website.select(".col4");
	        	column3 = website.select(".col5");
	    	}
		}
    	
    	// Removing the table titles
    	if (column0.size()>0) column0.remove(0);
    	if (column1.size()>0) column1.remove(0);
    	if (column2.size()>0) column2.remove(0);
    	if (column3.size()>0) column3.remove(0);
    	
    	String schoolURI,schoolName,cityState[],location,rankString,costString = "";
    	int rank,enrollments;
    	int[] feeInOut = {-1,-1};
    	//Pattern pattern = Pattern.compile("(\\d+),(\\d+)");
    	Pattern pattern = Pattern.compile("\\$(\\d+,*\\d+)");
    	Matcher matcher;
    	String schoolID = new String();
		String schoolIDPattern = ".*-(\\d*)";
		Pattern r = Pattern.compile(schoolIDPattern);
    	for (int i = 0; i < column0.size(); i++) {
    		
    		feeInOut[0] = feeInOut[1] = -1;
    		//schoolURI = "http://grad-schools.usnews.rankingsandreviews.com"+column0.get(i).select(".schoolname").attr("href");
    		schoolID = column0.get(i).select(".schoolname").attr("href");
    		Matcher schoolIDm = r.matcher(schoolID);
    		if (schoolIDm.find()) {
    		schoolURI = "http://example.com/gradSchoolID/"+schoolIDm.group(1);
    		} else {
    			schoolURI = "http://example.com/gradSchoolID/unknown";
    		}
    			
    		schoolName = column0.get(i).select(".schoolname").text();
        	location = column0.get(i).select(".citystate").text();
        	cityState = location.split(", ");  // splitting city and state
        	cityState[1] = states.get(cityState[1]);
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
    		
    		// Creating courseURI and specialtyURI
    		String courseURI = "", specialtyURI = "";
    		Pattern p = Pattern.compile("&program=([\\w-]+)&");
    		Matcher m = p.matcher(url);
    		if (m.find()) {
    		courseURI = "/"+m.group(1);
    		}
    		if (subCategory) {
    			Pattern p2 = Pattern.compile("&specialty=([\\w-]+)&");
    			Matcher m2 = p2.matcher(url);
    			if (m2.find()) {
    				specialtyURI = "/"+m2.group(1);
    			}
    		}
    		
    		System.out.println(schoolURI+"\t"+schoolName+"\t"+cityState[0]+"\t"+cityState[1]+"\t"+rank+"\t"+enrollments+"\t"+feeInOut[0]+"\t"+feeInOut[1]);
    		University university = new University(schoolURI,schoolName,"USA",cityState[0],cityState[1],false);
    		Course course = new Course(schoolURI+courseURI,cat.getName(),rank,feeInOut[0],feeInOut[1],enrollments);
    		
    		try {
    			if (!subCategory) {
    				Manage.createUnivCourseConnections(university, course, cat, null, null);
    				System.out.println(university.getName()+"\t"+course.getName()+"\t"+cat.getURI());
    			}
    			else {
    				Course subCourse = new Course(schoolURI+courseURI+specialtyURI,subCat.getName(),rank,feeInOut[0],feeInOut[1],enrollments);
    				Manage.createUnivCourseConnections(university, course, cat, subCourse, subCat);
    				System.out.println(university.getName()+"\t"+course.getName()+"\t"+cat.getURI()+"\t"+subCourse.getName()+"\t"+subCat.getURI());
    			}
    		} catch (Exception e) {
    			System.out.println("RDF call failed to create universities and courses");
    		}
    	}
    	
    	try {
    	Element last = website.select(".pager_link").last();
    	if (last.text().equals(">")) {  // If there is a next page link, set it as the new url 
    		url = "http://grad-schools.usnews.rankingsandreviews.com"+last.attr("href");
    		//System.out.println("new url is "+url);
    	} else {
    		url = null;
    		//System.out.println("new url is "+url);
    	}
    	} catch (NullPointerException npe ) {
    		url = null;
    	}
    	
		}
    	while(url!=null);
    	
	}

	
	public void wrapScienceAndArts(String url, boolean subCategory, Category cat, Category subCat) throws IOException {
		do {
		Document website = Jsoup.connect(url).timeout(0).get();
		Elements rows = website.select("tr");
		rows.remove(0);
		String schoolURI,schoolName,location,rankString = "";
		int rank;
    	String schoolID = new String();
		String schoolIDPattern = ".*-(\\d*)";
		Pattern r = Pattern.compile(schoolIDPattern);
		for (Element row : rows) {
			Element uni = row.select("td").first();
			//schoolURI = "http://grad-schools.usnews.rankingsandreviews.com"+uni.select(".schoolname").attr("href");
			schoolID = uni.select(".schoolname").attr("href");
    		Matcher schoolIDm = r.matcher(schoolID);
    		if (schoolIDm.find()) {
    		schoolURI = "http://example.com/gradSchoolID/"+schoolIDm.group(1);
    		} else {
    			schoolURI = "http://example.com/gradSchoolID/unknown";
    		}
    		
			schoolName = uni.select(".schoolname").text();
			location = uni.select(".citystate").text();
			String cityState[] = location.split(", ");
			cityState[1] = states.get(cityState[1]);
			rankString = row.select("td").last().text();
			rank = 0;
			try {
				rank = Integer.parseInt(rankString.replaceAll("#", ""));
			} catch (NumberFormatException nfe) {
				rank = -1;
			}
			
			// Creating course and subCourse URIs
			
			String courseURI = "", specialtyURI = "";
			
    		
    		if (subCategory) {
        		Pattern p = Pattern.compile("&program=([\\w-]+)&");
        		Matcher m = p.matcher(url);
        		if (m.find()) {
        		courseURI = "/"+m.group(1);
        		}
    			Pattern p2 = Pattern.compile("&specialty=([\\w-]+)");
    			Matcher m2 = p2.matcher(url);
    			if (m2.find()) {
    				specialtyURI = "/"+m2.group(1);
    			}
    		} else {
    			Pattern p = Pattern.compile("&program=([\\w-]+)");
        		Matcher m = p.matcher(url);
        		if (m.find()) {
        		courseURI = "/"+m.group(1);
        		}
    		}
    		
			System.out.println("CourseURI - "+schoolURI+courseURI+"\t"+"SubURI - "+schoolURI+courseURI+specialtyURI+"\t"+"UniversityURI - "+schoolURI);
			//System.out.println(schoolURI+"\t"+schoolName+"\t"+cityState[0]+"\t"+cityState[1]+"\t"+rank);
			University university = new University(schoolURI,schoolName,"USA",cityState[0],cityState[1],false);
    		Course course = new Course(schoolURI+courseURI,cat.getName(),rank,-1,-1,-1);
    		try {
    			if (!subCategory) {
    				Manage.createUnivCourseConnections(university, course, cat, null, null);
    				System.out.println(university.getName()+"\t"+course.getName()+"\t"+cat.getURI());
    			}
    			else {
    				Course subCourse = new Course(schoolURI+courseURI+specialtyURI,subCat.getName(),rank,-1,-1,-1);
    				Manage.createUnivCourseConnections(university, course, cat, subCourse, subCat);
    				System.out.println(university.getName()+"\t"+course.getName()+"\t"+cat.getURI()+"\t"+subCourse.getName()+"\t"+subCat.getURI());
    			}
    		} catch (Exception e) {
    			System.out.println("RDF call failed to create universities and courses");
    		}
		}
		
		try {
		Element last = website.select(".pager_link").last();
    	if (last.text().equals(">")) {  // If there is a next page link, set it as the new url 
    		url = "http://grad-schools.usnews.rankingsandreviews.com"+last.attr("href");
    	} else {
    		url = null;
    	}
		} catch (NullPointerException npe ) {
			url = null;
		}
		} while (url!=null);
	}
}
