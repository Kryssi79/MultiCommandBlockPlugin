package com.MeLxKry.mcbp;

public class Randomizer 
{
	private int random_range(int x1, int x2) {
	    return (int) (Math.floor(x1 + (Math.random() * (x2 - x1))));
	} 
}
