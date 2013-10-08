package com.MeLxKry.mcbp;

// import java.io.IOException;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.MeLxKry.mcbp.pluginCommands.*;



public class main_MCBP extends JavaPlugin 
{
	public PluginManager pluginMan;
	
    
    @Override
   public void onEnable()
   {
        System.out.println("Starte: Multi Command Block Plugin ...  ");
        pluginMan = getServer().getPluginManager();
        
        ConfigSpeichern();
        
        // Commands: 
        this.getCommand("mcbTest").setExecutor(new testCommand());
        this.getCommand("MCB").setExecutor(new MCB_Command());
        
        // Events: 
        getServer().getPluginManager().registerEvents(new EditPlayerListener(this), this);
   }
    
   
    @Override
   public void onDisable() {
    	System.out.println("beende:  Multi Command Block Plugin ");
   }
    
   
    
    private void ConfigSpeichern()
    {
    	// saveConfig();
    	this.saveDefaultConfig();
    	//this.saveResource(arg0, arg1);
    }
    
}
