/*
 *   The main list is at http://www.edulix.com/unisearch/univreview.php
 *   Get the value of each state and hit the corresponding page. Like the below for CA
 *   http://www.edulix.com/unisearch/univreview.php?v=1&stid=36
 *   From that page, get the urls of the unversities and call the unipageparser function to get the students details and reviews.
 *   The student details can be found in the links like http://www.edulix.com/unisearch/user.php?uid=170074
 */

package com.iiw.wrapper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import com.iiw.entities.University;
import com.iiw.entities.Student;
import com.iiw.rdf.Manage;

public class EduScraper {
	
	public void uniLister() throws IOException {
		Document website = Jsoup.connect("http://www.edulix.com/unisearch/univreview.php").timeout(0).get();
		Elements dropdown = website.select("select[name=state]");
		//System.out.println(dropdown);
		Elements options = dropdown.select("option");
		//System.out.println(options);
		for (Element currentOption : options) {
			int value = 0;
			try {
				value = Integer.parseInt(currentOption.attr("value"));
			} catch (Exception e) {
				value = -1;
			}
			String state = currentOption.text();
			String stateURL = "http://www.edulix.com/unisearch/univreview.php?v=1&stid="+value;
			//System.out.println(value+"\t"+state);
			// REMOVE to unleash
			if (value==36)
			statePageParser(stateURL, state);
		}
	}
	
	public void statePageParser(String url, String state) throws IOException {
		Document website = Jsoup.connect(url).timeout(0).get();
		Elements universities = website.select(".hov_line");
		//System.out.println(universities);
		for (Element currentUni : universities) {
			String uniURL = "http://www.edulix.com/unisearch/"+ currentUni.attr("href");
			String uniName = currentUni.text();
			// REMOVE to unleash
			if (uniURL.contains("univid=153"))
			uniPageParser(uniURL, uniName, state);
		}
	}
	
