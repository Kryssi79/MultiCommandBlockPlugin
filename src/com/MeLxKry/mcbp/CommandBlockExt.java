package com.MeLxKry.mcbp;

import org.bukkit.block.*;

public class CommandBlockExt {
	main_MCBP plugin;
	CommandBlock m_CommandBlock;
	String m_CommandsString;
	
	public CommandBlockExt(main_MCBP plugin, BlockState state) {
		this.plugin = plugin;
		if (state instanceof CommandBlock)
		{
			m_CommandBlock = (CommandBlock)state;
		}
		getCommand();
	}
	
	private void getCommand()
	{
		m_CommandsString = m_CommandBlock.getCommand();
	}
	
}
