package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;
import com.MeLxKry.mcbp.main_MCBP;


public class ParserAllPayer extends CommandParser {

	public ParserAllPayer(main_MCBP plugin) {
		super(plugin);
	}
	
	public int Parse(ParsedCommand postIntervalParsedCommentObj, Player[] playerList)
	{
		int exist = 0;
		// all Online Player
		if (postIntervalParsedCommentObj.getCommand().contains("@a")){
			if(bLogToConsole==true)  { System.out.println("ParserAllPayer -> @a"); }
			for (int i =0; i < playerList.length; i++){
				exist = 1;
				ParsedCommand newParsedCommand = new ParsedCommand();		
				// Interval Berechnung = BaseCommand interval / Menge der Commands
				int interval = postIntervalParsedCommentObj.getInterval();
				interval = interval / playerList.length;
							
				newParsedCommand.setInterval(interval);
				
				String newCommand = postIntervalParsedCommentObj.getCommand().replace
				("@a", playerList[i].getDisplayName());
				
				newParsedCommand.setCommand(newCommand.trim());
				m_CommandParts.add(newParsedCommand);
			}
		}
		return exist;
	}

}
