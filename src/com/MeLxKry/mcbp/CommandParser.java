package com.MeLxKry.mcbp;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class CommandParser {
	ParsedCommand[] m_CommandParts;
	main_MCBP m_plugin;
	Block m_fromBlock;
	
	public CommandParser(main_MCBP plugin){
		this.m_plugin = plugin;
	}
	
	public void Parse(String CommandStr, Block fromBlock)
	{
		this.m_fromBlock = fromBlock;
		String[] commands = new String[0];
		String lowerstring = CommandStr.toLowerCase(); // only for checking the first Characters
		m_CommandParts = new ParsedCommand[0];
		String checkerStr = "";
		
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
			parseInterval(commands); // continue parsing
		}
		else
		{
			// Nix fuer mich ;)
			 System.out.println("Parse -> No mcb Command found");
		}
		lowerstring = null;
		checkerStr = null;
		commands = null;
	}
	
	
	protected void parseInterval(String[] CommandArray)
	{
		String[] intervalSplittArray = new String[0];
		m_CommandParts = new ParsedCommand[CommandArray.length];
		for(int i =0; i < CommandArray.length; i++)
		{
			ParsedCommand newParsedCommand = new ParsedCommand();
			intervalSplittArray = CommandArray[i].split("#");
			if (intervalSplittArray.length == 2)
			{
				newParsedCommand.setInterval(Integer.parseInt(intervalSplittArray[1])); // set Interval
			}
			String freshCommand = intervalSplittArray[0].trim();
			
			//Checking @p  @a  @r
			freshCommand = replacePlatzhalter(freshCommand);
			newParsedCommand.setCommand(freshCommand); // set Command
			
			m_CommandParts[i] = newParsedCommand;
			// TODO:  sowas wie  newParsedCommand.dispose();
			// einfach mit newParsedCommand = null  ->  -- guckst Du --> ParsedCommand finalize()
		}
		intervalSplittArray = null;
		CommandArray = null;
	}
	
	//für @p  @a  @r 
	protected String replacePlatzhalter(String CommandStr)
	{
		Player[] playerList = m_plugin.getServer().getOnlinePlayers(); // alle Player
		
		if (m_fromBlock != null)
		{
			Location blockLocation = m_fromBlock.getLocation();
			Location nextPlayerLocation = null;
			
			Player johnDoe = null; // nächster Player
			double [] distArray = new double[playerList.length];
			double nextdist = 0.0;
			for(int i =0; i < playerList.length; i++)
			{
				nextPlayerLocation = playerList[i].getLocation();
				nextdist = getDistance(blockLocation, nextPlayerLocation);
				for(int x =0; x < distArray.length; x++)
				{
					if (nextdist <  distArray[x])
					{
						johnDoe = playerList[i]; // johnDoe for President ;)
					}
				}
				distArray[i] = nextdist;
			}
		}
		// Ab hier hast du den Player der am dichtesten am Block dran steht (wenn m_fromBlock nicht null ist!)
		// und du hast die Liste aller Online Player.
		
		//  TODO:  für @p  @a  @r  - Ab hier wird weiter gehackt ;)

		// ----INFO------
		String replaced="";
		// PLayerListe
		return CommandStr; // später replaced String
	}
	
	//Entfernung
	protected double getDistance(Location vLoc1, Location vLoc2)
	{
		return  vLoc1.distance(vLoc2);
	}
	
	public ParsedCommand[] getCommands() {
		return m_CommandParts;
	}
	
	
	//Destructor alternative unter Java
	@Override
	protected void finalize() throws Throwable {
		m_CommandParts = null;
		m_fromBlock = null;
		super.finalize();
	}
}

