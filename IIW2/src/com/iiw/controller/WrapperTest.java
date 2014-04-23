package com.iiw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class WrapperTest
 */
public class WrapperTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrapperTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Received Request");
    	Document website = Jsoup.connect("http://www.edulix.com/unisearch/univreview.php?stid=36&univid=153").get();
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
    	
    	//System.out.println(selection);
    	//System.out.println("Applied:");
    	//System.out.println(students.select(".hov_line"));
    	// No point in using the statistics of students who applied. Let's rather focus on the students who got admits and rejects.
    	System.out.println("Admits:");
    	// <a class="admit" href="user.php?uid=140692">appu05</a>
    	for (Element a : admits) {
    		System.out.println(a.attr("href")+" "+a.text());
    	}
    	
    	System.out.println("Rejects:");
    	// <a class="reject" href="user.php?uid=131912">51cent</a>
    	for (Element r : rejects) {
    		System.out.println(r.attr("href")+" "+r.text());
    	}
    	
    	Elements reviewers = reviews.select(".hov_line");
    	Elements contents = reviews.select(".tdborder");
    	
    	System.out.println("No of Reviews : "+reviewers.size());
    	System.out.println("Reviews:");
    	
    	Element currentReviewer;
    	Element currentContent;
    	for (int i = 0; i < reviewers.size(); i++) {
    		currentReviewer = reviewers.get(i);
    		// <a class="hov_line" href="user.php?uid=304"><b>sultan</b></a>
    		currentContent = contents.get(i+1);
    		// <td rowspan="2" valign="top" class="tdborder">Simply put....stay away.</td>
    	System.out.println((i+1)+" "+currentReviewer.attr("href")+" "+currentReviewer.text()+" "+currentContent.text());
    	}
    	/*Elements selection = website.select(".hov_line");
    	for (Element e : selection) {
    		System.out.println(e.attr("href")+", "+e.text()+", "+e);
    	}*/
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
