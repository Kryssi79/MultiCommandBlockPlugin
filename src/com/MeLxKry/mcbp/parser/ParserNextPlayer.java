package com.MeLxKry.mcbp.parser;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.Helper;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserNextPlayer extends CommandParser {

	public ParserNextPlayer(main_MCBP plugin) {
		super(plugin);
	}
	
	public int Parse(ParsedCommand postIntervalParsedCommentObj, Player[] playerList){
		int exist = 0;
		// next Online Player
		if (postIntervalParsedCommentObj.getCommand().contains("@p")){
		if(bLogToConsole==true)  { System.out.println("ParserNextPlayer -> @p"); }
		if (m_fromBlock != null) {
			Location blockLocation = m_fromBlock.getLocation();
			Location nextPlayerLocation = null;
						
			Player johnDoe = null; // naechster Player
			double [] distArray = new double[playerList.length];
			double nextdist = 0.0;
						
			if (playerList.length == 1){
				// wenn nur einer dann direkte zuweisung
				johnDoe = playerList[0];
			}
			else{
				for(int i =0; i < playerList.length; i++){
					nextPlayerLocation = playerList[i].getLocation();
					nextdist = Helper.getDistance(blockLocation, nextPlayerLocation);
					for(int x =0; x < distArray.length; x++){
						if (nextdist <  distArray[x]){
							johnDoe = playerList[i]; // johnDoe for President ;)
						}
					}
					distArray[i] = nextdist;
				}
			}
			if (johnDoe != null){
				exist = 1;
				ParsedCommand newParsedCommand = new ParsedCommand();
				newParsedCommand.setInterval(postIntervalParsedCommentObj.getInterval());
				
				String newCommand = postIntervalParsedCommentObj.getCommand().replace
				("@p", johnDoe.getDisplayName());
				
				newParsedCommand.setCommand(newCommand);
				m_CommandParts.add(newParsedCommand);
			}
		}
		}
		return exist;
	}

}
