package com.iiw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import com.iiw.wrapper.USNCustomWrapper;
import com.iiw.wrapper.USNewsWrapper;

public class USNWrapper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public USNWrapper() {
        super();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	System.out.println("Received request from "+request.getContextPath());
    	//new USNewsWrapper();
    	USNCustomWrapper usncw = new USNCustomWrapper();
    	//usncw.wrap("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=50&program=top-medical-schools&specialty=&name=&zip=&program_rank=Any&enrollment-min=0&enrollment-max=1000&tuition_and_fees-min=5000&tuition_and_fees-max=20000&sort=&sortdir=");
    	//usncw.wrapScienceAndArts("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=50&program=top-computer-science-schools&specialty=&name=&zip=&program_rank=Any&sort=&sortdir=");
    	//usncw.wrapScienceAndArts("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=50&program=top-computer-science-schools&specialty=artificial-intelligence-rankings&name=&zip=&program_rank=Any&sort=&sortdir=");
    	//usncw.wrap("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?sort=c_rank_final_overall_sort&sortdir=asc&spp=50&program=top-engineering-schools&page=3");
    	//usncw.wrap("http://grad-schools.usnews.rankingsandreviews.com/best-graduate-schools/search?spp=50&program=top-engineering-schools&sort=program_rank&sortdir=asc");
    	System.out.println("Finished processing.");
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
