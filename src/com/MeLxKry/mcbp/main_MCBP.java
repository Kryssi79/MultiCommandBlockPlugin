package com.MeLxKry.mcbp;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;



public class main_MCBP extends JavaPlugin 
{

	public PluginDescriptionFile pdf = this.getDescription();
    
    @Override
   public void onEnable(){
        // getLogger().info(pdf.getName() + " v" + pdf.getVersion() + " Enabled!");
        System.out.println("Starte: Multi Command Block Plugin ...  Ver.");
   }
   
    @Override
   public void onDisable() {
    	System.out.println("beende:  Multi Command Block Plugin ");
   }
    
}
