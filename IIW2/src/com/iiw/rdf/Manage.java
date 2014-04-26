package com.iiw.rdf;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.http.HTTPRepository;

import com.iiw.entities.Student;
import com.iiw.entities.University;


public class Manage {

	private static String sesameServer = "http://localhost:8080/openrdf-sesame/"; 
	private static String repositoryID = "IIW";;
	private static Repository repo;
	
	public static void intitialize() throws RepositoryException{
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();	
		ValueFactory f = repo.getValueFactory();
		URI universityClass = f.createURI("http://dbpedia.org/ontology/EducationalInstitution");
		URI name = f.createURI("http://dbpedia.org/property/name");
		URI studentClass = f.createURI("http://dbpedia.org/ontology/person");
		
//		RepositoryConnection con = repo.getConnection();
//		con.add(arg0, arg1, arg2, arg3).add(universityClass, RDF.TYPE, RDF.class);
//	    con.add(studentClass, name, RDF.class);	    
//	    con.close();
	}
	
	public static void create() throws RepositoryException{				
		ValueFactory f = repo.getValueFactory();
		URI alice = f.createURI("http://example.org/people/alice");
		URI bob = f.createURI("http://example.org/people/bob");
		URI name = f.createURI("http://example.org/ontology/name");
		URI person = f.createURI("http://example.org/ontology/Person");
		Literal bobsName = f.createLiteral("Bob");
		Literal alicesName = f.createLiteral("Alice");
		
		RepositoryConnection con = repo.getConnection();
		
		// alice is a person
	      con.add(alice, RDF.TYPE, person);
	      // alice's name is "Alice"
	      con.add(alice, name, alicesName);

	      // bob is a person
	      con.add(bob, RDF.TYPE, person);
	      // bob's name is "Bob"
	      con.add(bob, name, bobsName);
	      
	      con.close();
	      
	      RepositoryResult<Statement> statements = con.getStatements(alice, null, null, true);
	      try {
	    	   while (statements.hasNext()) {
	    	      Statement st = statements.next();

	    	     System.out.println(st.toString());
	    	   }
	    	}
	    	finally {
	    	   statements.close(); // make sure the result object is closed properly
	    	}
		
		
	}
	
	public static void createUniversity(University univ) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();		
		
		ValueFactory f = repo.getValueFactory();		
		URI univURI = f.createURI(univ.getURI());		
		Literal uniName = f.createLiteral(univ.getName());
		URI universityClass = f.createURI("http://dbpedia.org/ontology/EducationalInstitution");
		URI name = f.createURI("http://dbpedia.org/property/name");
		
		RepositoryConnection con = repo.getConnection();		
		con.add(univURI, RDF.TYPE, universityClass);
	    con.add(univURI, name, uniName);	    
	    con.close();
	}
	
	public static void createStudent(Student s) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();		
		
		ValueFactory f = repo.getValueFactory();	
		URI studentClass = f.createURI("http://dbpedia.org/ontology/person");
		URI studentURI = f.createURI(s.getURI());
		URI name = f.createURI("http://dbpedia.org/property/name");
		Literal sName = f.createLiteral(s.getName());	
		
		RepositoryConnection con = repo.getConnection();		
		con.add(studentURI, RDF.TYPE, studentClass);
	    con.add(studentURI, name, sName);	    
	    con.close();
	}
}
