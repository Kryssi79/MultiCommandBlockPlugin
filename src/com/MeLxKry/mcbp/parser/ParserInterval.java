package com.MeLxKry.mcbp.parser;

import com.MeLxKry.mcbp.main_MCBP;

public class ParserInterval extends CommandParser 
{
	
	CommandParser oParent;
	
	
	public ParserInterval(CommandParser myParent) {
		//super();
		this.oParent = myParent;
	}

	protected ParsedCommand parseInterval(String CommandStr) {
		ParsedCommand pcommand = new ParsedCommand();
		String[] intervalSplittArray = new String[0];
		
		if (CommandStr == null) {return null;}
		
		intervalSplittArray = CommandStr.split("#");
		if (intervalSplittArray.length == 2) {
			pcommand.setInterval(Integer.parseInt(intervalSplittArray[1])); // set Interval
		}
		// override CommandStr Reference
		pcommand.setCommand(intervalSplittArray[0].trim());  // ist neu  und   base nicht bekannt
		//oParent.parsedCommandforInterval.setCommand(intervalSplittArray[0].trim());
		System.out.println("Parent.commands.Len "+oParent.commands.length);
		return pcommand;
	}
}
