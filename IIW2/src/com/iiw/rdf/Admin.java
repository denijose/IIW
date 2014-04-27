package com.iiw.rdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.openrdf.repository.RepositoryException;

import com.iiw.entities.University;

public class Admin {

	private static Set<University> getUniversitiesFromFile() throws IOException{
		Set<University> universitySet = new HashSet<University>();
		File file = new File("C:\\D Drive\\KNOWLEDGE IS POWER\\IIW\\Project\\Universities.csv");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = new String();
		while((line=br.readLine())!=null){
			String[] tokens = line.split(",");
			String univURI = "http://www.edulix.com/unisearch/" + tokens[0];
			String univName = tokens[1];
			//universitySet.add(new University(univURI,univName,null,null,null,null,null));
		}
		br.close();
		return universitySet;
	}
	
	public static void createUniversities() throws IOException, RepositoryException{
		Set<University> universitySet = getUniversitiesFromFile();
		for(University u : universitySet){
			//Manage.createUniversity(u);	
			System.out.println(u.getURI()+ u.getName());
		}
	}
	
	
}
