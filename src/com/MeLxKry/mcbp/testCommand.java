package com.MeLxKry.mcbp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class testCommand implements CommandExecutor  
{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String strLabel, String[] args) {
		if(args.length == 0)
		{
			cs.sendMessage("test-Command   OK ");
			return true;
		}
		
		return false;
	}

}
