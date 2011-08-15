package com.matthewhuntington.homeautomation;

public class Action
{
	protected int socket;
	protected char house;
	protected boolean apply_to_all_sockets;
	protected int value;
	
	public Action(char house, int value)
	{
		this.house=house;
		this.apply_to_all_sockets=true;
		this.value=value;
	}
	
	public Action(char house, int socket, int value)
	{
		this.house=house;
		this.socket=socket;
		this.apply_to_all_sockets=false;
		this.value=value;
	}
	
	public int getSocket()
	{
		return socket;
	}
	
	public char getHouse()
	{
		return house;
	}
	
	public boolean getApplyToAllSockets()
	{
		return apply_to_all_sockets;
	}
	
	public int getValue()
	{
		return value;
	}
}
