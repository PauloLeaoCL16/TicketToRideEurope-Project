package ttreImages;
import java.util.*;
import java.awt.image.*;

public class City 
{
	private String name;
	private HashMap<City, ArrayList<RailRoad>> connections;
	private Station hasStation;
	private int x;
	private int y;
	
	public City(String name, Station station, int x, int y)
	{
		hasStation = station;
		this.name = name;
		this.x = x;
		this.y = y;
		connections = new HashMap<>();
	}
	
	public void addConnection(City city, ArrayList<RailRoad> railRoad)
	{
		connections.put(city, railRoad);
	}
	
	public String getName()
	{
		return name;
	}
	
	public HashMap<City, ArrayList<RailRoad>> getConnections()
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
