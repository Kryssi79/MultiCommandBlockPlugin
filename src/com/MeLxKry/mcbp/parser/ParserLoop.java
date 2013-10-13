package com.MeLxKry.mcbp.parser;

import org.bukkit.entity.Player;


public class ParserLoop extends CommandParser  
{
	
	CommandParser m_Parent;
	
	public ParserLoop(CommandParser myParent) {
		this.m_Parent = myParent;
	}
	
	protected void Parse(ParsedCommand parsedCommand)
	{
		if (parsedCommand.getCommand().contains(" LOOP:"))
		{
			if(m_Parent.bLogToConsole==true)  { System.out.println(" Parser LOOP  "); }
			
		}
		
	}
	

}
