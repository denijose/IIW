package com.iiw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.repository.RepositoryException;

import com.iiw.entities.User;
import com.iiw.rdf.Manage;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		System.out.println(request.getParameter("GREQScoreName"));
		String userURI = request.getParameter("userURIHiddenFieldName");
		String userName = request.getParameter("userNameHiddenFieldName");
		String GREQScore = request.getParameter("GREQScoreName");
		String GREVScore = request.getParameter("GREVScoreName");
		String GREAScore = request.getParameter("GREAScoreName");
		String ToeflScore = request.getParameter("ToeflScoreName");
		String country = request.getParameter("countryName");
		String stream = request.getParameter("streamName");
		User user = new User( userURI ,userName,  Integer.parseInt(GREQScore),	 Integer.parseInt(GREVScore),  Integer.parseInt(GREAScore),  Integer.parseInt(ToeflScore), country,stream);
		try {
			Manage.createLoginUser(user);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/user.jsp").forward(request, response);
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userURI = request.getParameter("userURI");
		System.out.println(request.getParameter("GREQScoreID"));
		System.out.println(request.getParameter("GREQScoreName"));
		System.out.println(request.getParameter("GREQScoreName"));
		System.out.println(request.getParameter("GREQScoreName"));
	}

}
