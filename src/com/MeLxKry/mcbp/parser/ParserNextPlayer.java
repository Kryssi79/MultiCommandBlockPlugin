package com.MeLxKry.mcbp.parser;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.Helper;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserNextPlayer extends CommandParser {
	
	private ParsedCommand m_ParsedCommand;
	private Player[] m_playerList;
	
	public ParserNextPlayer(ParsedCommand postIntervalParsedCommentObj,
			Player[] playerList) {
		super(postIntervalParsedCommentObj, playerList);
		
		m_ParsedCommand = postIntervalParsedCommentObj;
		m_playerList = playerList;
	}

	protected int Parse(){
		int exist = 0;
		
		if (m_ParsedCommand == null ||
			m_playerList == null){
			return exist;
		}
		
		// next Online Player
		if (m_ParsedCommand.getCommand().contains("@p")){
		if(bLogToConsole==true)  { System.out.println("ParserNextPlayer -> @p"); }
		if (m_fromBlock != null) {
			Location blockLocation = m_fromBlock.getLocation();
			Location nextPlayerLocation = null;
						
			Player johnDoe = null; // naechster Player
			double [] distArray = new double[m_playerList.length];
			double nextdist = 0.0;
						
			if (m_playerList.length == 1){
				// wenn nur einer dann direkte zuweisung
				johnDoe = m_playerList[0];
			}
			else{
				for(int i =0; i < m_playerList.length; i++){
					nextPlayerLocation = m_playerList[i].getLocation();
					nextdist = Helper.getDistance(blockLocation, nextPlayerLocation);
					for(int x =0; x < distArray.length; x++){
						if (nextdist <  distArray[x]){
							johnDoe = m_playerList[i]; // johnDoe for President ;)
						}
					}
					distArray[i] = nextdist;
				}
			}
			if (johnDoe != null){
				exist = 1;
				ParsedCommand newParsedCommand = new ParsedCommand();
				newParsedCommand.setInterval(m_ParsedCommand.getInterval());
				
				String newCommand = m_ParsedCommand.getCommand().replace
				("@p", johnDoe.getDisplayName());
				
				newParsedCommand.setCommand(newCommand);
				m_CommandParts.add(newParsedCommand);
			}
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
