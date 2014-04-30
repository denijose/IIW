package com.iiw.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import com.iiw.entities.*;
import com.iiw.rdf.Manage;

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
		RequestDispatcher rd = request.getRequestDispatcher("/fillform.jsp");  
		request.setAttribute("name",userName );
        rd.forward(request, response);
		
	}

}
