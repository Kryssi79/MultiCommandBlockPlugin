package com.MeLxKry.mcbp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockListener implements Listener {
	main_MCBP plugin;
	
	public BlockListener(main_MCBP plugin) {
		this.plugin = plugin;
	}
	

	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
	}
	
	@EventHandler
	public void stopPlacement(BlockPlaceEvent event) {
	}
	
	@EventHandler
	public void stopBreaking(BlockBreakEvent event) {
	}
	
    @EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPistonExtend(BlockPistonExtendEvent event) {
	}
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockDamage(BlockDamageEvent event) {
    }
}
