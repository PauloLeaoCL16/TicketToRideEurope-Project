import java.util.*;
import java.awt.image.*;

public class Ticket 
{
	String toCity;
	String fromCity;
	int points;
	BufferedImage image;
	
	public Ticket(String toCity, String fromCity, int points, BufferedImage image)
	{
		this.toCity = toCity;
		this.fromCity = fromCity;
		this.points = points;
		this.image = image;
	}
	
	public String getToCity()
	{
		return toCity;
	}
	
	public String getFromCity()
	{
		return fromCity;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