	public void uniPageParser(String url, String name, String state) throws IOException {
		//Create the edulix University bean instance
		University edulixUni = new University(url,name,"USA",null,state,false);
		
		Document website = Jsoup.connect(url).timeout(0).get();
    	//Elements selection = website.select("body");
    	Elements sections = website.select(".tdtable");
    	
    	// TODO - Validation
    	Element students = sections.get(0);
    	Elements waitingList = students.select(".hov_line");
    	Elements admits = students.select(".admit");
    	Elements rejects = students.select(".reject");
    	
    	// TODO - Validation. The text has to be General Review
    	// reviews.text() -> The first two words should be General Review
    	Element reviews = sections.get(sections.size()-1);
    	
    	System.out.println("No of Students who applied : "+(waitingList.size()+admits.size()+rejects.size()));
    	System.out.println("No of Students who got admitted : "+admits.size());
    	System.out.println("No of Students who got rejected : "+rejects.size());
    	edulixUni.setNumOfAdmits(admits.size());
    	edulixUni.setNumOfRejects(rejects.size());
    	edulixUni.setNumOfWaiting(waitingList.size());
    	
    	ArrayList<Student> acceptedStudents = new ArrayList();
    	ArrayList<Student> rejectedStudents = new ArrayList();
    	//System.out.println(selection);
    	//System.out.println("Applied:");
    	//System.out.println(students.select(".hov_line"));
    	// No point in using the statistics of students who applied. Let's rather focus on the students who got admits and rejects.
    	System.out.println("Admits:");
    	// <a class="admit" href="user.php?uid=140692">appu05</a>
    	for (Element a : admits) {
    		Student currentStudent = new Student();
    		String studURI = "http://www.edulix.com/unisearch/"+a.attr("href");
    		
    		// Remove to unleash
    		if (a.text().equals("aswinasar")) {
    			System.out.println(studURI+" "+a.text());
    			currentStudent = studentPageParser(studURI, a.text());
    			acceptedStudents.add(currentStudent);
    		}
    	}
    	edulixUni.setAcceptedStudents(acceptedStudents);
    	
    	System.out.println("Rejects:");
    	// <a class="reject" href="user.php?uid=131912">51cent</a>
    	for (Element r : rejects) {
    		Student currentStudent = new Student();
    		String studURI = "http://www.edulix.com/unisearch/"+r.attr("href");
    		//Remove to unleash
    		if (r.text().equals("joshi")) {
    			System.out.println(studURI+" "+r.text());
    			currentStudent = studentPageParser(studURI, r.text());
    			rejectedStudents.add(currentStudent);
    		}
    	}
    	edulixUni.setRejectedStudents(rejectedStudents);
    	
    	Elements reviewers = reviews.select(".hov_line");
    	Elements contents = reviews.select(".tdborder");
    	
    	System.out.println("No of Reviews : "+reviewers.size());
    	System.out.println("Reviews:");
    	StringBuilder reviewsString = new StringBuilder();
    	Element currentReviewer;
    	Element currentContent;
    	for (int i = 0; i < reviewers.size(); i++) {
    		currentReviewer = reviewers.get(i);
    		// <a class="hov_line" href="user.php?uid=304"><b>sultan</b></a>
    		currentContent = contents.get(i+1);
    		// <td rowspan="2" valign="top" class="tdborder">Simply put....stay away.</td>
    		reviewsString.append((i+1)+" "+currentReviewer.attr("href")+" "+currentReviewer.text()+" "+currentContent.text()+"\n\n");
    	//System.out.println((i+1)+" "+currentReviewer.attr("href")+" "+currentReviewer.text()+" "+currentContent.text());
    	}
    	
    	System.out.println("Reviews: "+reviewsString.toString());
    	
    	edulixUni.setReviews(reviewsString.toString());
    	/*Elements selection = website.select(".hov_line");
    	for (Element e : selection) {
    		System.out.println(e.attr("href")+", "+e.text()+", "+e);
    	}*/
    	try {
			Manage.createEdulixUniversity(edulixUni);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(edulixUni.getAcceptedStudents().get(0).getDetails());
   
	}
	
	public Student studentPageParser(String url, String name) throws IOException {
		Student currentStudent = new Student();
		currentStudent.setURI(url);
		currentStudent.setName(name);
		
		int greQScore, greVScore, toeflScore; 
		double undergradScore, greAScore;
		greQScore = greVScore = toeflScore = 0;
		undergradScore = greAScore = 0.0;
		String details = "";
		
		String temp;
		
		Document website = Jsoup.connect(url).timeout(0).get();
		Elements content = website.select("tr");
		for (Element row: content) {
			Elements td = row.select("td");
			if (td.size() > 1){
				//System.out.println(td+"\n");
				if (td.get(0).text().equals("GRE/GMAT")) {
					temp = td.get(2).text();
					//System.out.println("Quantitative: "+td.get(2).text());
					if (!temp.equals(""))
						greQScore = Integer.parseInt(temp);
					temp = td.get(4).text();
					if (!temp.equals(""))
						greVScore = Integer.parseInt(temp);
					//System.out.println("Verbal: "+td.get(4).text());
					temp = td.get(6).text();
					if (!temp.equals(""))
						greAScore = Double.parseDouble(temp);
					//System.out.println("AWA: "+td.get(6).text());
				}
				
				if (td.get(0).text().equals("TOEFL")) {
					temp = td.get(2).text();
					//System.out.println("TOEFL: "+td.get(2).text());
					if (!temp.equals(""))
						toeflScore = Integer.parseInt(temp);
				}
				
				if (td.get(0).text().equals("Grade")) {
					//System.out.println("Undergrad Score: "+td.get(1).text());
					temp = td.get(1).text();
					if (!temp.equals(""))
						undergradScore = Double.parseDouble(temp);
				}
			}
		}
		Element stuff = website.select("table.tdborder").get(0);
		Element lastRow = stuff.select("tr").last();
		//System.out.println("User Details: "+lastRow.text());
		details = lastRow.text();
		currentStudent.setGreQScore(greQScore);
		currentStudent.setGreVScore(greVScore);
		currentStudent.setGreAScore(greAScore);
		currentStudent.setToeflScore(toeflScore);
		currentStudent.setUndergradScore(undergradScore);
		currentStudent.setDetails(details);
		return currentStudent;
	}
	
}
