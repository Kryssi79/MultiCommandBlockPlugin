package com.MeLxKry.mcbp.parser;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
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
		// create Player from Block World List
		List<Player> playerfromBlockWorldList = new ArrayList<Player>();
		// compare player World with block current Block World
		for (int i =0; i < playerlist.length; i++){
			if (playerlist[i].getWorld().equals(m_Parent.m_fromBlock.getWorld())){
				playerfromBlockWorldList.add(playerlist[i]);
			}
		}
		
		if (parsedCommand.getCommand().contains("@r")){
			exist = 1;
			int sizeofPlayerList = playerfromBlockWorldList.size();
			if (sizeofPlayerList != 0)
			{
				int rand = Helper.nextRandomInt(sizeofPlayerList);
				if(m_Parent.bLogToConsole==true)
				{
					System.out.println("ParserRandomPlayer -> @r");
					System.out.println("Randomize Value = " + rand);
				}
				ParsedCommand newParsedCommand = new ParsedCommand();
				newParsedCommand.setInterval(parsedCommand.getInterval());
				String newCommand = parsedCommand.getCommand().replace
				("@r", playerlist[rand].getDisplayName());
				
				newParsedCommand.setCommand(newCommand);
				m_Parent.m_CommandParts.add(newParsedCommand);
			}
		}
		// clean
		playerfromBlockWorldList.clear();
		playerfromBlockWorldList = null;
		
		
		return exist;
	}
}
