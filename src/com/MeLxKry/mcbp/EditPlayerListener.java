package com.MeLxKry.mcbp;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
// import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.material.*;
import org.bukkit.block.*;
import org.bukkit.craftbukkit.v1_5_R3.block.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.MeLxKry.mcbp.*;



public class EditPlayerListener implements Listener 
{

		
	main_MCBP plugin;
	
	public EditPlayerListener(main_MCBP plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.NORMAL )
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		int iBlockID = block.getTypeId();
		player.sendMessage(ChatColor.GREEN + " Interact " );
		player.sendMessage("    TypeId: " + iBlockID);
		
		if(iBlockID==137)   // Kommandoblock
		{
			//   http://minecraft-de.gamepedia.com/Kommandoblock
			org.bukkit.event.block.Action oBlAct = event.getAction();
			player.sendMessage("     Class: " + block.getClass() + "  ");
			player.sendMessage("            " + block.getClass().getName());
			player.sendMessage("   event Action: " + oBlAct.toString());
			player.sendMessage("     Power: " + block.getBlockPower() );
			player.sendMessage("     State: " + block.getState() );
			player.sendMessage("     Type: " + block.getType() );

			if(oBlAct == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK)
			{
				// rechte MausTaste  auf Kommandoblock
				BlockState blState = event.getClickedBlock().getState();
				CommandBlockExt cmdBlockExt = new CommandBlockExt(plugin, blState);
			}
		}
	}
	
	
}
