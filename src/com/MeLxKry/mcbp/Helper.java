package com.MeLxKry.mcbp;

import org.bukkit.Location;
import java.util.*;
import java.util.Random;


public class Helper 
{

	public static double random_DblRange(int x1, int x2) {
	    return (Math.floor(x1 + (Math.random() * (x2 - x1))));
	}
	
	public static int random_IntRange(int iFrom, int iTo) {
		Random generator = new Random();
	    return generator.nextInt(iTo-iFrom)+iTo;
	}
	
	public static int nextRandomInt(int iMax) {
		Random generator = new Random();
	    return generator.nextInt();
	}
	
	
	//Entfernung
	public static double getDistance(Location vLoc1, Location vLoc2) {
		return  vLoc1.distance(vLoc2);
	}
	
	
}
