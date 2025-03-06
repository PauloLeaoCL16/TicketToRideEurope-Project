import java.util.*;
import java.awt.image.*;

public class City 
{
	private String name;
	private HashMap<String, RailRoad> connections;
	private Station hasStation;
	
	public City(String name, String connection, RailRoad railRoad, Station station)
	{
		hasStation = station;
		this.name = name;
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
	
	public void setHasStation(Station station)
	{
		hasStation = station;
	}
	
	public Station getHasStation()
	{
		return hasStation;
	}
}
