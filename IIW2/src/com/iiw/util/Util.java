package com.iiw.util;

import java.util.HashSet;

import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

public class Util {

	private static int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
 
	public static int coputeLevenshteinDistance(String str1,String str2) {
		int[][] distance = new int[str1.length() + 1][str2.length() + 1];
 
		for (int i = 0; i <= str1.length(); i++)
			distance[i][0] = i;
		for (int j = 1; j <= str2.length(); j++)
			distance[0][j] = j;
 
		for (int i = 1; i <= str1.length(); i++)
			for (int j = 1; j <= str2.length(); j++)
				distance[i][j] = minimum(
						distance[i - 1][j] + 1,
						distance[i][j - 1] + 1,
						distance[i - 1][j - 1]+ ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
 
		return distance[str1.length()][str2.length()];    
	}
	
	
	public static double jacardSimilarity(String similar1, String similar2){
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		
		for(String s: similar1.split("\\s+")){
		h1.add(s);		
		}
		System.out.println("h1 "+ h1);
		for(String s: similar2.split("\\s+")){
		h2.add(s);		
		}
		System.out.println("h2 "+ h2);
		
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2
		System.out.println("Intersection "+ h1);
		
			
		h2.removeAll(h1);
		//h2 now contains unique elements
		System.out.println("Unique in h2 "+ h2);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();
		
		return (double)intersection/union;
		
	}
	
	public static double jaroWinkler(String string1, String string2) {
		JaroWinkler jw = new JaroWinkler();
		if (string2.length() > string1.length())
		return jw.getSimilarity(string1, string2.substring(0, string1.length()));
		else
			return jw.getSimilarity(string1, string2);
	}
}
