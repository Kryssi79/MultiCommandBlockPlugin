package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.Helper;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserRandomPlayer extends CommandParser  {

	private ParsedCommand m_ParsedCommand;
	private Player[] m_playerList;
	
	public ParserRandomPlayer(ParsedCommand postIntervalParsedCommentObj,
			Player[] playerList) {
		super(postIntervalParsedCommentObj, playerList);
		
		m_ParsedCommand = postIntervalParsedCommentObj;
		m_playerList = playerList;
	}

	protected int Parse(){
		int exist = 0;
		// random Online Player
		
		if (m_ParsedCommand == null ||
			m_playerList == null){
			return exist;
		}
		
		if (m_ParsedCommand.getCommand().contains("@r")){
			if(bLogToConsole==true)  { System.out.println("ParserRandomPlayer -> @r"); } 
			exist = 1;
			int rand = Helper.nextRandomInt(m_playerList.length - 1);
			ParsedCommand newParsedCommand = new ParsedCommand();
			newParsedCommand.setInterval(m_ParsedCommand.getInterval());
			String newCommand = m_ParsedCommand.getCommand().replace
			("@r", m_playerList[rand].getDisplayName());
			
			newParsedCommand.setCommand(newCommand);
			m_CommandParts.add(newParsedCommand);
		}
		return exist;
	}
	
	@Override
	protected void finalize() throws Throwable {
		m_ParsedCommand = null;
		m_playerList = null;
	}
}
