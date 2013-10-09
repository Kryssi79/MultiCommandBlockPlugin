package com.MeLxKry.mcbp;

public class CommandParser {
	String[] m_CommandParts;

	public void Parse(String CommandStr)
	{
		String lowerstring = CommandStr.toLowerCase(); // only for checking the first Characters
		m_CommandParts = new String[0];
		String checkStr1 = lowerstring.substring(0, 4);
		String checkStr2 = lowerstring.substring(0, 3);

		if (checkStr1.equals("/mcb") ||
			checkStr2.equals("mcb"))
		{
			String checkerStr = "";
			if (CommandStr.substring(0,1).equals("/"))
			{
				checkerStr = CommandStr.replace("/", "");
			}
			checkerStr = checkerStr.replace(checkerStr.substring(0, 3), ""); // delete mcb
			System.out.println(checkerStr);
			
			m_CommandParts = checkerStr.split(";", -1);
			if (m_CommandParts==null || m_CommandParts.length==0)  {
				//wenn kein semikolon vorhanden dann nimm den ganzen string auf platz 0 ;)
				m_CommandParts = new String[1];
				m_CommandParts[0] = CommandStr;
				System.out.println("Bin hier:");
			    System.out.println("Parse -> MCB Command with empty Parameters found");
			}
		    System.out.println("Commands in Array: " + m_CommandParts.length);
		}
		else
		{
			// Nix f�r mich ;)
			 System.out.println("Parse -> No mcb Command found");
		}
	}
	
	public String[] getCommands() {
		return m_CommandParts;
	}
	
	//Destructor alternative unter Java
	@Override
	protected void finalize() throws Throwable {
		m_CommandParts = null;
		super.finalize();
	}
}
