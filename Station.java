import java.util.*;

public class Station 
{
	private String toCity;
	private String fromCity;
	
	public Station()
	{
		toCity = "";
		fromCity = "";
	}
	
	public String getToCity()
	{
		return toCity;
	}
	
	public String getFromCity()
	{
		return fromCity;
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
