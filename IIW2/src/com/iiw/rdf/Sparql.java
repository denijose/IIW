package com.iiw.rdf;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import com.iiw.entities.University;
import com.iiw.util.Util;

public class Sparql {

	private static String sesameServer = "http://localhost:8080/openrdf-sesame/"; 
	private static String repositoryID = "IIW";;
	private static Repository repo;
	
	private static Set<String> subPredObj(String s, String p, String o) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		Set<String> resulSet = new HashSet<String>();
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();con = repo.getConnection();
		String queryString = new String();
		if(s==null)
		 queryString = "SELECT ?x WHERE { ?x ?" + p + "?" + o + "} ";
		
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
  			BindingSet bindingSet = result.next();
  			Value valueOfX = bindingSet.getValue("x");
  			Value valueOfY = bindingSet.getValue("y");
  			resulSet.add(valueOfX.toString());
  			resulSet.add(valueOfY.toString());
  			// do something interesting with the values here...
  	      }
	  	  finally{
	  	      result.close();
	  	  }
    	  return resulSet;
	}
	
	public static Set<String> getAllUniversities() throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		Set<String> universities = new HashSet<String>();
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();con = repo.getConnection();
		String p = "<http://example.org/ofType>";
		String o = "<http://dbpedia.org/ontology/EducationalInstitution>";
		String queryString  = "SELECT ?name WHERE " +
				 		       " { ?x " + p + " " + o + "." +
							   "   ?x <http://dbpedia.org/property/name> ?name ." +
				               " } ";		
		System.out.println(queryString);
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
//    		  BindingSet bindingSet;
//  			  while(( bindingSet = result.next())!=null){
//  					Value valueOfX = bindingSet.getValue("x");System.out.println(valueOfX.toString());
//  					universities.add(valueOfX.toString());
//  			  }
    		  List<String> bindingNames = result.getBindingNames();
    		  while (result.hasNext()) {
    		     BindingSet bindingSet = result.next();
    		     Value valueOfX = bindingSet.getValue(bindingNames.get(0));
    		     System.out.println(valueOfX.toString());
    		     universities.add(valueOfX.toString());
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
    	  return universities;
		
	}
	
	public static String getUniversityWithName(String universityName) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		String university = new String();
		Set<String> universities = getAllUniversities();
		int levDistance = (int) Float.POSITIVE_INFINITY;
		for( String univ : universities){
			int distance;
			if( (distance = Util.computeLevenshteinDistance(universityName, univ)) < levDistance){
				levDistance = distance;
				university = univ;
			}
		}
			
		System.out.println(university);
		return university;
	}
	
	public static University getUniversityBean(String universityName) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		String name = getUniversityWithName(universityName);
		String URI;
		String country;
		String city;
		String state;

		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();
		String p = "<http://example.org/ofType>";
		String o = "<http://dbpedia.org/ontology/EducationalInstitution>";
		String queryString  = "SELECT ?u ?name ?country ?city ?state WHERE " +
				              "{ ?u <http://example.org/ofType> <http://dbpedia.org/ontology/EducationalInstitution> . " +  
				              "  ?u <http://dbpedia.org/property/name> ?name . " +
				              "  OPTIONAL { ?u  <http://dbpedia.org/property/country> ?country} . " +
				              "  OPTIONAL { ?u  <http://dbpedia.org/property/city> ?city} . " +
				              "  OPTIONAL { ?u  <http://dbpedia.org/property/state> ?state} ." +
				              "  ?u <http://dbpedia.org/property/name> ?" + name + " . " +
				              "}";		
		System.out.println(queryString);
		University univ = new University();
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
    		  List<String> bindingNames = result.getBindingNames();
    		  if(bindingNames.size()==0)
    			  return null;
    		  while (result.hasNext()) {
    		     BindingSet bindingSet = result.next();
    		     Value valueOfURI = bindingSet.getValue(bindingNames.get(0));
    		     Value valueOfName = bindingSet.getValue(bindingNames.get(1));
    		     Value valueOfCountry = bindingSet.getValue(bindingNames.get(2));
    		     Value valueOfCity = bindingSet.getValue(bindingNames.get(3));
    		     Value valueOfState = bindingSet.getValue(bindingNames.get(4));
    		     univ.setURI(valueOfURI.toString());
    		     univ.setName(valueOfName.toString());
    		     univ.setCountry(valueOfCountry.toString());
    		     univ.setState(valueOfState.toString());
    		     univ.setCity(valueOfCity.toString());
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
		return univ;
	}
}
