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

import com.MeLxKry.mcbp.pluginCommands.*;



public class main_MCBP extends JavaPlugin 
{
	public PluginManager pluginMan;
	String m_CommandsString;
	CommandParser m_parser;
	boolean m_bLogtoConsole = false;	
	
    
    @Override
   public void onEnable()
   {
        System.out.println("Starte: Multi Command Block Plugin ...  ");
        pluginMan = getServer().getPluginManager();
        this.m_parser = new CommandParser();
        System.out.println("  Parser OK  ");
        
        ConfigSpeichern();
        
        // Commands: 
        // this.getCommand("mcbTest").setExecutor(new testCommand());
        // this.getCommand("MCB").setExecutor(new MCB_Command());
        
        // Events: 
        // getServer().getPluginManager().registerEvents(new EditPlayerListener(this), this);
        // getServer().getPluginManager().registerEvents(new CommandBlockExt(this), this);
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
	    		System.out.println("  MCB   sendet ...   ");
	    		return true;
	    	}
	    	else
			{
				if ((cs instanceof BlockCommandSender)) 
				{
					BlockCommandSender blockCmdSender = (BlockCommandSender)cs;
					Block block = blockCmdSender.getBlock();
					CommandBlock m_CommandBlock = (CommandBlock)block.getState();
					m_CommandsString = m_CommandBlock.getCommand();
					System.out.println("     ");
					System.out.println(" BlockCommandSender sendet ... ");
					System.out.println("     " + m_CommandsString );
					String[] commands = getCommands(m_CommandsString);
					for (int i=0; i < commands.length;i++)
					{	
						sendeBefehl(cs, commands[i].trim());
						System.out.println("send Command: " + commands[i].trim());
					}
				}
				return true;
			}
    	}
    	return false;
	}
    
    
	private String[] getCommands(String sCommandsString)
	{	
		m_parser.Parse(sCommandsString);
		return m_parser.getCommands();
	}
	
	
	private boolean sendeBefehl(String sCmd)
	{
		return sendeBefehl(getServer().getConsoleSender(), sCmd);
	}
	
	private boolean sendeBefehl(CommandSender oSender, String sCmd)
	{
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
