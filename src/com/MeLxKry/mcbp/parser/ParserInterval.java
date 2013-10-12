package com.MeLxKry.mcbp.parser;
import com.MeLxKry.mcbp.main_MCBP;

public class ParserInterval extends CommandParser {
	
	private String m_Command;
	
	public ParserInterval(String CommandStr) {
		super(CommandStr);
		m_Command = CommandStr;
	}

	protected ParsedCommand parseInterval() {
		ParsedCommand pcommand = new ParsedCommand();
		String[] intervalSplittArray = new String[0];
		
		if (m_Command == null) {return null;}
		
		intervalSplittArray = m_Command.split("#");
		if (intervalSplittArray.length == 2) {
			pcommand.setInterval(Integer.parseInt(intervalSplittArray[1])); // set Interval
		}
		// override CommandStr Reference
		pcommand.setCommand(intervalSplittArray[0].trim());
		return pcommand;
	}
	
	@Override
	protected void finalize() throws Throwable {
		m_Command = null;
	}
}
