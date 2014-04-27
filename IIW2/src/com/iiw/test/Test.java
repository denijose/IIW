package com.iiw.test;

import java.io.IOException;

import org.openrdf.repository.RepositoryException;

import com.iiw.entities.University;
import com.iiw.rdf.Admin;
import com.iiw.rdf.Manage;

public class Test {

	/**
	 * @param args
	 * @throws RepositoryException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws RepositoryException, IOException {
		Admin.createUniversities();
		//Manage.createUniversity(new University("http://www.edulix.com/unisearch/univreview.php?stid=36&univid=37","Alliant International University",null,null,null,null,null));

	}

}
