package com.MeLxKry.mcbp;

import org.bukkit.Location;

public class Helper 
{

	public static int random_range(int x1, int x2) {
	    return (int) (Math.floor(x1 + (Math.random() * (x2 - x1))));
	} 
	
	//Entfernung
	public static double getDistance(Location vLoc1, Location vLoc2) {
		return  vLoc1.distance(vLoc2);
	}
	
	
}
