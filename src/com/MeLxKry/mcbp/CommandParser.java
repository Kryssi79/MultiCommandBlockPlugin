package com.MeLxKry.mcbp;

public class CommandParser {
	String[] m_CommandParts;

	public void Parse(String CommandStr)
	{
		String lowerstring = CommandStr.toLowerCase(); // only for checking the first Characters
		if (lowerstring.substring(0, 3) == "mcb" || 
			lowerstring.substring(0, 4) == "/mcb")
		{
			if (CommandStr.substring(0,1) == "/")
			{
				CommandStr.replace("/", "");
			}
			CommandStr.replace(lowerstring.substring(0, 3), ""); // delete mcb
			
			m_CommandParts = CommandStr.split(";");
			if (m_CommandParts.length == 0)  {
				//wenn kein semikolon vorhanden dann nimm den ganzen string auf platz 0 ;)
				m_CommandParts = new String[1];
				m_CommandParts[0] = CommandStr;}
			else{
				System.out.println("Parse -> MCB Command with empty Parameters found");
			}	
		}
		else
		{
			// Nix für mich ;)
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
