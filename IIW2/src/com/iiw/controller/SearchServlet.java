package com.iiw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.iiw.entities.University;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String country = request.getParameter("country");
		System.out.println(country);

		Gson gson = new Gson();
		ArrayList<University> univList = new ArrayList<University>();		
		for(Integer i =0 ;i<10;i++){
			University univ = new University();
			univ.setName("Univ-"+i.toString());
			univList.add(univ);
		}
		String Universities = gson.toJson(univList);
		String JSONUniversity = "{University : " + Universities + "}";
		System.out.println(JSONUniversity);
		response.setContentType("application/json");
		response.getWriter().write(Universities);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String country = request.getParameter("country");
//		Set<String> universities
		Gson gson = new Gson();
		String JSONUniversity = "{\"university\" : [ {\"name\" : \"denisUniv\"}," +
												    "{\"name\" : \"aswinUniv\"} " +
												  "]" +
							     "}";
		String json = gson.toJson(JSONUniversity);
		
		response.setContentType("application/json");
		request.setAttribute("jsonObject", json);
		request.getRequestDispatcher("/fillform.jsp").forward(request, response);
		
	}

}
