package com.bukkit.teltow.mcbp;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCBP extends JavaPlugin {
	public PluginDescriptionFile pdf = this.getDescription();
    
     @Override
    public void onEnable(){
         getLogger().info(pdf.getName() + " v" + pdf.getVersion() + " Enabled!");
    }
    
     @Override
    public void onDisable() {
    }
}
