import java.util.*;

public class RailRoad
{
	private int cost;
	private String color;
	private int wildNum;
	private boolean mountains;
	private boolean bought;
	
	public RailRoad(int cost, String color, int wild, boolean mountains, boolean bought)
	{
		this.cost = cost;
		this.color = color;
		wildNum = wild;
		this.mountains = mountains;
		this.bought = bought;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public int getWildNum()
	{
		return wildNum;
	}
	
	public boolean getMountains()
	{
		return mountains;
	}
	
	public boolean getBought()
	{
		return bought;
	}
	
	public int getPoints()
	{
		if(cost == 1)
			return 1;
		else if(cost == 2)
			return 2;
		else if(cost == 3)
			return 4;
		else if(cost == 4)
			return 7;
		else if(cost == 6)
			return 15;
		else // cost == 8
			return 21;
	}
}
