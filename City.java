package ttreImages;

import java.util.*;
import java.awt.image.*;

public class City 
{
	private String name;
	private HashMap<String, RailRoad> connections;
	private Station hasStation;
	private int x;
	private int y;
	
	public City(String name, int x, int y)
	{
		this.name = name;
		this.x= x;
		this.y = y;
		hasStation = new Station();
	}
	
	public City(String name, String connection, RailRoad railRoad, Station station)
	{
		hasStation = station;
		this.name = name;
		connections.put(connection, railRoad);
	}
	
	public void addConnection(String connection, RailRoad railRoad)
	{
		connections.put(connection, railRoad);
	}
	
	public String getName()
	{
		return name;
	}
	
	public HashMap<String, RailRoad> getConnections()
	{
		return connections;
	}
	
	public void addStation(Station station)
	{
		hasStation = station;
	}
	
	public Station getHasStation()
	{
		return hasStation;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
