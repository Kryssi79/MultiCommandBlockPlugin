package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;
import com.MeLxKry.mcbp.Helper;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserRandomPlayer extends CommandParser  {

	public ParserRandomPlayer(main_MCBP plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	
	public int Parse(ParsedCommand postIntervalParsedCommentObj, Player[] playerList)
	{
		int exist = 0;
		// random Online Player
		if (postIntervalParsedCommentObj.getCommand().contains("@r")){
			if(bLogToConsole==true)  { System.out.println("ParserRandomPlayer -> @r"); } 
			exist = 1;
			int rand = Helper.nextRandomInt(playerList.length - 1);
			ParsedCommand newParsedCommand = new ParsedCommand();
			newParsedCommand.setInterval(postIntervalParsedCommentObj.getInterval());
			String newCommand = postIntervalParsedCommentObj.getCommand().replace
			("@r", playerList[rand].getDisplayName());
			
			newParsedCommand.setCommand(newCommand);
			m_CommandParts.add(newParsedCommand);
		}
		return exist;
	}
	
	

}
