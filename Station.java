package ttreImages;

import java.util.*;

public class Station 
{
	private String toCity;
	private String fromCity;
	private String color;
	
	public Station(String color)
	{
		toCity = "";
		fromCity = "";
		this.color = color;
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
	
	public void setToCity(String city)
	{
		toCity = city;
	}
	
	public void setFromCity(String city)
	{
		fromCity = city;
	}
	
}
