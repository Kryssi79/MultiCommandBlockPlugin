package com.MeLxKry.mcbp;

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
}
