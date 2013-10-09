package com.MeLxKry.mcbp.logging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;

import com.MeLxKry.mcbp.main_MCBP;

public class BlockLogger {
	main_MCBP plugin;
	CommandBlock m_CommandBlock;
	
	public BlockLogger(main_MCBP plugin){
		this.plugin = plugin;
	}
	
	//Put a CommandBlock Info to the Log File
	public boolean log(BlockState state) {
		boolean logged = false;
		
		if (state instanceof CommandBlock)
		{
			PrintWriter printWriter;
			m_CommandBlock = (CommandBlock)state;
			
			// Block infos
			String worldStr = m_CommandBlock.getWorld().toString();
			int x = m_CommandBlock.getX();
			int y = m_CommandBlock.getY();
			int z = m_CommandBlock.getZ();
			

			try {
	            // Will overwrite the file if exists or creates new
	            printWriter = new PrintWriter(
	                    "mcbP.log",
	                    "UTF-8");
	            printWriter.println(worldStr + ";" + x + ";" + y + ";" + z);
	            printWriter.close();
	        } catch (FileNotFoundException fileNotFoundException) {
	            fileNotFoundException.printStackTrace();
	        } catch (UnsupportedEncodingException unsupportedEncodingException) {
	            unsupportedEncodingException.printStackTrace();
	        }
			
			//printWriter.println(worldStr + ";" + x + ";" + y + ";" + z);
			
			logged = true;
		}
		// else ignore all other blocks
		return logged;
	}
	
	//Load all new Command Blocks from Log File
	public CommandBlock[] loadCommandBlocks() {
		FileInputStream fstream;
		BufferedReader br;
		List<CommandBlock> templist = new ArrayList<CommandBlock>();
		try {
            // Will overwrite the file if exists or creates new
			fstream = new FileInputStream("mcbP.log");
		    br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  String[] splittStr = strLine.split(";");
			  
			  // Block infos
			  if (splittStr.length == 3)
			  {
				String worldStr = splittStr[0];
			    int x =  Integer.parseInt(splittStr[1]);
				int y =  Integer.parseInt(splittStr[2]);
				int z =  Integer.parseInt(splittStr[3]);
				
				BlockState _state = (BlockState) plugin.getServer().getWorld(worldStr).getBlockAt(x, z, y);
				if (_state instanceof CommandBlock)
				{
					CommandBlock newBlock = (CommandBlock) plugin.getServer().getWorld(worldStr).getBlockAt(x, z, y);
					templist.add(newBlock);
				}
			  }
			}
			fstream.close();
			br.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace(); }
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); }
		
		return (CommandBlock[]) templist.toArray();
	}
}
