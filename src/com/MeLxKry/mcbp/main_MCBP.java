package com.MeLxKry.mcbp;

// import java.io.IOException;

//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main_MCBP extends JavaPlugin 
{

	// public PluginDescriptionFile pdf = this.getDescription();
	
	public PluginManager pluginMan;
	private EditPlayerListener listener;
	
    
    @Override
   public void onEnable(){
        // getLogger().info(pdf.getName() + " v" + pdf.getVersion() + " Enabled!");
        System.out.println("Starte: Multi Command Block Plugin ...  ");
        
        pluginMan = getServer().getPluginManager();

        pluginMan.registerEvents(listener, this);
   }
    
   
    
    @Override
   public void onDisable() {
    	System.out.println("beende:  Multi Command Block Plugin ");
   }
    
    
}
