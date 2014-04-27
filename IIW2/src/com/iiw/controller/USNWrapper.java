package com.iiw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiw.wrapper.USNCustomWrapper;
import com.iiw.wrapper.USNewsWrapper;

public class USNWrapper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public USNWrapper() {
        super();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	System.out.println("Received request from "+request.getContextPath());
    	//new USNewsWrapper();
    	new USNCustomWrapper();
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
