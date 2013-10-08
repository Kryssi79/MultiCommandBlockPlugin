package com.MeLxKry.mcbp.pluginCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;




public class MCB_Command  implements CommandExecutor  
{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String strLabel, String[] args) 
	{
		if(args.length == 0)  //  nur  /MCB  ohne Params
		{
			cs.sendMessage(" MCB  OK ... ");
			return true;
		}
		else
		{
			cs.sendMessage(ChatColor.GREEN + "  strLbl:  " + strLabel );
			
			for(int iArg=0; iArg<args.length; iArg++)
			{
				cs.sendMessage(" args["+iArg+"] :  " + args[iArg] );
			}
			return true;
		}
		
		// return true;
	}

}
