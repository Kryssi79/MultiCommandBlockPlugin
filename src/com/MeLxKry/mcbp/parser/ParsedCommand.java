package com.MeLxKry.mcbp.parser;

public class ParsedCommand
{
	protected int m_Interval = 5; // standard value
	protected String m_CommandLine;
	
	public void setInterval(int Interval)
	{
		this.m_Interval = Interval;
	}
	
	public int getInterval()
	{
		return this.m_Interval;
	}
	
	public void setCommand(String Command)
	{
		this.m_CommandLine = Command;
	}
	
	public String getCommand()
	{
		return this.m_CommandLine;
	}
	
	@Override
	protected void finalize() throws Throwable {
		m_Interval = 0;
		m_CommandLine = null;
		super.finalize();
	}
}
