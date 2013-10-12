package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.main_MCBP;


public class ParserAllPayer extends CommandParser {
	
	private ParsedCommand m_ParsedCommand;
	private Player[] m_playerList;
	
	public ParserAllPayer(ParsedCommand postIntervalParsedCommentObj,
			Player[] playerList) {
		super(postIntervalParsedCommentObj, playerList);
		
		m_ParsedCommand = postIntervalParsedCommentObj;
		m_playerList = playerList;
	}

	protected int Parse()
	{
		int exist = 0;
		
		if (m_ParsedCommand == null ||
			m_playerList == null){
			return exist;
		}
		
		// all Online Player
		if (m_ParsedCommand.getCommand().contains("@a")){
			if(bLogToConsole==true)  { System.out.println("ParserAllPayer -> @a"); }
			for (int i =0; i < m_playerList.length; i++){
				exist = 1;
				ParsedCommand newParsedCommand = new ParsedCommand();		
				// Interval Berechnung = BaseCommand interval / Menge der Commands
				int interval = m_ParsedCommand.getInterval();
				interval = interval / m_playerList.length;
							
				newParsedCommand.setInterval(interval);
				
				String newCommand = m_ParsedCommand.getCommand().replace
				("@a", m_playerList[i].getDisplayName());
				
				newParsedCommand.setCommand(newCommand.trim());
				m_CommandParts.add(newParsedCommand);
			}
		}
		return exist;
	}
	
	@Override
	protected void finalize() throws Throwable {
		m_ParsedCommand = null;
		m_playerList = null;
	}

}
