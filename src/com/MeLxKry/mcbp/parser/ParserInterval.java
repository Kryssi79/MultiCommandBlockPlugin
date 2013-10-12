package com.MeLxKry.mcbp.parser;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserInterval extends CommandParser {
	
	public ParserInterval() {
		super();
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
		pcommand.setCommand(intervalSplittArray[0].trim());
		return pcommand;
	}
}
