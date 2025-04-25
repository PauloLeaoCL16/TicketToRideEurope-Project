package ttreImages;

import java.util.*;
import java.awt.image.*;

public class Station 
{
	private String toCity;
	private String fromCity;
	private String color;
	private BufferedImage stationImage;
	
	public Station(String color, BufferedImage stationImage)
	{
		toCity = "";
		fromCity = "";
		this.color = color;
		this.stationImage = stationImage;
	}
	
	public String getToCity()
	{
		return toCity;
	}
	
	public String getFromCity()
	{
		return fromCity;
	}
	
	public String getColor() {
		return color;
	}
	
	public BufferedImage getStationImage() {
		return stationImage;
	}
	
	public void setToCity(String city)
	{
		toCity = city;
	}
	
	public void setFromCity(String city)
	{
		fromCity = city;
	}
	
}
