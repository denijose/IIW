package com.iiw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import com.google.gson.Gson;
import com.iiw.entities.*;
import com.iiw.rdf.Manage;
import com.iiw.rdf.Sparql;

/**
 * Servlet implementation class Form
 */
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Form() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter pw = response.getWriter();
//		pw.write("heya!");
		
		Set<String> universitySet = new HashSet<String>();
		try {
			universitySet = Sparql.getAllUniversities();
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
		
		universitySet =  removeQuotes(universitySet);
		ArrayList<String> sorted = new ArrayList<String>(universitySet);
		Collections.sort(sorted);
		Gson gson = new Gson();
		String universityJSON = gson.toJson(sorted);
		RequestDispatcher rd = request.getRequestDispatcher("/shootOut.jsp");  
		request.setAttribute("universitiesJSON", universityJSON);
        rd.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userURI = request.getParameter("userURI");
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		System.out.println(userURI);
		System.out.println(userName);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		
		//check if it is a first time user
		User user = null;
		try {
			if( (user=Sparql.getUser(userURI))==null){
				//time to create the bugger muhahahha!!
				RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");  
				request.setAttribute("name",userName );
				request.setAttribute("userURI",userURI );
			    rd.forward(request, response);
			    return;
			}
		} catch (QueryEvaluationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedQueryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		String userJSON = gson.toJson(user);
		System.out.println(userJSON);
		
		
//		try {
//			Manage.createStudent(student);
//		} catch (RepositoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Set<String> categorySet = new HashSet<String>();
		try {
			categorySet = Sparql.getAllCategories();
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
		
		categorySet = removeQuotes(categorySet);
		String categoryJSON = gson.toJson(categorySet);
		System.out.println(categoryJSON);
		
		Set<String> universitySet = new HashSet<String>();
		try {
			universitySet = Sparql.getAllUniversities();
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
		universitySet =  removeQuotes(universitySet);
		String universityJSON = gson.toJson(universitySet);
		//System.out.println(universityJSON);
		//asdskjskls
		RequestDispatcher rd = request.getRequestDispatcher("/fillform.jsp");  
		request.setAttribute("name",userName );
		request.setAttribute("categoryJSON", categoryJSON);
		request.setAttribute("universitiesJSON", universityJSON);
        rd.forward(request, response);
		
	}
	
	private Set<String> removeQuotes(Set<String> categorySet){
		Set<String> newSet = new HashSet<String>();
		for(String s:categorySet){
			newSet.add(s.replaceAll("\"", ""));
		}
		return newSet;
	}

}
