package com.iiw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import com.google.gson.Gson;
import com.iiw.entities.University;
import com.iiw.rdf.Sparql;

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
		String category = request.getParameter("category");
		String feesRange = request.getParameter("feesRange");
		String GREScoreRange = request.getParameter("GREScoreRange");
		//System.out.println(country);
		//System.out.println(category);
        
		String JSON = new String();
		try {
			JSON = search(country,category,feesRange,GREScoreRange);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(JSON);
		response.setContentType("application/json");
		response.getWriter().write(JSON);
	}

	private String search(String country, String category, String feesRange, String GREScoreRange) throws RepositoryException, MalformedQueryException, QueryEvaluationException, JSONException {
		
		LinkedHashMap<University,String> details = Sparql.getUnivGivenCategory(category);
		JSONObject JSONObject = new JSONObject();
		JSONArray JSONArray = new JSONArray();
		JSONObject.put("Details",JSONArray );
		
		
		for(University u: details.keySet()){
			//System.out.println(u.getName()+ " - " + details.get(u));
			int secondPref = details.get(u).lastIndexOf("\"");
			String rank = details.get(u).substring(1, secondPref);
			JSONObject jsonU = new JSONObject(u);
			//JSONObject jsonR = new JSONObject(rank);
			//JSONObject jsonUniv = new JSONObject().put("University", jsonU);
			//JSONObject jsonRank = new JSONObject().put("Rank", jsonR);
			JSONObject jsonUR = new JSONObject();
			jsonUR.put("University", jsonU);
			jsonUR.put("Rank", rank);
			JSONArray.put(jsonUR);
		}
		JSONObject.put("Details", JSONArray);
//		Gson gson = new Gson();
//		String JSON = gson.toJson(details); 
//		System.out.println(JSON);
//		return JSON;
		System.out.println(JSONObject.toString());
		return JSONObject.toString();
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
