package com.iiw.rdf;

import java.util.Set;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
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
//		con.add(arg0, arg1, arg2, arg3).add(universityClass, ofType, RDF.class);
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
		URI ofType = f.createURI("http://example.org/ofType");
		URI univURI = f.createURI(univ.getURI());		
		Literal uniName = f.createLiteral(univ.getName());
		URI universityClass = f.createURI("http://dbpedia.org/ontology/EducationalInstitution");
		URI name = f.createURI("http://dbpedia.org/property/name");
		URI country = f.createURI("http://dbpedia.org/property/country");
		URI city = f.createURI("http://dbpedia.org/property/city");
		URI state = f.createURI("http://dbpedia.org/property/state");
		URI univType = f.createURI("http://example.org/universityType");
		
		
		RepositoryConnection con = repo.getConnection();		
		con.add(univURI, ofType, universityClass);
	    con.add(univURI, name, uniName);
	    if(univ.getCountry()!=null){
	    	Literal unicountry = f.createLiteral(univ.getCountry());
	    	con.add(univURI, country, unicountry);
	    }
	    if(univ.getCity()!=null){
	    	Literal unicity = f.createLiteral(univ.getCity());
	    	con.add(univURI, city, unicity);
	    }
	    if(univ.getState()!=null){
	    	Literal unistate = f.createLiteral(univ.getState());
	    	con.add(univURI, state, unistate);
	    }	
	    if(univ.getType()!=null){
	    	Literal uniType = f.createLiteral(univ.getType());
	    	con.add(univURI, state, univType);
	    }
	    con.close();
	}
	
	public static void createEdulixUniversity(University univ) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		University USNewsUnivBean = null;
		if(univ.getURI().contains("dulix")){
		   if( (USNewsUnivBean = Sparql.getUniversityBean(univ.getName())) == null )
			   return;
		}
		
		repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();		
		ValueFactory f = repo.getValueFactory();	
		URI UnivURI = f.createURI(USNewsUnivBean.getURI());	
		URI edulixName = f.createURI("http://example.org/edulixName");	
		URI numOfAdmits = f.createURI("http://example.org/numOfAdmits");
		URI numOfRejects = f.createURI("http://example.org/numOfRejects");
		URI numOfWaiting = f.createURI("http://example.org/numOfWaiting");
		RepositoryConnection con = repo.getConnection();
		
		Literal edulixNameLiteral = f.createLiteral(univ.getName());
    	con.add(UnivURI, edulixName, edulixNameLiteral);
		
	    for(Student s : univ.getAcceptedStudents())
	    	createStudentUniversityConnection(USNewsUnivBean,s,"hasAdmit");
	    	
		
	    for(Student s : univ.getRejectedStudents())
	    	createStudentUniversityConnection(USNewsUnivBean,s,"hasReject");
		
	   // for(Student s : univ.getWaitingStudents())
	   // 	createStudentUniversityConnection(USNewsUnivBean,s,"isWaiting");
		
	    if(univ.getNumOfAdmits() != -1){
	    	Literal numOfAdmitsLiteral = f.createLiteral(univ.getNumOfAdmits());
	    	con.add(UnivURI, numOfAdmits, numOfAdmitsLiteral);
	    }
	    if(univ.getNumOfRejects() != -1){
	    	Literal numOfRejectsLiteral = f.createLiteral(univ.getNumOfRejects());
	    	con.add(UnivURI, numOfRejects, numOfRejectsLiteral);
	    }
	    if(univ.getNumOfWaiting() != -1){
	    	Literal numOfWaitingLiteral = f.createLiteral(univ.getNumOfWaiting());
	    	con.add(UnivURI, numOfWaiting, numOfWaitingLiteral);
	    }

	    con.close();
	}
	
	public static void createStudent(Student s) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();		
		
		ValueFactory f = repo.getValueFactory();	
		URI ofType = f.createURI("http://example.org/ofType");
		URI person = f.createURI("http://dbpedia.org/ontology/person");
		URI studentURI = f.createURI(s.getURI());
		URI name = f.createURI("http://dbpedia.org/property/name");
		URI GREQScore = f.createURI("http://dbpedia.org/property/GREQScore");
		URI GREVScore = f.createURI("http://dbpedia.org/property/GREVScore");
		URI GREAScore = f.createURI("http://dbpedia.org/property/GREAScore");
		URI ToeflScore = f.createURI("http://dbpedia.org/property/ToeflScore");
		URI undergradScore = f.createURI("http://dbpedia.org/property/undergradScore");  // --> Replace age with undergradScore
		URI details = f.createURI("http://dbpedia.org/property/details");
		RepositoryConnection con = repo.getConnection();	
		
		Literal studentName = f.createLiteral(s.getName());				
		con.add(studentURI, ofType, person);
	    con.add(studentURI, name, studentName);	    
	    
	    if(s.getGreQScore()!=-1 || s.getGreQScore()!= null ){
	    	Literal GreQScoreLiteral = f.createLiteral(s.getGreQScore());	
	    	con.add(studentURI, GREQScore, GreQScoreLiteral);
	    }
	    if(s.getGreVScore()!=-1 || s.getGreVScore()!= null ){
	    	Literal GreVScoreLiteral = f.createLiteral(s.getGreVScore());	
	    	con.add(studentURI, GREVScore, GreVScoreLiteral);
	    }
	    if(s.getGreAScore()!=-1 || s.getGreAScore()!= null ){
	    	Literal GreAScoreLiteral = f.createLiteral(s.getGreAScore());	
	    	con.add(studentURI, GREAScore, GreAScoreLiteral);
	    }
	    if(s.getToeflScore()!=-1 || s.getToeflScore()!= null ){
	    	Literal ToeflScoreLiteral = f.createLiteral(s.getToeflScore());	
	    	con.add(studentURI, ToeflScore, ToeflScoreLiteral);
	    }
	    if(s.getUndergradScore()!=-1 || s.getUndergradScore()!= null ){
	    	Literal undergradScoreLiteral = f.createLiteral(s.getUndergradScore());	
	    	con.add(studentURI, undergradScore, undergradScoreLiteral);
	    }
	    if(s.getDetails()!= null){
	    	Literal detailsLiteral = f.createLiteral(s.getDetails());	
	    	con.add(studentURI, details, detailsLiteral);
	    }
	    con.close();
	}
	
	public static void createCategory(Category c) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();		
		
		ValueFactory f = repo.getValueFactory();	
		URI ofType = f.createURI("http://example.org/ofType");
		URI categoryClass = f.createURI("http://example.org/category");
		URI categoryURI = f.createURI(c.getURI());
		URI name = f.createURI("http://dbpedia.org/property/name");
		Literal cName = f.createLiteral(c.getName());	
		
		RepositoryConnection con = repo.getConnection();		
		con.add(categoryURI, ofType, categoryClass);
	    con.add(categoryURI, name, cName);	    
	    con.close();
	} 
	
	public static void createCategoryAndSubCategories(Category c, Set<Category> subCategories) throws RepositoryException{
		Repository repo = new HTTPRepository(sesameServer, repositoryID);
		repo.initialize();
		ValueFactory f = repo.getValueFactory();	
		URI ofType = f.createURI("http://example.org/ofType");
		URI categoryClass = f.createURI("http://example.org/category");
		URI subCategoryClass = f.createURI("http://example.org/subCategory");
		URI categoryURI = f.createURI(c.getURI());
		URI name = f.createURI("http://dbpedia.org/property/name");
		URI hasSubCategory = f.createURI("http://example.org/hasSubCategory");
		Literal cName = f.createLiteral(c.getName());	
		RepositoryConnection con = repo.getConnection();		
		con.add(categoryURI, ofType, categoryClass);
	    con.add(categoryURI, name, cName);	 
	    
	    for(Category subCategory : subCategories){
	    	URI subCategoryURI = f.createURI(subCategory.getURI());
	    	Literal subCategoryName = f.createLiteral(subCategory.getName());	
	    	con.add(subCategoryURI, ofType, subCategoryClass);
	    	con.add(subCategoryURI, name, subCategoryName);	 
	    	con.add(categoryURI, hasSubCategory, subCategoryURI);	    	
	    }
	}
	    
	public static void createUnivCourseConnections(University univ, Course course, Category category, Course subCourse, Category subCategory) throws RepositoryException{
			Repository repo = new HTTPRepository(sesameServer, repositoryID);
			repo.initialize();
			ValueFactory f = repo.getValueFactory();
			URI ofType = f.createURI("http://example.org/ofType");
			RepositoryConnection con = repo.getConnection();	
			
			URI univURI = f.createURI(univ.getURI());
			URI categoryURI = f.createURI(category.getURI());
			URI subCategoryURI = null;
			if(subCategory!=null)
			  subCategoryURI = f.createURI(subCategory.getURI());
			URI name = f.createURI("http://dbpedia.org/property/name");			
			URI hasCourse = f.createURI("http://example.org/hasCourse"); 
			URI hasSubCourse = f.createURI("http://example.org/hasSubCourse"); 
			URI hasRank = f.createURI("http://example.org/hasRank");
			URI hasFeesInstate = f.createURI("http://example.org/hasFeesInstate");
			URI hasFeesOutState = f.createURI("http://example.org/hasFeesOutState"); 
			URI hasEnrollments = f.createURI("http://example.org/hasEnrollments"); 	
			
			//create university
			createUniversity(univ);
			
			//create the main course			
			URI courseURI = f.createURI(course.getURI());
			Literal courseName = f.createLiteral(course.getName());
			con.add(courseURI, ofType, categoryURI);
			con.add(courseURI, name, courseName);			
			if(subCourse==null && course.getRank()!=-1){		
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
				con.add(subCourseURI, ofType, subCategoryURI);
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
			con.close();
			
	}
	
	public static void test() throws RepositoryException{
			Repository repo = new HTTPRepository(sesameServer, repositoryID);
			repo.initialize();
			ValueFactory f = repo.getValueFactory();	
			URI ofType = f.createURI("http://example.org/ofType");
			RepositoryConnection con = repo.getConnection();	
			URI bob = f.createURI("http://example.org/people/bob");
			URI name = f.createURI("http://example.org/ontology/name");
			URI person = f.createURI("http://dbpedia.org/ontology/person");
		
			Literal bobsName = f.createLiteral("Boby");
		      con.add(bob, ofType, person);
		      con.add(bob, name, bobsName);		      
		      con.close();
		}
		
	public static void createStudentUniversityConnection(University univ, Student student, String status) throws RepositoryException{
			//createUniversity(univ);
			createStudent(student);
			Repository repo = new HTTPRepository(sesameServer, repositoryID);
			repo.initialize();
			ValueFactory f = repo.getValueFactory();	
			URI ofType = f.createURI("http://example.org/ofType");
			RepositoryConnection con = repo.getConnection();
			URI studentURI = f.createURI(student.getURI());
			URI univURI = f.createURI(univ.getURI());	
			URI isWaiting = f.createURI("http://example.org/isWaiting");
			URI hasAdmit = f.createURI("http://example.org/hasAdmit");
			URI hasReject = f.createURI("http://example.org/hasReject");
			if(status.equalsIgnoreCase("isWaiting"))
			  con.add(studentURI, isWaiting, univURI);
			if(status.equalsIgnoreCase("hasAdmit"))
				  con.add(studentURI, hasAdmit, univURI);
			if(status.equalsIgnoreCase("hasReject"))
				  con.add(studentURI, hasReject, univURI);
		  	    
		    con.close();
			
		}
	
}

	
