package ttreImages;

import java.util.*;
import java.awt.Color;
import java.awt.image.*;

public class Player {
	
    private int points;
    private ArrayList<Ticket> tickets;
    private ArrayList<ColorCard> card;
    private ArrayList<City> railRoadsBought;
    private ArrayList<Station> usedStationList;
    private ArrayList<Station> unusedStationList;
    private int trainsLeft;
    private String color;
    private Color plrColor;
    private boolean lastTurn;
    
    public Player(String color, BufferedImage stationImage, Color plrColor) {
        points = 0;
        trainsLeft = 45;
        tickets = new ArrayList<Ticket>();
        card = new ArrayList<ColorCard>();
        unusedStationList = new ArrayList<Station>();
        usedStationList = new ArrayList<Station>();
        unusedStationList.add(new Station(color, stationImage));
        unusedStationList.add(new Station(color, stationImage));
        unusedStationList.add(new Station(color, stationImage));
        this.color = color;
        this.plrColor = plrColor;
        lastTurn = false;
    }
    
    public int getStations()
    {
    	int size = 0;
    	size += unusedStationList.size();
    	return size;
    }
    
    public void useStation()
    {
    	if(unusedStationList.size()>=1)
    		usedStationList.add(unusedStationList.remove(unusedStationList.size()-1));
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public ArrayList<Ticket> getTicket() {
        return tickets;
    }

    public void setPoint(int pt) {
        points = pt;
    }

    public int getPoint() {
        return points;
    }

    public void addCard(ColorCard card) {
        this.card.add(card);
    }
	
	public void addCard(String color, int num)
	{
		for( int i: num)
			card.add(new ColorCard(color)
	}
	
    public ArrayList<ColorCard> getCard() {
        return card;
    }
    
    public int getCardColor(String color) {
    	int total = 0;
    	for (int i = 0; i< card.size(); i++) {
    		if (card.get(i).getColor().equals(color)) {
    			total += 1;
    		}
    	}
    	return total;
    }
    
    public String getHighestColorNumStr() {
    	Integer highest = null;
    	String highestColor = null;
    	String[] arr = new String[9];
    	arr[0] = "purple";
    	arr[1] = "white";
    	arr[2] = "blue";
    	arr[3] = "yellow";
    	arr[4] = "brown";
    	arr[5] = "black";
    	arr[6] = "red";
    	arr[7] = "green";
    	arr[8] = "wild";
    	
    	for (int j = 0; j < arr.length; j++) {
    		int temp = 0;
    		for (int i = 0; i< card.size(); i++) {
        		if (card.get(i).getColor().equals(arr[j])) {
        			temp += 1;
        		}
        	}
    		if (highest == null || highest < temp) {
    			highest = temp;
    			highestColor = arr[j];
    		}
    	}
    	return highestColor;
    }
    
    public int getHighestColorNum() {
    	Integer highest = null;
    	String[] arr = new String[9];
    	arr[0] = "purple";
    	arr[1] = "white";
    	arr[2] = "blue";
    	arr[3] = "yellow";
    	arr[4] = "brown";
    	arr[5] = "black";
    	arr[6] = "red";
    	arr[7] = "green";
    	arr[8] = "wild";
    	
    	for (int j = 0; j < arr.length; j++) {
    		int temp = 0;
    		for (int i = 0; i< card.size(); i++) {
        		if (card.get(i).getColor().equals(arr[j])) {
        			temp += 1;
        		}
        	}
    		if (highest == null || highest < temp) {
    			highest = temp;
    		}
    	}
    	return highest;
    }
    
    public void removeCards(String color, int num)
    {
    	for(ColorCard cardRemove : card)
    	{
	    	if(cardRemove.getColor().equals(color) && num>0)
	    	{
	    		card.remove(cardRemove);
	    		num--;
	    	}
    	}
    }

    public void removeTrain(int numberToRemove)
    {
    	if( trainsLeft - numberToRemove >= 0)
    		trainsLeft -= numberToRemove;
    }
    
    public Station removeStation() {
    	Station station = unusedStationList.remove(0);
    	return station;
    }

    public int getTrainsLeft() {
        return trainsLeft;
    }

    public String getPlayerColor() {
    	return color;
    }
    
    public Color getPlrColor() {
    	return plrColor;
    }
    
    public void setLastTurn() {
    	lastTurn = true;
    }
    
    public boolean getLastTurn() {
    	return lastTurn;
    }
}
