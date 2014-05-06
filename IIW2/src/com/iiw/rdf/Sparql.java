package com.iiw.rdf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.iiw.entities.User;
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
    		    // System.out.println(valueOfX.toString());
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
		double jacDistance = (int) Float.NEGATIVE_INFINITY;
		for( String univ : universities){
			double distance;
			if( (distance = Util.jaroWinkler(universityName, univ)) > jacDistance){
				jacDistance = distance;
				university = univ;
			}
		}
			
		System.out.println("selected by Jaro - " + university);
		return university;
	}
	
	public static String getUniversityWithState(String universityName,String state) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		String university = new String();
		Set<String> universities = getStateUniversities(state);
		double jacDistance = (int) Float.NEGATIVE_INFINITY;
		for( String univ : universities){
			double distance;
			distance = Util.jaroWinkler(universityName, univ);
			if( distance > jacDistance){
				jacDistance = distance;
				university = univ;
			}
		}
			
		System.out.println("Uniname - "+universityName+". Jaro Selection - " + university);
		return university;
	}
	private static Set<String> getStateUniversities(String state) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		Set<String> universities = new HashSet<String>();
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();con = repo.getConnection();
		String p = "<http://example.org/ofType>";
		String o = "<http://dbpedia.org/ontology/EducationalInstitution>";
		String queryString  = "SELECT ?name WHERE " +
				 		       " { ?x " + p + " " + o + "." +
				 		       "   ?x <http://dbpedia.org/property/state> \""+ state +"\" . "+
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
    		    // System.out.println(valueOfX.toString());
    		     universities.add(valueOfX.toString());
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
    	  return universities;
	}

	public static University getUniversityBean(String universityName, String givenState) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		String name = new String();
		if(givenState!=null)
		 name = getUniversityWithState(universityName,givenState);
		else
			name = getUniversityWithName(universityName);
		String URI;
		String country;
		String city;
		String state;
		
		if ((name==null) || (name.equals(""))) {
			System.out.println("Jaro Failed for "+universityName);
			return null;
		}
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
		
	public static Set<String> getAllCategories() throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();
		String queryString  = "SELECT ?categoryName WHERE " +
				              "{ ?c <http://example.org/ofType> <http://example.org/category> . " +  
				              "  ?c <http://dbpedia.org/property/name> ?name . " +
				              "  ?c <http://dbpedia.org/property/name> ?categoryName  . " +
				              "}";		
		System.out.println(queryString);
		Set<String> categorySet = new HashSet<String>();
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
    		  List<String> bindingNames = result.getBindingNames();
    		  if(bindingNames.size()==0)
    			  return null;
    		  while (result.hasNext()) {
    		     BindingSet bindingSet = result.next();
    		     categorySet.add(bindingSet.getValue(bindingNames.get(0)).toString());
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
		return categorySet;
	}
	
	public static LinkedHashMap<University,String> getUnivGivenCategory(String category) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();
		String queryString  = "SELECT ?u ?name ?country ?city ?state ?rank WHERE " +
				              "{ ?u <http://example.org/ofType> <http://dbpedia.org/ontology/EducationalInstitution> ." +
				              "  ?u <http://dbpedia.org/property/name> ?name . " +
				              "  OPTIONAL { ?u  <http://dbpedia.org/property/country> ?country} . " +
				              "  OPTIONAL { ?u  <http://dbpedia.org/property/city> ?city} . " +
				              "  OPTIONAL { ?u  <http://dbpedia.org/property/state> ?state} . " +
				              "  ?u <http://example.org/hasCourse> ?course . " +  
				              "  ?course <http://example.org/ofType> ?cat . " +
				              "  ?course <http://example.org/hasRank> ?rank . " +
				              "  ?cat <http://example.org/ofType> <http://example.org/category>  . " +
				              "  ?cat <http://dbpedia.org/property/name> \"" + category + "\" . " + 
				              "} order by ?rank";		
		System.out.println(queryString);
		LinkedHashMap<University,String> universityRankMap = new LinkedHashMap<University,String>();
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
    		  List<String> bindingNames = result.getBindingNames();
    		  if(bindingNames.size()==0)
    			  return null;
    		  int noOfResults = 0;
    		  while (result.hasNext() && noOfResults < 20) {
    			  BindingSet bindingSet = result.next();
    		     String URI = bindingSet.getValue(bindingNames.get(0).toString()).toString();
    		     String name = bindingSet.getValue(bindingNames.get(1).toString()).toString();
    		     String country = bindingSet.getValue(bindingNames.get(2).toString()).toString();
    		     String city = bindingSet.getValue(bindingNames.get(3).toString()).toString();
    		     String state = bindingSet.getValue(bindingNames.get(4).toString()).toString();
    		     University u = new University( URI, name, country, city, state, false);
    		     String rank = bindingSet.getValue(bindingNames.get(5).toString()).toString();
    		     universityRankMap.put(u, rank);    
    		     noOfResults++;
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
		return universityRankMap;
	}

	public static User getUser(String userURI) throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();
		String queryString  = "SELECT ?u ?name ?qscore ?vscore ?ascore ?tscore ?country ?stream WHERE {" +
							  " ?u <http://example.org/ofType> <http://dbpedia.org/ontology/faceBook> .   "  +
							  "	?u <http://dbpedia.org/property/name> ?name . " +
							  "	OPTIONAL { ?u  <http://example.org/GREQScore> ?qscore } . " +
							"	OPTIONAL { ?u  <http://example.org/GREVScore> ?vscore } . " +
							"	OPTIONAL { ?u  <http://example.org/GREAScore> ?ascore } . " +
							"	OPTIONAL { ?u  <http://example.org/ToeflScore> ?tscore } . " +
							"	OPTIONAL { ?u  <http://example.org/country> ?country } . " +
							"	OPTIONAL { ?u  <http://example.org/stream> ?stream } . " +
							"	}";		
		System.out.println(queryString);
		User u = null;
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
    		  List<String> bindingNames = result.getBindingNames();
    		  if(bindingNames.size()==0)
    			  return null;
    		  int noOfResults = 0;
    		  while (result.hasNext() && noOfResults < 20) {
    			 BindingSet bindingSet = result.next();
    		     String URI = bindingSet.getValue(bindingNames.get(0).toString()).toString();
    		     String name = bindingSet.getValue(bindingNames.get(1).toString()).toString();
    		     String qscore = bindingSet.getValue(bindingNames.get(2).toString()).toString(); qscore = qscore.substring(1, qscore.lastIndexOf("\""));
    		     String vscore = bindingSet.getValue(bindingNames.get(3).toString()).toString();vscore = vscore.substring(1, vscore.lastIndexOf("\""));
    		     String ascore = bindingSet.getValue(bindingNames.get(4).toString()).toString();ascore = ascore.substring(1, ascore.lastIndexOf("\""));
    		     String tscore = bindingSet.getValue(bindingNames.get(5).toString()).toString();tscore = tscore.substring(1, tscore.lastIndexOf("\""));
    		     String country = bindingSet.getValue(bindingNames.get(6).toString()).toString();
    		     String stream = bindingSet.getValue(bindingNames.get(7).toString()).toString();
    		     u = new User( URI,  name, Integer.parseInt(qscore), Integer.parseInt(vscore),  Integer.parseInt(ascore),  Integer.parseInt(tscore),  country,  stream );    		       
    		     noOfResults++;
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
		return u;
	}
	
	
	public static String search(String country, String state, String category, String QScore, String VScore, String AScore, String TScore, String BScore, String Fees ) throws RepositoryException, QueryEvaluationException, JSONException, MalformedQueryException{
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		RepositoryConnection con = repo.getConnection();
		
		String cntry = null;
		String st = null;
		String cat = category;
		
		if(country==null || country=="" || country=="-1" || country.length()!=1)
			cntry = "?country";
		else
			cntry = "\"" + country + "\"";
		if(state==null || state=="" || state =="-1")
			st = "?state";
		else
			st = "\"" + state + "\"" ;
		
	
		String having = "having (avg(?Q)>0 && avg(?V)>0 && avg(?A)>0 && avg(?T)>0 && avg(?B)>0 ";
		if(QScore!=null && QScore!="" && QScore.length()!=0)
			having+=" && avg(?Q)< " + QScore;
		if(VScore!=null && VScore!="" && VScore.length()!=0)
			having+=" && avg(?V)< " + VScore;
		if(AScore!=null && AScore!="" && AScore.length()!=0)
			having+=" && avg(?A)< " + AScore;
		if(TScore!=null && TScore!="" && TScore.length()!=0)
			having+=" && avg(?T)< " + TScore;
		if(BScore!=null && BScore!="" && BScore.length()!=0)
			having+=" && avg(?B)< " + BScore;
		
		having += ")" ;
		
		String queryString  = 	"SELECT ?u ?name ?country ?city ?state ?rank ?cat ?admits ?rejects ?fees ?enr (avg(?Q) as ?avgQ) (avg(?V) as ?avgV) (avg(?A) as ?avgA) (avg(?T) as ?avgT) (avg(?B) as ?avgB) " +
				 				" WHERE { "+ 
					 				" ?u <http://example.org/ofType> <http://dbpedia.org/ontology/EducationalInstitution> .   " +
					 				" ?u <http://example.org/edulixName> ?name . " +
					 				
					                " OPTIONAL { ?u  <http://dbpedia.org/property/country> "+ cntry +" } . " +
				                	" OPTIONAL { ?u  <http://dbpedia.org/property/city> ?city} . " +
					                " OPTIONAL { ?u  <http://dbpedia.org/property/state> " + st +" } . " +
				                	
					                " ?u <http://example.org/hasCourse> ?course .   " +
					                " ?course <http://example.org/ofType> ?cat . " + 
					                " ?cat <http://example.org/ofType> <http://example.org/category>  . " + 
					                " ?cat <http://dbpedia.org/property/name> \"" + cat +"\" . " + 
					                
					                " OPTIONAL { ?u <http://example.org/numOfAdmits> ?admits . }" +
                                    " OPTIONAL { ?u <http://example.org/numOfReject> ?rejects . }" +
                                    " OPTIONAL { ?course <http://example.org/hasFeesOutState> ?fees . }" +
                                    " OPTIONAL { ?course <http://example.org/hasEnrollments> ?enr. }" +		
                                    
					                " ?course <http://example.org/hasRank> ?rank . " +

					                " ?s <http://example.org/hasAdmit> ?u . " +
					                " ?s <http://dbpedia.org/property/GREQScore> ?Q . " +
					                " ?s <http://dbpedia.org/property/GREVScore> ?V . " +
					                " ?s <http://dbpedia.org/property/GREAScore> ?A . " +
					                " ?s <http://dbpedia.org/property/ToeflScore> ?T . " +
					                " ?s <http://dbpedia.org/property/undergradScore> ?B . " +
					                
					                " filter($Q>170 && $V>170) " +
				                " } " +
					                
				                "group by ?u ?name ?country ?city ?state ?rank ?cat ?admits ?rejects ?fees ?enr " +
				                 having + " order by ?rank";
		System.out.println(queryString);
		
		JSONObject table = new JSONObject();
		JSONArray rows = new JSONArray();
		table.put("table", rows);

		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
    	  TupleQueryResult result = tupleQuery.evaluate();
    	  try {
    		  List<String> bindingNames = result.getBindingNames();
    		  if(bindingNames.size()==0)
    			  return null;
    		  int noOfResults = 0;
    		  while (result.hasNext() && noOfResults < 20) {
    			  JSONObject row = new JSONObject();
    			 BindingSet bindingSet = result.next();
    		     
    			 String URI = bindingSet.getValue(bindingNames.get(0).toString()).toString();
    		     row.put("U", URI);
    		     
    		     String name = bindingSet.getValue(bindingNames.get(1).toString()).toString();
    		     row.put("name", name);
    		     
    		     if(bindingSet.getValue(bindingNames.get(2).toString())!=null){
    		     String country1 = bindingSet.getValue(bindingNames.get(2).toString()).toString();
    		     row.put("country", country1);
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(3).toString())!=null){
    		     String city1 = bindingSet.getValue(bindingNames.get(3).toString()).toString();
    		     row.put("city", city1);
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(4).toString())!=null){
    		     String state1 = bindingSet.getValue(bindingNames.get(4).toString()).toString();
    		     row.put("state", state1);
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(5).toString())!=null){
    		     String rank = bindingSet.getValue(bindingNames.get(5).toString()).toString();
    		     row.put("rank", rank);
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(6).toString())!=null){
    		     String cat1 = bindingSet.getValue(bindingNames.get(6).toString()).toString();
    		     row.put("cat", cat1);    		     
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(7).toString())!=null){
    		     String admits = bindingSet.getValue(bindingNames.get(7).toString()).toString(); admits = admits.substring(1, admits.lastIndexOf("\""));
    		     row.put("admits", admits); 
    		     }
    		     
    		     
    		     if(bindingSet.getValue(bindingNames.get(8).toString())!=null){
	    		     String rejects = bindingSet.getValue(bindingNames.get(8).toString()).toString(); rejects = rejects.substring(1, rejects.lastIndexOf("\""));
	    		     row.put("rejects", rejects);  
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(9).toString())!=null){
	    		     String fees = bindingSet.getValue(bindingNames.get(8).toString()).toString(); fees = fees.substring(1, fees.lastIndexOf("\""));
	    		     row.put("fees", fees);  
    		     }
    		     
    		     if(bindingSet.getValue(bindingNames.get(10).toString())!=null){
	    		     String enr = bindingSet.getValue(bindingNames.get(8).toString()).toString(); //enr = enr.substring(1, enr.lastIndexOf("\""));
	    		     row.put("enr", enr);  
    		     }
    		     
  		     
    		     String q = bindingSet.getValue(bindingNames.get(11).toString()).toString();
    		     row.put("q", q);  
    		     
    		     String v = bindingSet.getValue(bindingNames.get(12).toString()).toString();
    		     row.put("v", v);
    		     
    		     String a = bindingSet.getValue(bindingNames.get(13).toString()).toString();
    		     row.put("a", a);
    		     
    		     String t = bindingSet.getValue(bindingNames.get(14).toString()).toString();
    		     row.put("t", t);
    		     
    		     String b = bindingSet.getValue(bindingNames.get(15).toString()).toString();
    		     row.put("b", b);
    		     
    		     rows.put(row);
    		     noOfResults++;
    		  }
    	  }
	  	  finally{
	  	      result.close();
	  	  }
    	 
    	  System.out.println(table.toString());
    	 
		return table.toString();
	}
	
	

	
	
}
