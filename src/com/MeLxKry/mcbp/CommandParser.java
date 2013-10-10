package com.MeLxKry.mcbp;

public class CommandParser {
	ParsedCommand[] m_CommandParts;

	public void Parse(String CommandStr)
	{
		String[] commands = new String[0];
		String lowerstring = CommandStr.toLowerCase(); // only for checking the first Characters
		m_CommandParts = new ParsedCommand[0];
		String checkerStr = "";
		String[] intervalSplittArray = new String[0];
		
		if (lowerstring.substring(0, 4).equals("/mcb") ||
			lowerstring.substring(0, 3).equals("mcb"))
		{
			if (CommandStr.substring(0,1).equals("/"))
			{
				checkerStr = CommandStr.replace("/", "");
			}
			checkerStr = checkerStr.replace(checkerStr.substring(0, 3), ""); // delete mcb
			System.out.println(checkerStr);
			

			commands = checkerStr.split(";", -1);
			if (m_CommandParts==null || m_CommandParts.length==1)  {
				//wenn kein semikolon vorhanden dann nimm den ganzen string auf platz 0 ;)
				commands = new String[1];
				commands[0] = checkerStr.trim();
			    System.out.println("Parse -> MCB Command with empty Parameters found");
			}
			
			for(int i =0; i < commands.length; i++)
			{
				ParsedCommand newParsedCommand = new ParsedCommand();
				intervalSplittArray = commands[i].split("#");
				if (intervalSplittArray.length == 2)
				{
					newParsedCommand.setInterval(Integer.parseInt(intervalSplittArray[1])); // set Interval
				}
				newParsedCommand.setCommand(intervalSplittArray[0].trim()); // set Command
			}
		    System.out.println("Commands in Array: " + m_CommandParts.length);
		}
		else
		{
			// Nix f�r mich ;)
			 System.out.println("Parse -> No mcb Command found");
		}
		lowerstring = null;
		checkerStr = null;
		commands = null;
		intervalSplittArray = null;
	}
	
	
	public ParsedCommand[] getCommands() {
		return m_CommandParts;
	}
	
	
	//Destructor alternative unter Java
	@Override
	protected void finalize() throws Throwable {
		m_CommandParts = null;
		super.finalize();
	}
}

