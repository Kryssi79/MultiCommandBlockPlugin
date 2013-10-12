package com.MeLxKry.mcbp.parser;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.MeLxKry.mcbp.Helper;

public class ParserNextPlayer extends CommandParser {
	
	CommandParser m_Parent;
	
	public ParserNextPlayer(CommandParser myParent) {
		this.m_Parent = myParent;
	}

	protected int Parse(ParsedCommand parsedCommand, Player[] playerlist){
		int exist = 0;
		// next Online Player
		
		if (parsedCommand.getCommand().contains("@p")) {
		if(m_Parent.bLogToConsole==true)  { System.out.println("ParserNextPlayer -> @p "); }
		if (m_Parent.m_fromBlock != null) {
			Location blockLocation = m_Parent.m_fromBlock.getLocation();
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
					if(bLogToConsole==true)  
					{ 
						System.out.println("PlayerLocation -> from " 
								+ playerlist[i].getDisplayName() 
								+ " distance -> " 
								+ nextPlayerLocation); 
						System.out.println("Who is johnDoe ?: " + johnDoe.getDisplayName());
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
				m_Parent.m_CommandParts.add(newParsedCommand);
				return exist;
			}
		}
		}
		return exist;
	}
}
