package com.MeLxKry.mcbp;

// import java.io.IOException;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class main_MCBP extends JavaPlugin 
{
	public PluginManager pluginMan;
	
    
    @Override
   public void onEnable()
   {
        System.out.println("Starte: Multi Command Block Plugin ...  ");
        pluginMan = getServer().getPluginManager();
        
        // Commands: 
        this.getCommand("mcbTest").setExecutor(new testCommand());
        
        // Events: 
        getServer().getPluginManager().registerEvents(new EditPlayerListener(), this);
   }
    
   
    
    
    @Override
   public void onDisable() {
    	System.out.println("beende:  Multi Command Block Plugin ");
   }
    
    
    
}
