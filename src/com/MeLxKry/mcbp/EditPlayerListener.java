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

import com.sun.corba.se.spi.orbutil.fsm.Action;


public class EditPlayerListener implements Listener 
{

		
	@EventHandler(priority=EventPriority.NORMAL )
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		int iBlockID = block.getTypeId();
		player.sendMessage(" Interact " );
		player.sendMessage("    TypeId: " + iBlockID);
		
		if(iBlockID==137)   // Kommandoblock
		{
			//   http://minecraft-de.gamepedia.com/Kommandoblock
			org.bukkit.event.block.Action oBlAct = event.getAction();
			player.sendMessage("    Class: " + block.getClass() + "  ");
			player.sendMessage("            " + block.getClass().getName());
			player.sendMessage("    Action: " + oBlAct.toString());
			if(oBlAct == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK)
			{
				// rechte MausTaste  auf Kommandoblock
			}
		}
	}
	
	
}
