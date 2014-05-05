package com.iiw.test;

import java.io.IOException;
import java.util.Set;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import com.iiw.entities.University;
import com.iiw.rdf.Admin;
import com.iiw.rdf.Manage;
import com.iiw.rdf.Sparql;
import com.iiw.util.*;

public class Test {

	/**
	 * @param args
	 * @throws RepositoryException 
	 * @throws IOException 
	 * @throws QueryEvaluationException 
	 * @throws MalformedQueryException 
	 */
	public static void main(String[] args) throws RepositoryException, IOException, MalformedQueryException, QueryEvaluationException {
		//Admin.createUniversities();
		//Manage.createUniversity(new University("http://www.edulix.com/unisearch/univreview.php?stid=36&univid=37","Alliant International University",null,null,null,null,null));
		//Manage.test();
		String multiline = "Hello"+"\n"+"Multiline"+"\n"+"third line";
		System.out.println(Util.jaccardSimilarity("University of Southern California", "University of Southern California - Computer Science Department"));
		System.out.println(Util.jaccardSimilarity("University of Southern California", "University of Utah - School of Computing"));
		System.out.println(Util.jaccardSimilarity("University of Southern California", "University of Southern Calipornia"));
		
		System.out.println(multiline);
	}

}
