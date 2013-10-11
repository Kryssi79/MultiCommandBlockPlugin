package com.MeLxKry.mcbp.parser;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.MeLxKry.mcbp.main_MCBP;


public class CommandParser {
	List<ParsedCommand> m_CommandParts;
	main_MCBP m_plugin;
	Block m_fromBlock;
	
	public CommandParser(main_MCBP plugin){
		this.m_plugin = plugin;
		m_CommandParts = new ArrayList<ParsedCommand>();
	}
	
	public void Parse(String CommandStr, Block fromBlock){
		m_CommandParts.clear();
		this.m_fromBlock = fromBlock;
		String[] commands = new String[0];
		String lowerstring = CommandStr.toLowerCase(); // only for checking the first Characters
		String checkerStr = "";
		
		if (lowerstring.substring(0, 4).equals("/mcb") ||
			lowerstring.substring(0, 3).equals("mcb")){
			if (CommandStr.substring(0,1).equals("/")){
				checkerStr = CommandStr.replace("/", "");
			}
			checkerStr = checkerStr.replace(checkerStr.substring(0, 3), ""); // delete mcb
			System.out.println(checkerStr);
			
			commands = checkerStr.split(";", -1);
			if (commands==null || commands.length==1)  {
				//wenn kein semikolon vorhanden dann nimm den ganzen string auf platz 0 ;)
				commands = new String[1];
				commands[0] = checkerStr.trim();
			    System.out.println("Parse -> MCB Command with empty Parameters found");
			}
			for(int i =0; i < commands.length; i++){
				parsePlayerShortcuts(commands[i]); // continue parsing
			}
		}
		else{
			// Nix fuer mich ;)
			 System.out.println("Parse -> No mcb Command found");
		}
		lowerstring = null;
		checkerStr = null;
		commands = null;
	}

	//for @p  @a  @r 
	protected void parsePlayerShortcuts(String CommandStr)
	{
		boolean filled = false;
		Player[] playerList = m_plugin.getServer().getOnlinePlayers(); // alle Player
		
		ParsedCommand parsedCommandforInterval = parseInterval(CommandStr);
		CommandStr = parsedCommandforInterval.getCommand();
		
		System.out.println("parsePlayerShortcuts Command ->"+  CommandStr);
		System.out.println("parsePlayerShortcuts Interval ->"+  parsedCommandforInterval.getInterval());
		
		// random Online Player
		if (CommandStr.contains("@r")){
			filled = true;
			int rand = random_range(0,playerList.length - 1);
			ParsedCommand newParsedCommand = new ParsedCommand();
			newParsedCommand.setInterval(parsedCommandforInterval.getInterval());
			String newCommand = CommandStr.replace("@r", playerList[rand].getDisplayName());
			newParsedCommand.setCommand(newCommand);
			m_CommandParts.add(newParsedCommand);
		}
		// all Online Player
		if (CommandStr.contains("@a")){
			for (int i =0; i < playerList.length; i++){
				filled = true;
				ParsedCommand newParsedCommand = new ParsedCommand();
				
				// Interval Berechnung = BaseCommand interval / Menge der Commands
				int interval = parsedCommandforInterval.getInterval();
				interval = interval / playerList.length;
				
				newParsedCommand.setInterval(interval);
				String newCommand = CommandStr.replace("@a", playerList[i].getDisplayName());
				newParsedCommand.setCommand(newCommand);
				m_CommandParts.add(newParsedCommand);
			}
		}
		// next Online Player
		if (CommandStr.contains("@p")){
			if (m_fromBlock != null){
				Location blockLocation = m_fromBlock.getLocation();
				Location nextPlayerLocation = null;
				
				Player johnDoe = null; // n�chster Player
				double [] distArray = new double[playerList.length];
				double nextdist = 0.0;
				for(int i =0; i < playerList.length; i++){
					nextPlayerLocation = playerList[i].getLocation();
					nextdist = getDistance(blockLocation, nextPlayerLocation);
					for(int x =0; x < distArray.length; x++){
						if (nextdist <  distArray[x]){
							johnDoe = playerList[i]; // johnDoe for President ;)
						}
					}
					distArray[i] = nextdist;
				}
				if (johnDoe != null){
					filled = true;
					ParsedCommand newParsedCommand = new ParsedCommand();
					newParsedCommand.setInterval(parsedCommandforInterval.getInterval());
					String newCommand = CommandStr.replace("@p", johnDoe.getDisplayName());
					newParsedCommand.setCommand(newCommand);
					m_CommandParts.add(newParsedCommand);
				}
			}
		}
		// if never filled !
		if (!filled){
			ParsedCommand newParsedCommand = new ParsedCommand();
			newParsedCommand.setInterval(newParsedCommand.getInterval());
			newParsedCommand.setCommand(CommandStr);
			m_CommandParts.add(newParsedCommand);
		}
		parsedCommandforInterval = null;
		playerList = null;
		
		System.out.println("parsePlayerShortcuts End");
	}
	
	protected ParsedCommand parseInterval(String CommandStr){
		ParsedCommand pcommand = new ParsedCommand();
		String[] intervalSplittArray = new String[0];
		intervalSplittArray = CommandStr.split("#");
		if (intervalSplittArray.length == 2){
			pcommand.setInterval(Integer.parseInt(intervalSplittArray[1])); // set Interval
		}
		// override CommandStr Reference
		pcommand.setCommand(intervalSplittArray[0].trim());
		return pcommand;
	}
	
	private int random_range(int x1, int x2) {
	    return (int) (Math.floor(x1 + (Math.random() * (x2 - x1))));
	} 
	
	//Entfernung
	protected double getDistance(Location vLoc1, Location vLoc2){
		return  vLoc1.distance(vLoc2);
	}
	
	public ParsedCommand[] getCommands() 
	{
		System.out.println("getCommands ->start");
		ParsedCommand[] outArray = 
				m_CommandParts.toArray(new ParsedCommand[m_CommandParts.size()]);
		System.out.println(outArray.length);
		System.out.println("getCommands -> end");
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
		super.finalize();
	}
}

