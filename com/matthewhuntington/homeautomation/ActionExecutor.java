package com.matthewhuntington.homeautomation;

import java.util.ArrayList;

public class ActionExecutor
{
	protected ArrayList<Action> actions;
	protected static final String program_name = "br";
	protected static final String port_param = "--port=";
	protected static final String house_param = "--house=";
	protected static final String on_param = "--on=";
	protected static final String off_param = "--off=";
	protected static final String on = "--ON";
	protected static final String off = "--OFF";
	
	protected static final String default_port = "/dev/ttyS0";
	protected static final String default_command_start = program_name + " " + port_param + default_port + " " + house_param;

	public ActionExecutor(ArrayList<Action> actions)
	{
		this.actions = actions;
	}
	
	protected void call(String command)
	{
		try
		{
			Runtime.getRuntime().exec(command);
			//System.out.println(command);
		}
		catch(Exception e)
		{
			System.out.println("Could not execute runtime command");
			e.printStackTrace();
		}
	}
	
	public void activate(char house, int socket)
	{
		String command = default_command_start + house + " " + on_param + socket;
		call(command);
	}
	
	public void activate(char house)
	{
		String command = default_command_start + house + " " + on;
		call(command);
	}
	
	public void deactivate(char house, int socket)
	{
		String command = default_command_start + house + " " + off_param + socket;
		call(command);
	}
	
	public void deactivate(char house)
	{
		String command = default_command_start + house + " " + off;
		call(command);
	}
	
	public void execute(Action action)
	{
		if(action.getApplyToAllSockets())
		{
			if(action.getValue()==1)
				activate(action.getHouse());
			else if(action.getValue()==0)
				deactivate(action.getHouse());
		}
		else
		{
			if(action.getValue()==1)
				activate(action.getHouse(), action.getSocket());
			else if(action.getValue()==0)
				deactivate(action.getHouse(), action.getSocket());
		}
	}
	
	public void executeAll()
	{
		for(int i=0; i<actions.size(); i++)
		{
			execute(actions.get(i));
		}
	}
}
