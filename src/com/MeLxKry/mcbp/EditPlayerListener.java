package com.MeLxKry.mcbp;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
// import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
// import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.block.Sign;


public class EditPlayerListener implements Listener 
{

		
	@EventHandler(priority=EventPriority.NORMAL )
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		int iBlockID = block.getTypeId();
		player.sendMessage(" Interact " );
		player.sendMessage("    TypeId: " + iBlockID);
		if(iBlockID==137)
		{
			player.sendMessage("    Class: " + block.getClass() + "  ");
			player.sendMessage("            " + block.getClass().getName());
		}
	}
	
}
