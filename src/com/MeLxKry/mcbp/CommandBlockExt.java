package com.MeLxKry.mcbp;

import org.bukkit.block.*;
import org.bukkit.command.CommandSender;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;


public class CommandBlockExt implements Listener 
{
	main_MCBP plugin;
	CommandBlock m_CommandBlock;  //  = (CommandBlock)state    = BlockState state
	String m_CommandsString;
	
	
	public CommandBlockExt(main_MCBP plugin) 
	{
		this.plugin = plugin;
	}
	
	public CommandBlockExt(main_MCBP plugin, BlockState state) 
	{
		this.plugin = plugin;
		if (state instanceof CommandBlock)
		{
			m_CommandBlock = (CommandBlock)state;
		}
		getCommand();
	}
	
		
	@EventHandler(priority=EventPriority.NORMAL )
	public void onBlockRedstone(BlockRedstoneEvent event) 
	{
		//wenn: am Block  redstone  gebaut/abgebaut   bzw. Signal sich ändert
		Block block = event.getBlock();
		int iBlockID = block.getTypeId();
		if ( !(iBlockID==137) )   { return; }
		BlockState blState = event.getBlock().getState();
		if (blState instanceof CommandBlock)   // Kommandoblock
		{
			m_CommandBlock = (CommandBlock)blState;
			int iPower= block.getBlockPower();
			if(iPower==0) { return; }
			System.out.println(" MCBP plugin:  Kommandoblock  onBlockRedstone()  ");
			System.out.println(" MCBP plugin:   BlockPower =  " + iPower);
			m_CommandsString = m_CommandBlock.getCommand();
			if ( m_CommandsString.contains( "/MCB" ) )
			{
				System.out.println(" MCBP plugin:    " + m_CommandsString);
			}
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL )
	public void onBlockSignChange(SignChangeEvent event) 
	{
		Block block = event.getBlock();
		int iBlockID = block.getTypeId();
		if(iBlockID==137)   // Kommandoblock
		{
			System.out.println(" MCBP plugin:  Kommandoblock  onBlockSignChange()  ");
		}
	}
	
	
	private void getCommand()
	{
		// oder über   org.bukkit.command.CommandExecutor  
		m_CommandsString = m_CommandBlock.getCommand();
		//sendeBefehl("say hello World");
	}
	
	
	private boolean sendeBefehl(String sCmd)
	{
		// BlockCommandSender    /   plugin.getServer().getConsoleSender()
		return sendeBefehl(plugin.getServer().getConsoleSender(), sCmd);
	}
	
	private boolean sendeBefehl(CommandSender oSender, String sCmd)
	{
		// org.bukkit.command.CommandExecutor  
		// getServer().dispatchCommand(getServer().getConsoleSender(), "BEFEHL");
		
		//für  eigene/interne   Plugin Commands
		//org.bukkit.command.PluginCommand cmd = plugin.getServer().getPluginCommand("say moin Kryssi");
		//cmd.execute(player, command, arguments);
		
		plugin.getServer().dispatchCommand(oSender, sCmd);
		return true;
	}
	
	
	
}
