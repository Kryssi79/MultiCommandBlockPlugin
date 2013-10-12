package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.main_MCBP;


public class ParserAllPayer extends CommandParser {
	CommandParser m_Parent;
	
	public ParserAllPayer(CommandParser myParent) {
		this.m_Parent = myParent;
	}

	protected int Parse(ParsedCommand parsedCommand, Player[] playerlist)
	{
		int exist = 0;
		
		// all Online Player
		if (parsedCommand.getCommand().contains("@a")){
			if(m_Parent.bLogToConsole==true)  { System.out.println("ParserAllPayer -> @a"); }
			for (int i =0; i < playerlist.length; i++){
				exist = 1;
				ParsedCommand newParsedCommand = new ParsedCommand();		
				// Interval Berechnung = BaseCommand interval / Menge der Commands
				int interval = parsedCommand.getInterval();
				interval = interval / playerlist.length;
							
				newParsedCommand.setInterval(interval);
				
				String newCommand = parsedCommand.getCommand().replace
				("@a", playerlist[i].getDisplayName());
				
				newParsedCommand.setCommand(newCommand.trim());
				m_Parent.m_CommandParts.add(newParsedCommand);
			}
		}
		return exist;
	}
}
