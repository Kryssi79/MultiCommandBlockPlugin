package com.MeLxKry.mcbp;

import org.bukkit.block.*;
import org.bukkit.command.CommandSender;

public class CommandBlockExt 
{
	main_MCBP plugin;
	CommandBlock m_CommandBlock;
	String m_CommandsString;
	
	
	public CommandBlockExt(main_MCBP plugin, BlockState state) {
		this.plugin = plugin;
		if (state instanceof CommandBlock)
		{
			m_CommandBlock = (CommandBlock)state;
		}
		getCommand();
	}
	
	
	private void getCommand()
	{
		// oder über   org.bukkit.command.CommandExecutor  
		m_CommandsString = m_CommandBlock.getCommand();
		sendeBefehl("say hello World");
	}
	
	
	private boolean sendeBefehl(String sCmd)
	{
		return sendeBefehl(plugin.getServer().getConsoleSender(), sCmd);
	}
	
	private boolean sendeBefehl(CommandSender oSender, String sCmd)
	{
		// org.bukkit.command.CommandExecutor  
		// getServer().dispatchCommand(getServer().getConsoleSender(), "BEFEHL");
		plugin.getServer().dispatchCommand(oSender, sCmd);
		return true;
	}
	
}
