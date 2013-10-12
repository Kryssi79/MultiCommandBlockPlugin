package com.MeLxKry.mcbp.parser;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.Helper;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserNextPlayer extends CommandParser {
	
	public ParserNextPlayer() {
		//super();
	}

	protected int Parse(ParsedCommand parsedCommand, Player[] playerlist){
		int exist = 0;
		
		// next Online Player
		if (parsedCommand.getCommand().contains("@p")) {
		if(bLogToConsole==true)  { System.out.println("ParserNextPlayer -> @p "); }
		if (m_fromBlock != null) {
			Location blockLocation = m_fromBlock.getLocation();
			Location nextPlayerLocation = null;
						
			Player johnDoe = null; // naechster Player
			double [] distArray = new double[playerlist.length];
			double nextdist = 0.0;
						
			if (playerlist.length == 1){
				// wenn nur einer dann direkte zuweisung
				johnDoe = playerlist[0];
			}
			else{
				for(int i =0; i < playerlist.length; i++){
					nextPlayerLocation = playerlist[i].getLocation();
					nextdist = Helper.getDistance(blockLocation, nextPlayerLocation);
					for(int x =0; x < distArray.length; x++){
						if (nextdist <  distArray[x]){
							johnDoe = playerlist[i]; // johnDoe for President ;)
						}
					}
					distArray[i] = nextdist;
				}
			}
			if (johnDoe != null){
				exist = 1;
				ParsedCommand newParsedCommand = new ParsedCommand();
				newParsedCommand.setInterval(parsedCommand.getInterval());
				
				String newCommand = parsedCommand.getCommand().replace("@p", johnDoe.getDisplayName());
				if(bLogToConsole==true)  { System.out.println("ParserNextPlayer -> "+newCommand); }
				
				newParsedCommand.setCommand(newCommand);
				m_CommandParts.add(newParsedCommand);
				return exist;
			}
		}
		}
		return exist;
	}
}
