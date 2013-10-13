package com.MeLxKry.mcbp;

import org.bukkit.Location;
import java.util.*;
import java.util.Random;


public class Helper 
{

	public static double random_DblRange(int x1, int x2) {
	    return Math.floor(x1 + (Math.random() * (x2 - x1)));
	}
	
	public static int random_IntRange(int iFrom, int iTo) {
		if(iTo==0) { return 0; }
		Random generator = new Random();
	    return generator.nextInt(iTo-iFrom)+iTo;
	}
	
	public static int nextRandomInt(int iMax) {
		if(iMax==0) { return 0; }
		Random generator = new Random();
	    return generator.nextInt(iMax);
	}
	
	
	//Entfernung
	public static double getDistance(Location vLoc1, Location vLoc2) {
		return  vLoc1.distance(vLoc2);
	}
	
	//implementation of 'TryParse' 
	public static Integer tryParse(String pString) {
		 Integer retVal;
		 try {
		    retVal = Integer.parseInt(pString);
		 } catch (NumberFormatException nfe) {
		   retVal = 0;
		 }
		 return retVal;
	}
	
	
}
