package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.Helper;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserRandomPlayer extends CommandParser  {

	CommandParser m_Parent;
	
	public ParserRandomPlayer(CommandParser myParent) {
		this.m_Parent = myParent;
	}

	protected int Parse(ParsedCommand parsedCommand, Player[] playerlist){
		int exist = 0;
		// random Online Player
		
		if (parsedCommand == null ||
				playerlist == null){
			return exist;
		}
		
		if (parsedCommand.getCommand().contains("@r")){
			if(m_Parent.bLogToConsole==true)  { System.out.println("ParserRandomPlayer -> @r"); } 
			exist = 1;
			int rand = Helper.nextRandomInt(playerlist.length - 1);
			ParsedCommand newParsedCommand = new ParsedCommand();
			newParsedCommand.setInterval(parsedCommand.getInterval());
			String newCommand = parsedCommand.getCommand().replace
			("@r", playerlist[rand].getDisplayName());
			
			newParsedCommand.setCommand(newCommand);
			m_Parent.m_CommandParts.add(newParsedCommand);
		}
		return exist;
	}
}
