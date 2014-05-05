package com.iiw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter pw = response.getWriter();
		pw.write("heya!");
		
		//RequestDispatcher rd = request.getRequestDispatcher("/fillform.jsp");  
        //rd.forward(request, response);
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
		Student student = new Student();
		student.setName(userName);
		student.setURI(userURI);
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
		Gson gson = new Gson();
		categorySet = removeQuotes(categorySet);
		String categoryJSON = gson.toJson(categorySet);
		System.out.println(categoryJSON);
		RequestDispatcher rd = request.getRequestDispatcher("/fillform.jsp");  
		request.setAttribute("name",userName );
		request.setAttribute("categoryJSON", categoryJSON);
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
