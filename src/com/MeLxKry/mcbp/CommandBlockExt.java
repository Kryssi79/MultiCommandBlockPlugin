package com.MeLxKry.mcbp;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;


public class CommandBlockExt //  implements Listener   , CommandSender
{
	main_MCBP plugin;
	CommandBlock m_CommandBlock;  //  = (CommandBlock)state    = BlockState state
	String m_CommandsString;
	CommandParser m_parser;
	boolean m_bLogtoConsole = false;
	
	
	public CommandBlockExt(main_MCBP plugin) 
	{
		this.plugin = plugin;
		this.m_parser = new CommandParser(plugin);
	}
	
	
	
	public void onBlockRedstone(BlockRedstoneEvent event) 
	{
		//wenn: am Block  redstone  gebaut/abgebaut   bzw. Signal sich �ndert
		Block block = event.getBlock();
		int iBlockID = block.getTypeId();
		int iPower = 0;
		if ( !(iBlockID==137) )   { return; }
		BlockState blState = event.getBlock().getState();
		if (blState instanceof CommandBlock)   // Kommandoblock
		{
			m_CommandBlock = (CommandBlock)blState;
			try
			{
				iPower = block.getBlockPower();
				if(iPower==0) { return; }
			}  catch  ( Exception exc ) { iPower = 0; return; }
			System.out.println(" MCBP plugin:  Kommandoblock  onBlockRedstone()  ");
			System.out.println(" MCBP plugin:   BlockPower =  " + iPower);
			
			m_CommandsString = m_CommandBlock.getCommand();
			
			System.out.println(" MCBP plugin:    " + m_CommandsString);
			// logTo(String sLogStr)
			//  =>  weiter an   CommandParser()
			ParsedCommand[] commands = getCommands(m_CommandsString, block);
			for (int i=0; i < commands.length;i++)
			{	
				//CommandSender oSender = (CommandSender) m_CommandBlock.getBlock();
				//BlockCommandSender bcs = (BlockCommandSender)m_CommandBlock;
				sendeBefehl(commands[i].getCommand());
				System.out.println("send Command: " + commands[i].getCommand());
				try {
					Thread.sleep(commands[i].getInterval());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	
	private ParsedCommand[] getCommands(String sCommandsString, Block fromblock)
	{	
		m_parser.Parse(sCommandsString, fromblock);
		return m_parser.getCommands();
	}
	
	
	
	
	private boolean sendeBefehl(String sCmd)
	{
		// BlockCommandSender    /   plugin.getServer().getConsoleSender()
		return sendeBefehl(plugin.getServer().getConsoleSender(), sCmd);
	}
	
	private boolean sendeBefehl(CommandSender oSender, String sCmd)
	{
		//Bukkit.dispatchCommand(oSender, sCmd);
		plugin.getServer().dispatchCommand(oSender, sCmd);
		return true;
	}
	
	
	//  TODO:  
	private void logTo(String sLogStr)
	{
		// boolean m_bLogtoConsole = false;
	}
	
	
}
