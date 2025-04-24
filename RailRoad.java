package ttreImages;

import java.awt.Color;
import java.util.*;

public class RailRoad
{
	private int cost;
	private String color;
	private int wildNum;
	private boolean mountains;
	private boolean bought;
	private int x;
	private int y;
	private double degree;
	private Color railRoadColor;
	private Player plrThatBoughtTheRailRoad;
	
	public RailRoad(int cost, String color, int wild, boolean mountains, boolean bought, int x, int y, double degree)
	{
		this.cost = cost;
		this.color = color;
		wildNum = wild;
		this.mountains = mountains;
		this.bought = bought;
		this.x = x;
		this.y = y;
		this.degree = degree;
		this.railRoadColor = null;
		plrThatBoughtTheRailRoad = null;
	}
	
	public int getX() {
		return x;
	}
	
	public Player getThePlayerRailroad() {
		return plrThatBoughtTheRailRoad;
	}
	
	public Color getRailRoadColor() {
		return railRoadColor;
	}
	
	public int getY() {
		return y;
	}
	
	public double getDegree() {
		return degree;
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
	
	public boolean getPlrBought()
	{
		return bought;
	}
	
	public void setBought(Player plrThatBoughtTheRailRoad, Color railRoadColor) {
		bought = true;
		this.plrThatBoughtTheRailRoad = plrThatBoughtTheRailRoad;
		this.railRoadColor = railRoadColor;
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
