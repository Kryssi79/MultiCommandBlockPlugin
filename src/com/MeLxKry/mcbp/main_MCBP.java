package com.MeLxKry.mcbp;

// import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.MeLxKry.mcbp.parser.CommandParser;
import com.MeLxKry.mcbp.parser.ParsedCommand;



public class main_MCBP extends JavaPlugin 
{
	public PluginManager pluginMan;
	String m_CommandsString;
	CommandParser m_parser;
	boolean bLogToConsole = false;	
	
    
    @Override
   public void onEnable()
   {
        System.out.println("Starte: Multi Command Block Plugin ...  ");
        pluginMan = getServer().getPluginManager();
        this.m_parser = new CommandParser(this);
        System.out.println("  Parser OK  ");
        
        ConfigSpeichern();
        bLogToConsole = this.getConfig().getBoolean("MultiCommandBlock.logtoconsole");
        
        // Commands: 
        // this.getCommand("MCB").setExecutor(new MCB_Command());
        
        // Events: 
        // getServer().getPluginManager().registerEvents(new EditPlayerListener(this), this);
   }
    
   
    @Override
   public void onDisable() {
    	System.out.println("beende:  Multi Command Block Plugin ");
   }
    
    @Override
	public boolean onCommand(CommandSender cs, Command cmd, String strLabel, String[] args) 
	{
    	if(cmd.getName().equals("MCB")) 
    	{
	    	if(args.length == 0)  //  nur  /MCB  ohne Params
	    	{
	    		if(bLogToConsole==true)  { System.out.println("  MCB   sendet ...   "); }
	    		return true;
	    	}
	    	else
			{
				if ((cs instanceof BlockCommandSender)) 
				{
					final BlockCommandSender blockCmdSender = (BlockCommandSender)cs;
					Block block = blockCmdSender.getBlock();
					CommandBlock m_CommandBlock = (CommandBlock)block.getState();
					m_CommandsString = m_CommandBlock.getCommand();
					if(bLogToConsole==true)  { System.out.println("     "); }
					if(bLogToConsole==true)  { System.out.println(" BlockCommandSender sendet ... "); }
					if(bLogToConsole==true)  { System.out.println("     " + m_CommandsString );       } 
					ParsedCommand[] commands = getCommands(m_CommandsString, block);
					
					int iVorGesTime = 0;
					for (int i=0; i < commands.length;i++)
					{	
						final String fiStr = commands[i].getCommand();
						int iIntTime = 0;
						if(i == 0) {
							iIntTime = commands[i].getInterval() * 20;
							iVorGesTime += iIntTime;
						}
						else {
							iIntTime = iVorGesTime + (commands[i].getInterval() * 20);
							iVorGesTime += iIntTime;
						}
						
				        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() 
				        {
				        	public void run() 
				        	{
				        		//hier rein:  sendeBefehl 
				        		sendeBefehl(blockCmdSender, fiStr);
				        	}
				        }, iIntTime );   //   * 20  ????
				        
						/*
						try {
							Thread.sleep(commands[i].getInterval());  // commands[i].getInterval()
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						*/
					}
				}
				return true;
			}
    	}
    	return false;
	}
    
	private ParsedCommand[] getCommands(String sCommandsString, Block fromBlock)
	{	
		m_parser.Parse(sCommandsString, fromBlock);
		return m_parser.getCommands();
	}
	
	
	// https://board.nitrado.net/community-area/programmierung/hilfe/49382/explosionen-erzeugen-wait/
	private boolean sendeBefehl(String sCmd)
	{
		return sendeBefehl(getServer().getConsoleSender(), sCmd);
	}
	
	private boolean sendeBefehl(CommandSender oSender, String sCmd)
	{
		if(bLogToConsole==true)  {  System.out.println("send Command: " +  sCmd); }
		//Bukkit.dispatchCommand(oSender, sCmd);
		getServer().dispatchCommand(oSender, sCmd);
		return true;
	}
    
   
    
    private void ConfigSpeichern()
    {
    	// saveConfig();
    	this.saveDefaultConfig();
    	//this.saveResource(arg0, arg1);
    }
    
}
