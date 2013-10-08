package com.MeLxKry.mcbp;

public class CommandParser {
	String[] m_CommandParts;

	public void Parse(String CommandStr)
	{
		// splitt string after every semicolon
		m_CommandParts = CommandStr.split(";");
		if (m_CommandParts.length == 0)  {
			//wenn kein semikolon vorhanden dann nimm den ganzen string auf platz 0 ;)
			m_CommandParts = new String[1];
			m_CommandParts[0] = CommandStr;
		}
		else
		{
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
