package com.MeLxKry.mcbp.parser;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.main_MCBP;


public class CommandParser {
	List<ParsedCommand> m_CommandParts;
	main_MCBP m_plugin;
	Block m_fromBlock;
	boolean bLogToConsole = false;
	ParserInterval m_parserInterval;
	ParserAllPayer m_parserAllPayer;
	ParserNextPlayer m_parserNextPlayer;
	ParserRandomPlayer m_parserRandomPlayer;
	
	public CommandParser(main_MCBP plugin){
		this.m_plugin = plugin;
		m_CommandParts = new ArrayList<ParsedCommand>();
		bLogToConsole = m_plugin.getConfig().getBoolean("MultiCommandBlock.logtoconsole");
	}
	
	public CommandParser(String CommandStr){
		
	}
	
	public CommandParser(ParsedCommand postIntervalParsedCommentObj, Player[] playerList){
		
	}
	
	public ParserInterval getIntervalParser(){
		return m_parserInterval;
	}
	
	public void Parse(String CommandStr, Block fromBlock){
		m_CommandParts.clear();
		this.m_fromBlock = fromBlock;
		String[] commands = new String[0];
		String lowerstring = CommandStr.toLowerCase(); // only for checking the first Characters
		String checkerStr = "";
		Player[] playerList = null;
		ParsedCommand parsedCommandforInterval = null;
		
		if (lowerstring.substring(0, 4).equals("/mcb") ||
			lowerstring.substring(0, 3).equals("mcb")){
			if (CommandStr.substring(0,1).equals("/")){
				checkerStr = CommandStr.replace("/", "");
			}
			checkerStr = checkerStr.replace(checkerStr.substring(0, 3), ""); // delete mcb
			if(bLogToConsole==true)  { System.out.println(checkerStr); }
			
			commands = checkerStr.split(";", -1);
			if (commands==null || commands.length==1)  {
				//wenn kein semikolon vorhanden dann nimm den ganzen string auf platz 0 ;)
				commands = new String[1];
				commands[0] = checkerStr.trim();
				if(bLogToConsole==true)  { System.out.println("Parse -> MCB Command with empty Parameters found"); }
			}
			for(int i =0; i < commands.length; i++){
				playerList = m_plugin.getServer().getOnlinePlayers(); // alle Player
				
				m_parserInterval = new ParserInterval(commands[i].trim());
				parsedCommandforInterval = m_parserInterval.parseInterval();
				
				// Load Modules
				m_parserAllPayer = new ParserAllPayer(parsedCommandforInterval, playerList);
				m_parserNextPlayer = new ParserNextPlayer(parsedCommandforInterval, playerList);
				m_parserRandomPlayer = new ParserRandomPlayer(parsedCommandforInterval, playerList);
				
				int exist = 0;
				System.out.println("Parse -> exist" + exist);
				// parse @a
				exist += m_parserAllPayer.Parse();
				System.out.println("Parse -> exist" + exist);
				// parse @p
				exist += m_parserNextPlayer.Parse();
				System.out.println("Parse -> NextPlayer" + exist);
				// parse @r
				exist += m_parserRandomPlayer.Parse();
				System.out.println("Parse -> exist" + exist);
				
				if (exist == 0){
					// take the rest
					ParsedCommand newParsedCommand = new ParsedCommand();
					newParsedCommand.setInterval(newParsedCommand.getInterval());
					newParsedCommand.setCommand(CommandStr);
					m_CommandParts.add(newParsedCommand);
				}
			}
		}
		else{
			if(bLogToConsole==true)  { System.out.println("Parse -> No mcb Command found"); }
		}
		lowerstring = null;
		checkerStr = null;
		commands = null;
	}
	
	public ParsedCommand[] getCommands() 
	{
		if(bLogToConsole==true)  { System.out.println("getCommands ->start"); }
		ParsedCommand[] outArray = 
				m_CommandParts.toArray(new ParsedCommand[m_CommandParts.size()]);
		//System.out.println(outArray.length);
		//System.out.println("getCommands -> end");
		return outArray;
	}
	
	//Destructor alternative unter Java
	@Override
	protected void finalize() throws Throwable {
		if (m_CommandParts != null){
			m_CommandParts.clear();
			m_CommandParts = null;
		}
		m_fromBlock = null;
		
		m_parserInterval = null;
		m_parserAllPayer = null;
		m_parserNextPlayer = null;
		m_parserRandomPlayer = null;
		super.finalize();
	}
}

