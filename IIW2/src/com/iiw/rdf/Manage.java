package com.iiw.rdf;

import java.util.Set;

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

import com.iiw.entities.*;



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
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();	
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
		repo = new HTTPRepository(sesameServer, repositoryID);
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
	
	public static void createCategory(Category c) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();		
		
		ValueFactory f = repo.getValueFactory();	
		URI categoryClass = f.createURI("http://example.org/category");
		URI categoryURI = f.createURI(c.getURI());
		URI name = f.createURI("http://dbpedia.org/property/name");
		Literal cName = f.createLiteral(c.getName());	
		
		RepositoryConnection con = repo.getConnection();		
		con.add(categoryURI, RDF.TYPE, categoryClass);
	    con.add(categoryURI, name, cName);	    
	    con.close();
	} 
	
	public static void createCategoryAndSubCategories(Category c, Set<Category> subCategories) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		ValueFactory f = repo.getValueFactory();	
		URI categoryClass = f.createURI("http://example.org/category");
		URI categoryURI = f.createURI(c.getURI());
		URI name = f.createURI("http://dbpedia.org/property/name");
		URI hasSubCategory = f.createURI("http://example.org/hasSubCategory");
		Literal cName = f.createLiteral(c.getName());	
		RepositoryConnection con = repo.getConnection();		
		con.add(categoryURI, RDF.TYPE, categoryClass);
	    con.add(categoryURI, name, cName);	 
	    
	    for(Category subCategory : subCategories){
	    	URI subCategoryURI = f.createURI(subCategory.getURI());
	    	Literal subCategoryName = f.createLiteral(subCategory.getName());	
	    	con.add(subCategoryURI, RDF.TYPE, categoryClass);
	    	con.add(subCategoryURI, name, subCategoryName);	 
	    	con.add(categoryURI, hasSubCategory, subCategoryURI);	    	
	    }
	}
	    
	 public static void createUnivCourseConnections(University univ, Course course, Category category, Course subCourse, Category subCategory) throws RepositoryException{
			Repository repo = new HTTPRepository(sesameServer, repositoryID);
			repo.initialize();
			ValueFactory f = repo.getValueFactory();
			RepositoryConnection con = repo.getConnection();	
			
			URI univURI = f.createURI(univ.getURI());
			URI categoryURI = f.createURI(category.getURI());
			URI subCategoryURI = f.createURI(subCategory.getURI());
			URI name = f.createURI("http://dbpedia.org/property/name");			
			URI hasCourse = f.createURI("http://example.org/hasCourse"); 
			URI hasSubCourse = f.createURI("http://example.org/hasSubCourse"); 
			URI hasRank = f.createURI("http://example.org/hasCategory");
			URI hasFeesInstate = f.createURI("http://example.org/hasFeesInstate");
			URI hasFeesOutState = f.createURI("http://example.org/hasFeesOutState"); 
			URI hasEnrollments = f.createURI("http://example.org/hasEnrollments"); 	
			
			//create the main course			
			URI courseURI = f.createURI(course.getURI());
			Literal courseName = f.createLiteral(course.getName());
			con.add(courseURI, RDF.TYPE, categoryURI);
			con.add(courseURI, name, courseName);			
			if(course.getRank()!=-1){
				Literal courseRank = f.createLiteral(course.getRank());
				con.add(courseURI, hasRank, courseRank);
			}
			if(course.getFeesInstate()!=-1){
				Literal courseFeesInstate = f.createLiteral(course.getFeesInstate());
				con.add(courseURI, hasFeesInstate, courseFeesInstate);
			}
			if(course.getFeesOutState()!=-1){
				Literal courseFeesOutState = f.createLiteral(course.getFeesOutState());
				con.add(courseURI, hasFeesOutState, courseFeesOutState);
			}
			if(course.getEnrollments()!=-1){
				Literal courseEnrollments = f.createLiteral(course.getEnrollments());
				con.add(courseURI, hasEnrollments, courseEnrollments);
			}
			
			//connect univ to the main course
			con.add(univURI, hasCourse, courseURI);
			
			//create the subcourse if exists
			if(subCourse!=null){
				URI subCourseURI = f.createURI(subCourse.getURI());
				Literal subCourseName = f.createLiteral(subCourse.getName());
				con.add(subCourseURI, RDF.TYPE, subCategoryURI);
				con.add(subCourseURI, name, subCourseName);
				if(subCourse.getRank()!=-1){
					Literal subCourseRank = f.createLiteral(subCourse.getRank());
					con.add(subCourseURI, hasRank, subCourseRank);
				}
				if(subCourse.getFeesInstate()!=-1){
					Literal subCourseFeesInstate = f.createLiteral(subCourse.getFeesInstate());
					con.add(subCourseURI, hasFeesInstate, subCourseFeesInstate);
				}
				if(subCourse.getFeesOutState()!=-1){
					Literal subCourseFeesOutState = f.createLiteral(subCourse.getFeesOutState());
					con.add(subCourseURI, hasFeesOutState, subCourseFeesOutState);
				}
				if(subCourse.getEnrollments()!=-1){
					Literal subCourseEnrollments = f.createLiteral(subCourse.getEnrollments());
					con.add(courseURI, hasEnrollments, subCourseEnrollments);
				}
				//connect the main course to subcourse
				con.add(courseURI, hasSubCourse, subCourseURI);				
			}
			
			
	}
	
	
	
}

	
